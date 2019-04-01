package nu.ist.iosf.web.appBasic.provider;

import org.springframework.security.authentication.AccountStatusException;

@SuppressWarnings("serial")
public class IOSFUserStatusException extends AccountStatusException{
	public IOSFUserStatusException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
