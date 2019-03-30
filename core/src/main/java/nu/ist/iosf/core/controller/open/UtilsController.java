package nu.ist.iosf.core.controller.open;

import nu.ist.iosf.commons.models.appBasic.KeyValue;
import nu.ist.iosf.commons.models.appBasic.Response;
import nu.ist.iosf.core.controller.BaseController;
import nu.ist.iosf.core.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilsController extends BaseController {
	
	@Autowired private LookupService lookupService;

	@GetMapping("roles")
	public Response<List<KeyValue>> roleList(){
		return lookupService.getRoleList();
	}
}
