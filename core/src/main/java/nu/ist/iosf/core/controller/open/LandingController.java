package nu.ist.iosf.core.controller.open;

import nu.ist.iosf.commons.enums.ResponseCode;
import nu.ist.iosf.commons.models.appBasic.Response;
import nu.ist.iosf.commons.models.response.ServerStatus;
import nu.ist.iosf.commons.utils.IOSFUtils;
import nu.ist.iosf.core.appBasic.i18n.MessageKey;
import nu.ist.iosf.core.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class LandingController extends BaseController {

	private final LocalDateTime serverStartupDatetime = LocalDateTime.now();

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<ServerStatus> iosfCore() {
		Response<ServerStatus> response = new Response<>();

		ServerStatus serverStatus = new ServerStatus();
		serverStatus.setServerTime(IOSFUtils.getDateTimeString(LocalDateTime.now()));
		serverStatus.setStartupTime(IOSFUtils.getDateTimeString(this.serverStartupDatetime));

		response.setItems(serverStatus);
		response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
		response.setResponseMessage(getMessage(MessageKey.SERVICE_RUNNING));

		return response;
	}
}
