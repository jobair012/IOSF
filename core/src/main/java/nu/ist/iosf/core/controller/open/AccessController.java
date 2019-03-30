package nu.ist.iosf.core.controller.open;

import nu.ist.iosf.commons.enums.ResponseCode;
import nu.ist.iosf.commons.models.appBasic.Response;
import nu.ist.iosf.commons.models.request.TokenRequest;
import nu.ist.iosf.commons.models.response.Token;
import nu.ist.iosf.core.appBasic.i18n.MessageKey;
import nu.ist.iosf.core.controller.BaseController;
import nu.ist.iosf.core.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController extends BaseController {
	
	@Autowired private AccessService accessService;

	@PostMapping("access-token")
	public Response<Token> getAccessToken(@RequestBody @Validated TokenRequest tokenRequest, BindingResult result){
		if(result.hasErrors()) {
			return new Response<>(ResponseCode.INVALID_ARGUMENT.getCode(), getMessage(MessageKey.INVALID_ARGUMENTS));
		}
		
		return accessService.getTokenResponse(tokenRequest);
	}
	
	@PostMapping("delete-token")
	public Response<?> getAccessToken(){
		return accessService.deleteToken();
	}
}
