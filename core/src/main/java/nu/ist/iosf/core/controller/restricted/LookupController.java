package nu.ist.iosf.core.controller.restricted;

import nu.ist.iosf.commons.models.appBasic.KeyValue;
import nu.ist.iosf.commons.models.appBasic.Response;
import nu.ist.iosf.core.controller.BaseController;
import nu.ist.iosf.core.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lookup")
public class LookupController extends BaseController {

    @Autowired private LookupService lookupService;

    @GetMapping("systemuser")
    public Response<List<KeyValue>> systemUserStatus() {
        return lookupService.getAllSystemUser();
    }

}
