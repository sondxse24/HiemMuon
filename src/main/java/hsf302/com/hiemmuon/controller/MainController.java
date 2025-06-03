package hsf302.com.hiemmuon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String firstPage() {
        return "firstPage";
    }

    @GetMapping("/menuDoctor")
    public String menuDoctorPage() {
        return "menuDoctor";
    }
}
