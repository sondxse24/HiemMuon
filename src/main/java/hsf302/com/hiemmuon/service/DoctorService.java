package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.dto.CreateDoctorRequest;
import hsf302.com.hiemmuon.entity.Doctor;
import hsf302.com.hiemmuon.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DoctorService {

    Optional<Doctor> getDoctorByUserId(int userId);

    Doctor saveDoctor(Doctor doctor);

    List<Doctor> findAll();

    Doctor updateDoctor(int id, Doctor doctor);

    Doctor updateDoctorActive(int id, boolean active);

    void createDoctor(CreateDoctorRequest request);
}

