//package hsf302.com.hiemmuon.controller;
//
//import hsf302.com.hiemmuon.pojo.Doctor;
//import hsf302.com.hiemmuon.repository.DoctorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class ReadDoctorByManager {
//
//    @Autowired
//    private DoctorRepository doctorRepository;
//
//    @GetMapping("/doctors")
//    public String listDoctors(Model model) {
//        List<Doctor> doctors = doctorRepository.findByIsActiveTrue();
//        model.addAttribute("doctors", doctors);
//        return "manageDoctorByManager";
//    }
//}
