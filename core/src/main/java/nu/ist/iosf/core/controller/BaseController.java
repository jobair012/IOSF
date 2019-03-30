package nu.ist.iosf.core.controller;

import nu.ist.iosf.core.appBasic.i18n.ServiceMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    private ServiceMessage serviceMessage;

    protected String getMessage(String messageKey) {
        return serviceMessage.getMessage(messageKey);
    }


}
