package nu.ist.iosf.web.service;

import nu.ist.iosf.commons.enums.ResponseCode;
import nu.ist.iosf.commons.models.appBasic.Response;
import nu.ist.iosf.commons.models.request.TokenRequest;
import nu.ist.iosf.commons.models.response.Token;
import nu.ist.iosf.web.appBasic.rester.IOSFRestTemplate;
import nu.ist.iosf.web.appBasic.rester.ServiceUrl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class TokenService extends BaseService {

	public Response<Token> getToken(String username, String password) {
		IOSFRestTemplate<Response<Token>> restTemplate = new IOSFRestTemplate<>(serviceMessage);
		TokenRequest requestBody = new TokenRequest();
		requestBody.setUsername(username);
		requestBody.setPassword(password);

		return restTemplate.postWithoutHeader(ServiceUrl.GET_TOKEN, requestBody,
				new ParameterizedTypeReference<Response<Token>>() {
				});
	}

	public boolean deleteToken() {
		IOSFRestTemplate<Response<?>> restTemplate = new IOSFRestTemplate<>(serviceMessage);

		Response<?> response = restTemplate.postWithoutHeader(ServiceUrl.DELETE_TOKEN, null, new ParameterizedTypeReference<Response<?>>() { });
		if (ResponseCode.OPERATION_SUCCESSFUL.getCode() == response.getResponseCode()) {
			return true;
		}
		return false;
	}

}
