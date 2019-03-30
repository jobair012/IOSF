package nu.ist.iosf.core.service;

import nu.ist.iosf.commons.enums.ResponseCode;
import nu.ist.iosf.commons.enums.Status;
import nu.ist.iosf.commons.models.appBasic.KeyValue;
import nu.ist.iosf.commons.models.appBasic.Response;
import nu.ist.iosf.core.appBasic.i18n.MessageKey;
import nu.ist.iosf.core.appBasic.mapper.DbObjectMapper;
import nu.ist.iosf.entityRepo.entity.UserGroup;
import nu.ist.iosf.entityRepo.repository.SystemUserRepo;
import nu.ist.iosf.entityRepo.repository.UserGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LookupService extends BaseService{

	@Autowired private DbObjectMapper dbObjectMapper;
	@Autowired private UserGroupRepo userGroupRepo;
	@Autowired private SystemUserRepo systemUserRepo;
	
	public Response<List<KeyValue>> getRoleList() {
		Response<List<KeyValue>> response = new Response<>();	
		
		response.setItems(this.makeRoleList());
		response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
		response.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));
		
		return response;
	}
	
	public Response<List<KeyValue>> getStatusList() {
		Response<List<KeyValue>> response = new Response<>();	
		
		response.setItems(this.makeStatusList());
		response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
		response.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));
		
		return response;
	}

	private List<KeyValue> makeStatusList() {
		List<KeyValue> statusList = new ArrayList<>();
		
		statusList.add(new KeyValue(String.valueOf(Status.INACTIVE.getCode()), Status.INACTIVE.getDescription()));
		statusList.add(new KeyValue(String.valueOf(Status.ACTIVE.getCode()), Status.ACTIVE.getDescription()));
		
		return statusList;
	}

	private List<KeyValue> makeRoleList() {
		List<KeyValue> roleList = new ArrayList<>();

		for(UserGroup userGroup : userGroupRepo.getAllActiveUserGroup()){
			roleList.add(new KeyValue(userGroup.getId(), userGroup.getName()));
		}

		return roleList;
	}

	public Response<List<KeyValue>> getAllSystemUser() {
		Response<List<KeyValue>> response = new Response<>();
		response.setItems(systemUserRepo.getKeyValue("id", "username", null));
		response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
		response.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));

		return response;
	}
}
