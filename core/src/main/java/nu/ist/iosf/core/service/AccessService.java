package nu.ist.iosf.core.service;

import nu.ist.iosf.commons.enums.ResponseCode;
import nu.ist.iosf.commons.enums.Status;
import nu.ist.iosf.commons.models.appBasic.Response;
import nu.ist.iosf.commons.models.request.TokenRequest;
import nu.ist.iosf.commons.models.response.Token;
import nu.ist.iosf.core.appBasic.i18n.MessageKey;
import nu.ist.iosf.entityRepo.entity.SystemUser;
import nu.ist.iosf.entityRepo.entity.UserRoleGroup;
import nu.ist.iosf.entityRepo.entity.UserToken;
import nu.ist.iosf.entityRepo.repository.SystemUserRepo;
import nu.ist.iosf.entityRepo.repository.UserTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class AccessService extends BaseService{
	
	@Autowired private SystemUserRepo systemUserRepo;
	@Autowired private UserTokenRepo userTokenRepo;

	public Response<Token> getTokenResponse(TokenRequest tokenRequest) {
		Response<Token> response = new Response<>();
				
		SystemUser user = systemUserRepo.getUserByUsername(tokenRequest.getUsername());
		if(user != null) {
			if(user.getPassword().equals(tokenRequest.getPassword())) {
				if( Status.ACTIVE.getCode() == user.getStatus() ) {
					response.setItems(this.makeToken(user));
					response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
					response.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));
				}else {
					response.setResponseCode(ResponseCode.NOT_DONE.getCode());
					response.setResponseMessage(getMessage(MessageKey.ACCOUNT_INACTIVE));
				}	
			}else {
				response.setResponseCode(ResponseCode.WRONG_INFORMATION.getCode());
				response.setResponseMessage(getMessage(MessageKey.WRONG_CREDENTIALS));
			}
		}else {
			response.setResponseCode(ResponseCode.RECORD_NOT_FOUND.getCode());
			response.setResponseMessage(getMessage(MessageKey.RECORD_NOT_FOUND));
		}
		
		return response;
	}
	
	public Response<?> deleteToken() {
		if(!StringUtils.isEmpty(super.getUserToken())){
			UserToken userToken = userTokenRepo.getByUserToken(super.getUserToken());
			if(userToken != null){
				userTokenRepo.delete(userToken);
			}
		}
		
		return new Response<>(ResponseCode.OPERATION_SUCCESSFUL.getCode(), getMessage(MessageKey.OPERATION_SUCCESSFUL));
	}
	
	private Token makeToken(SystemUser systemUser) {
		Token token = new Token();
		token.setFullName(systemUser.getSystemUserDetails().getFullName());
//		token.setGender(systemUser.getSystemUserDetails().getGender());
		token.setStatus(systemUser.getStatus());
		token.setToken(this.getTokenString(systemUser));
		token.setUsername(systemUser.getUsername());
		token.setUserRoles(this.getUserRoles(systemUser));
		return token;
	}
	
	private String getTokenString(SystemUser systemUser) {
		String uniqueId = UUID.randomUUID().toString();
		UserToken userToken = new UserToken();
		userToken.setSystemUser(systemUser);
		userToken.setToken(uniqueId);
		userToken.setExpireDate(LocalDateTime.now().plusHours(1));

		userTokenRepo.create(userToken);

		return uniqueId;
	}

	private Set<String> getUserRoles(SystemUser systemUser){
		Set<String> userRoles = new HashSet<>();
		if(systemUser.getUserRoleGroup() != null){
			for(UserRoleGroup userRoleGroup : systemUser.getUserRoleGroup()){
				userRoles.add(userRoleGroup.getRoleGroup().getUserGroup().getId());
			}
		}
		return userRoles;
	}

	public int getOne(){
		return 1;
	}
	
}
