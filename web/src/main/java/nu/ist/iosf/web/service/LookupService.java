package nu.ist.iosf.web.service;

import nu.ist.iosf.commons.models.appBasic.KeyValue;
import nu.ist.iosf.commons.models.appBasic.Response;
import nu.ist.iosf.web.appBasic.rester.IOSFRestTemplate;
import nu.ist.iosf.web.appBasic.rester.ServiceUrl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupService extends BaseService {

    public Response<List<KeyValue>> getAllSystemUser(){
        IOSFRestTemplate<Response<List<KeyValue>>> restTemplate = new IOSFRestTemplate<>(serviceMessage);
        return restTemplate.get(ServiceUrl.USERNAME_LOOKUP, null, new ParameterizedTypeReference<Response<List<KeyValue>>>() {});
    }

}
