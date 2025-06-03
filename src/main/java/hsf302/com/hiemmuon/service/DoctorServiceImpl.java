package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.pojo.Doctor;
import hsf302.com.hiemmuon.pojo.Role;
import hsf302.com.hiemmuon.pojo.User;
import hsf302.com.hiemmuon.repository.DoctorRepository;
import hsf302.com.hiemmuon.repository.RoleRepository;
import hsf302.com.hiemmuon.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor loginDoctor(String email, String password) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByUserEmail(email);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            if (doctor.getUser().getPasswordHash().equals(password)) {
                return doctor;
            }
        }
        return null;
    }
}
