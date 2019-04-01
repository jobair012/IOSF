package nu.ist.iosf.web.appBasic.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ServiceMessageImpl implements ServiceMessage {
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(String messageKey) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(messageKey, null, locale);
	}

}
