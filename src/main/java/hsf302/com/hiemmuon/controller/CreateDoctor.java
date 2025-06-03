package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.pojo.Doctor;
import hsf302.com.hiemmuon.pojo.Role;
import hsf302.com.hiemmuon.pojo.User;
import hsf302.com.hiemmuon.repository.DoctorRepository;
import hsf302.com.hiemmuon.repository.RoleRepository;
import hsf302.com.hiemmuon.repository.UserRepository;
import hsf302.com.hiemmuon.service.DoctorService;
import hsf302.com.hiemmuon.service.RoleService;
import hsf302.com.hiemmuon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class CreateDoctor {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/createDoctor")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("roles", roleService.findAll());
        return "createDoctor";
    }

    @PostMapping("/createForDoctor")
    public String createDoctor(@ModelAttribute("user") User user,
                               @RequestParam("description") String description,
                               @RequestParam("experience") int experience,
                               Model model) {
        try {
            Doctor doctor = doctorService.createDoctor(user, description, experience);
            return "loginForDoctor";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("roles", roleService.findAll());
            return "createDoctor";
        }
    }
}
