package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.pojo.Doctor;
import hsf302.com.hiemmuon.service.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ReadDoctorByAdmin {
    @Autowired
    private DoctorServiceImpl doctorService;

    @GetMapping("/manageDoctors")
    public String manageDoctors(Model model) {
        List<Doctor> doctors = doctorService.findAll();
        model.addAttribute("manageDoctors", doctors);
        return "manageDoctorByAdmin";
    }
}
