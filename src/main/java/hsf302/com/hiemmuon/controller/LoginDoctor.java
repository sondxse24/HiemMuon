package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.pojo.Customer;
import hsf302.com.hiemmuon.pojo.Doctor;
import hsf302.com.hiemmuon.service.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.print.Doc;

@Controller
public class LoginDoctor {
    @Autowired
    private DoctorServiceImpl doctorService;

    @RequestMapping(value = "/loginDoctor", method = RequestMethod.GET)
    public String getLoginDoctor() {
        return "loginForDoctor";
    }

    @RequestMapping(value = "/loginForDoctor", method = RequestMethod.POST)

    public String loginDoctor(@ModelAttribute(name = "doctorDTO") Doctor doctor, Model model) {
        String email = doctor.getUser().getEmail();
        String password = doctor.getUser().getPasswordHash();

        Doctor doc = doctorService.loginDoctor(email, password);
        if (doc != null) {
            return "home";
        } else {
            return "loginForDoctor";
        }
    }
}
