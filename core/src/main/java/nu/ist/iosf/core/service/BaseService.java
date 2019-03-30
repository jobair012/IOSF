package nu.ist.iosf.core.service;

import nu.ist.iosf.core.appBasic.i18n.ServiceMessage;
import nu.ist.iosf.core.service.messaging.SmsService;
import nu.ist.iosf.entityRepo.entity.SystemUser;
import nu.ist.iosf.entityRepo.entity.UserToken;
import nu.ist.iosf.entityRepo.repository.UserTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseService {

	@Autowired private HttpServletRequest httpServletRequest;
	@Autowired private ServiceMessage serviceMessage;
	@Autowired private UserTokenRepo userTokenRepo;
	@Autowired protected SmsService smsService;

	protected String  getMessage(String id) {
		return serviceMessage.getMessage(id);
	}

	protected String getUserToken() {
		return httpServletRequest.getHeader("AuthenticationToken");
	}

	protected SystemUser getSystemUser(){
		if(!StringUtils.isEmpty(this.getUserToken())){
			UserToken userToken = userTokenRepo.getByUserToken(this.getUserToken());
			return userToken.getSystemUser();
		}
		return null;
	}
}
