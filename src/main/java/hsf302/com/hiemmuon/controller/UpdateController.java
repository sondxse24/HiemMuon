package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.pojo.Doctor;
import hsf302.com.hiemmuon.pojo.User;
import hsf302.com.hiemmuon.service.DoctorService;
import hsf302.com.hiemmuon.service.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateController {

    @Autowired
    private DoctorServiceImpl doctorService;

    @GetMapping("/doctors/edit/{doctorId}")
    public String editDoctor(@PathVariable("doctorId") int id, Model model) {
        Doctor doctor = doctorService.getDoctorByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));
        model.addAttribute("doctor", doctor);
        return "editDoctor";
    }

    @PostMapping("/doctors/edit/{doctorId}")
    public String updateDoctor(@PathVariable("doctorId") int id, @ModelAttribute Doctor doctor) throws Exception {

        Doctor existingDoctor = doctorService.getDoctorByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));

        User existingUser = existingDoctor.getUser();
        existingUser.setName(doctor.getUser().getName());
        existingUser.setPhone(doctor.getUser().getPhone());
        existingUser.setPassword(doctor.getUser().getPassword());

        existingDoctor.setUser(existingDoctor.getUser());
        existingDoctor.setExperience(doctor.getExperience());
        existingDoctor.setDescription(doctor.getDescription());

        doctorService.saveDoctor(existingDoctor);
        return "redirect:/doctors";
    }
}
