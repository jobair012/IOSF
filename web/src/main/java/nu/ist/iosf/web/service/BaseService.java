package nu.ist.iosf.web.service;

import nu.ist.iosf.web.appBasic.i18n.ServiceMessage;
import nu.ist.iosf.web.appBasic.rester.ServiceUrl;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
	
	@Autowired
	protected ServiceMessage serviceMessage;
	
	protected String  getMessageString(String id) {
		return serviceMessage.getMessage(id);
	}
	
	protected static String getFullUrl(String url) {
		return ServiceUrl.BASE_URL.concat(url);
	}
	
}
