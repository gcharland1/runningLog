package GabrielCharland.runningLog.Home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/")
public class HomeController {

    @GetMapping
    public String hello(Model model, @RequestParam(required = false) String name) {
        if (name != null) {
            model.addAttribute("name", name);
        } else {
            model.addAttribute("name", "Slim Shaddy");
        }
        return "home";
    }

}
