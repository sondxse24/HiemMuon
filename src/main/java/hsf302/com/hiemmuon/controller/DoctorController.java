package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.entity.Doctor;
import hsf302.com.hiemmuon.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> manageDoctors() {
        return doctorService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createDoctor(
            @RequestBody Doctor request) {
        try {
            Doctor doctor = doctorService.createDoctor(
                    request.getUser(),
                    request.getDescription(),
                    request.getExperience());
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<?> updateDoctor(
            @PathVariable("doctorId") int id, @RequestBody Doctor updateDoctor) {
        try {
            Doctor savedDoctor = doctorService.updateDoctor(id, updateDoctor);
            return ResponseEntity.ok(savedDoctor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{doctorId}/status")
    public ResponseEntity<?> deactivateDoctor(
            @PathVariable("doctorId") int doctorId,
            @RequestParam("active") boolean active) {
        Doctor doctor = doctorService.getDoctorByUserId(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid doctor ID: " + doctorId));

        doctor.setIsActive(active);
        doctorService.saveDoctor(doctor);

        String statusText = active ? "activated" : "deactivated";
        return ResponseEntity.ok().body("Doctor " + statusText + " successfully.");
    }
}
