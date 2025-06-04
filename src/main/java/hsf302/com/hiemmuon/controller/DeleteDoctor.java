package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.pojo.Doctor;
import hsf302.com.hiemmuon.service.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteDoctor {
    @Autowired
    private DoctorServiceImpl doctorService;

    @PatchMapping("/doctors/delete/{doctorId}")
    public String deleteDoctor(@PathVariable("doctorId") int doctorId) throws Exception {
        Doctor doctor = doctorService.getDoctorByUserId(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id: " + doctorId));

        doctor.setIsActive(false);
        doctorService.saveDoctor(doctor);

        return "redirect:/doctors";
    }

}
