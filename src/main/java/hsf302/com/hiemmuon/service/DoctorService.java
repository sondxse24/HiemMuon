package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.entity.Doctor;
import hsf302.com.hiemmuon.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DoctorService {
    Doctor loginDoctor(String email, String password);

    Doctor createDoctor(User user, String description, int experience) throws Exception;

    Optional<Doctor> getDoctorByUserId(int userId);

    Doctor saveDoctor(Doctor doctor) throws Exception;

    List<Doctor> findAll();

    Doctor updateDoctor(int id, Doctor doctor);
}

