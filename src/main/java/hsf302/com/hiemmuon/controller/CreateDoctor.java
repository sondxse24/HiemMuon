package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.pojo.Doctor;
import hsf302.com.hiemmuon.pojo.User;
import hsf302.com.hiemmuon.repository.DoctorRepository;
import hsf302.com.hiemmuon.repository.RoleRepository;
import hsf302.com.hiemmuon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class CreateDoctor {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private RoleRepository roleRepo;

    @GetMapping("/createDoctor")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("roles", roleRepo.findAll());
        return "createDoctor";
    }

    @PostMapping("/createForDoctor")
    public String createDoctor(@ModelAttribute("user") User user,
                               @RequestParam("description") String description,
                               @RequestParam("experience") int experience,
                               @RequestParam(value = "isActive", required = false) Boolean isActive,
                               Model model) {
        if (userRepo.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email đã tồn tại!");
            model.addAttribute("roles", roleRepo.findAll());
            return "createDoctor";
        }

        user.setCreateAt(LocalDate.now());
        user.setUpdateAt(LocalDate.now());
        // TODO: Hash password thực tế
        User savedUser = userRepo.save(user);

        Doctor doctor = new Doctor();
        doctor.setUser(savedUser);
        doctor.setDescription(description);
        doctor.setExperience(experience);
        doctor.setIsActive(isActive != null ? isActive : false);

        doctorRepo.save(doctor);

        return "loginForDoctor";
    }
}
