package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.pojo.Doctor;
import hsf302.com.hiemmuon.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepositor;

    @Override
    public Doctor loginDoctor(String email, String password) {
        Optional<Doctor> optionalDoctor = doctorRepositor.findByUserEmail(email);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            if (doctor.getUser().getPasswordHash().equals(password)) {
                return doctor;
            }
        }
        return null;
    }
}
