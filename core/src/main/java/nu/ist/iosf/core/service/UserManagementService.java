package nu.ist.iosf.core.service;

import nu.ist.iosf.commons.enums.ResponseCode;
import nu.ist.iosf.commons.enums.Status;
import nu.ist.iosf.commons.models.appBasic.IOSFSearchResult;
import nu.ist.iosf.commons.models.appBasic.Response;
import nu.ist.iosf.commons.models.request.SystemUserSearchRequest;
import nu.ist.iosf.commons.models.request.UserCrupRequest;
import nu.ist.iosf.commons.models.response.*;
import nu.ist.iosf.core.appBasic.i18n.MessageKey;
import nu.ist.iosf.core.appBasic.mapper.DbObjectMapper;
import nu.ist.iosf.entityRepo.entity.RoleGroup;
import nu.ist.iosf.entityRepo.entity.SystemUser;
import nu.ist.iosf.entityRepo.entity.SystemUserDetails;
import nu.ist.iosf.entityRepo.entity.UserRoleGroup;
import nu.ist.iosf.entityRepo.repository.RoleGroupRepo;
import nu.ist.iosf.entityRepo.repository.SystemUserDetailsRepo;
import nu.ist.iosf.entityRepo.repository.SystemUserRepo;
import nu.ist.iosf.entityRepo.repository.UserRoleGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserManagementService extends BaseService {

    @Autowired private LookupService lookupService;
    @Autowired private SystemUserRepo systemUserRepo;
    @Autowired private SystemUserDetailsRepo systemUserDetailsRepo;
    @Autowired private UserRoleGroupRepo userRoleGroupRepo;
    @Autowired private RoleGroupRepo roleGroupRepo;
    @Autowired private DbObjectMapper dbObjectMapper;

    public Response<UserCrupBasicInfo> getUserCRUPBasicInfo() {
        Response<UserCrupBasicInfo> response = new Response<>();

        response.setItems(this.makeUserCrupBasicInfo());
        response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
        response.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));

        return response;
    }

    private UserCrupBasicInfo makeUserCrupBasicInfo() {
        UserCrupBasicInfo basicInfo = new UserCrupBasicInfo();
        basicInfo.setRoleList(lookupService.getRoleList().getItems());
        basicInfo.setStatusList(lookupService.getStatusList().getItems());

        return basicInfo;
    }

    public Response<Crup> crupSystemUser(UserCrupRequest request) {
        Response<Crup> crupResponse = new Response<>();
        SystemUser loggedInSystemUser = super.getSystemUser();
        SystemUser systemUser;

        /* --- creating system user --- */
        if (request.getUserId() == null || request.getUserId() == 0) {
            if (systemUserRepo.getUserByUsername(request.getUsername()) == null) {
                systemUser = this.createSystemUser(request, loggedInSystemUser);

                /*if(systemUser.getId() != null){
                    new Thread(() -> {
                        smsService.sendSMS(request.getContactNo(), "Congratulations! You have successfully registered. Your credentials are given here. " +
                                "\nUsername: " + systemUser.getUsername() + "\nPassword: " + systemUser.getPassword());
                    }).start();
                }*/

            }else{
                crupResponse.setResponseCode(ResponseCode.WRONG_INFORMATION.getCode());
                crupResponse.setResponseMessage(getMessage(MessageKey.USERNAME_EXISTS));
                return crupResponse;
            }
        } else {  /* --- updating system user --- */
            systemUser = this.updateSystemUser(request, loggedInSystemUser);
        }

        crupResponse.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
        crupResponse.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));
        crupResponse.setItems(new Crup(systemUser.getId()));
        return crupResponse;
    }

    private SystemUser createSystemUser(UserCrupRequest request, SystemUser loggedInSystemUser) {
        SystemUser systemUser = this.makeSystemUser(request, loggedInSystemUser);
        systemUserRepo.create(systemUser);
        systemUserDetailsRepo.create(this.makeSystemUserDetails(request, systemUser, loggedInSystemUser));
        this.createUserRoleGroup(request, systemUser, loggedInSystemUser);
        return systemUser;
    }

    private SystemUser updateSystemUser(UserCrupRequest request, SystemUser loggedInSystemUser) {
        SystemUser systemUser = systemUserRepo.get(request.getUserId());
        this.updateUserRoleGroup(request, systemUser, loggedInSystemUser);
        this.updateSystemUserDetails(request, systemUser, loggedInSystemUser);

        systemUser.setStatus(request.getStatus());
        systemUser.setUpdatedBy(loggedInSystemUser.getUsername());
        systemUserRepo.update(systemUser);

        return systemUser;
    }

    private void updateSystemUserDetails(UserCrupRequest request, SystemUser systemUser, SystemUser loggedInSystemUser) {
        SystemUserDetails systemUserDetails = systemUser.getSystemUserDetails();
        systemUserDetails.setContactNo(request.getContactNo());
        systemUserDetails.setEmailAddress(request.getEmailAddress());
        systemUserDetails.setFullName(request.getFullName());
        systemUserDetails.setStatus(request.getStatus());
        systemUserDetails.setUpdatedBy(loggedInSystemUser.getUsername());
        systemUserDetailsRepo.update(systemUserDetails);
    }

    private void updateUserRoleGroup(UserCrupRequest request, SystemUser systemUser, SystemUser loggedInSystemUser) {

        List<UserRoleGroup> userRoleGroupList = systemUser.getUserRoleGroup();
        /* --- system user contains these roles --- */
        Set<String> groups = this.getUserGroupsOfUser(systemUser);

        groups.forEach(group -> {
            if (!group.equalsIgnoreCase(request.getRole())) {
                userRoleGroupList.forEach(userRoleGroup -> {
                    userRoleGroup.setStatus(Status.INACTIVE.getCode());
                    userRoleGroup.setUpdatedBy(loggedInSystemUser.getUsername());
                    userRoleGroupRepo.update(userRoleGroup);
                });
            }
        });
        this.createUserRoleGroup(request, systemUser, loggedInSystemUser);
    }

    private Set<String> getUserGroupsOfUser(SystemUser systemUser) {
        Set<String> groups = new HashSet<>();
        systemUser.getUserRoleGroup().forEach(role -> {
            if (Status.ACTIVE.getCode() == role.getStatus()) {
                groups.add(role.getRoleGroup().getUserGroup().getId());
            }
        });
        return groups;
    }

    private SystemUser makeSystemUser(UserCrupRequest request, SystemUser loggedInSystemUser) {
        SystemUser systemUser = new SystemUser();
        systemUser.setId(request.getUserId());
        systemUser.setUsername(request.getUsername());
        systemUser.setPassword(String.valueOf(new Random().nextInt(900000) + 100000));
        systemUser.setStatus(Integer.valueOf(request.getStatus()));
        systemUser.setBadLoginAttempts(Integer.valueOf(0));
//        systemUser.setCreatedBy(loggedInSystemUser.getUsername());
//        systemUser.setUpdatedBy(loggedInSystemUser.getUsername());
        return systemUser;
    }

    private SystemUserDetails makeSystemUserDetails(UserCrupRequest request, SystemUser systemUser, SystemUser loggedInSystemUser) {
        SystemUserDetails systemUserDetails = new SystemUserDetails();
        systemUserDetails.setSystemUser(systemUser);
        systemUserDetails.setFullName(request.getFullName());
        systemUserDetails.setEmailAddress(request.getEmailAddress());
        systemUserDetails.setContactNo(request.getContactNo());
        systemUserDetails.setStatus(Integer.valueOf(request.getStatus()));
//        systemUserDetails.setCreatedBy(loggedInSystemUser.getUsername());
//        systemUserDetails.setUpdatedBy(loggedInSystemUser.getUsername());
        return systemUserDetails;
    }

    private void createUserRoleGroup(UserCrupRequest request, SystemUser systemUser, SystemUser loggedInSystemUser) {

        List<RoleGroup> roleGroupList = roleGroupRepo.getAllActiveByUserGroup(request.getRole());
        for (RoleGroup roleGroup : roleGroupList) {
            UserRoleGroup userRoleGroup = new UserRoleGroup();
            userRoleGroup.setSystemUser(systemUser);
            userRoleGroup.setRoleGroup(roleGroup);
            userRoleGroup.setStatus(Status.ACTIVE.getCode());
            userRoleGroup.setCreatedBy(loggedInSystemUser.getUsername());
            userRoleGroup.setUpdatedBy(loggedInSystemUser.getUsername());
            userRoleGroupRepo.create(userRoleGroup);
        }
    }

    public Response<SystemUserDetailsResponse> getSystemUser(Long id) {
        Response<SystemUserDetailsResponse> response = new Response<>();

        SystemUser systemUser = systemUserRepo.get(id);
        SystemUserDetailsResponse systemUserDetails = dbObjectMapper.map(systemUser, SystemUserDetailsResponse.class);

        if (systemUserDetails == null) {
            response.setResponseCode(ResponseCode.RECORD_NOT_FOUND.getCode());
            response.setResponseMessage(super.getMessage(MessageKey.RECORD_NOT_FOUND));
            return response;
        }

        response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
        response.setResponseMessage(super.getMessage(MessageKey.OPERATION_SUCCESSFUL));
        response.setItems(systemUserDetails);

        return response;
    }

    public Response<SystemUserStatusResponse> getSystemUserStatus(String username) {
        Response<SystemUserStatusResponse> response = new Response<>();
        SystemUserStatusResponse statusItem = new SystemUserStatusResponse();
        statusItem.setStatus(systemUserRepo.getUserStatusByUsername(username));

        if (statusItem.getStatus() == null) {
            response.setResponseCode(ResponseCode.RECORD_NOT_FOUND.getCode());
            response.setResponseMessage(super.getMessage(MessageKey.RECORD_NOT_FOUND));
        } else {
            response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
            response.setResponseMessage(super.getMessage(MessageKey.OPERATION_SUCCESSFUL));
        }

        response.setItems(statusItem);

        return response;
    }

    public Response<SystemUserSearchData> getSystemUserSearchData() {
        Response<SystemUserSearchData> response = new Response<>();

        response.setItems(this.makeSystemUserSearchData());
        response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
        response.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));

        return response;
    }

    private SystemUserSearchData makeSystemUserSearchData() {
        SystemUserSearchData searchData = new SystemUserSearchData();
        searchData.setRoleList(lookupService.getRoleList().getItems());
        searchData.setStatusList(lookupService.getStatusList().getItems());
        searchData.setUsernameList(lookupService.getAllSystemUser().getItems());

        return searchData;
    }

    public Response<IOSFSearchResult> getSystemUserSearchResult(SystemUserSearchRequest request) {

        Response<IOSFSearchResult> response = new Response<>();
        IOSFSearchResult searchResult = new IOSFSearchResult();
        IOSFSearchResult dbSearchResult = systemUserRepo.searchSystemUser(request);

        List<SystemUser> systemUserList = dbSearchResult.getRows();
        if(systemUserList.size() == 0){
            response.setResponseCode(ResponseCode.RECORD_NOT_FOUND.getCode());
            response.setResponseMessage(getMessage(MessageKey.RECORD_NOT_FOUND));
            return response;
        }

        List<SystemUserDetailsResponse> systemUserDetailsResponseList = new ArrayList<>();
        systemUserList.forEach(systemUser -> {
            systemUserDetailsResponseList.add(dbObjectMapper.map(systemUser, SystemUserDetailsResponse.class));
        });

        searchResult.setRows(systemUserDetailsResponseList);
        searchResult.setTotal(dbSearchResult.getTotal());

        response.setItems(searchResult);
        response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
        response.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));

        return response;
    }
}
