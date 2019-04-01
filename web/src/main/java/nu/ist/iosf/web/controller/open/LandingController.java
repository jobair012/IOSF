package nu.ist.iosf.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LandingController {

    @GetMapping("")
    public ModelAndView showLandingPage() {
        return new ModelAndView("index");
    }
}
