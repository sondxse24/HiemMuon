package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.pojo.Doctor;
import hsf302.com.hiemmuon.pojo.Role;
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
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/createDoctor")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("roles", roleRepository.findAll());
        return "createDoctor";
    }

    @PostMapping("/createForDoctor")
    public String createDoctor(@ModelAttribute("user") User user,
                               @RequestParam("description") String description,
                               @RequestParam("experience") int experience,
                               Model model) {
        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email đã tồn tại!");
            model.addAttribute("roles", roleRepository.findAll());
            return "createDoctor";
        }

        user.setCreateAt(LocalDate.now());
        user.setUpdateAt(LocalDate.now());

        Role role = roleRepository.findByRoleName("doctor");
        user.setRole(role);

        User savedUser = userRepository.save(user);

        Doctor doctor = new Doctor();
        doctor.setUser(savedUser);
        doctor.setDescription(description);
        doctor.setExperience(experience);
        doctor.setIsActive(true);

        doctorRepository.save(doctor);

        return "loginForDoctor";
    }
}
