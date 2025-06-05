package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.dto.CreateDoctorRequest;
import hsf302.com.hiemmuon.entity.Doctor;
import hsf302.com.hiemmuon.entity.Role;
import hsf302.com.hiemmuon.entity.User;
import hsf302.com.hiemmuon.repository.DoctorRepository;
import hsf302.com.hiemmuon.repository.RoleRepository;
import hsf302.com.hiemmuon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Optional<Doctor> getDoctorByUserId(int userId) {
        return doctorRepository.findByUserUserId(userId);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public void createDoctor(CreateDoctorRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(roleRepository.findByRoleName("doctor"));

        User savedUser = userRepository.save(user);

        Doctor doctor = new Doctor();
        doctor.setUser(savedUser);
        doctor.setIsActive(true);

        doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(int id, Doctor updateDoctor) {
        Doctor existingDoctor = getDoctorByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id: " + id));

        User existingUser = existingDoctor.getUser();
        User incomingUser = updateDoctor.getUser();

        if (incomingUser != null) {
            if (incomingUser.getName() != null) {
                existingUser.setName(incomingUser.getName());
            }
            if (incomingUser.getPhone() != null) {
                existingUser.setPhone(incomingUser.getPhone());
            }
            if (incomingUser.getPassword() != null) {
                existingUser.setPassword(incomingUser.getPassword());
            }
        }

        if (updateDoctor.getExperience() != 0) {
            existingDoctor.setExperience(updateDoctor.getExperience());
        }
        if (updateDoctor.getDescription() != null) {
            existingDoctor.setDescription(updateDoctor.getDescription());
        }
        return saveDoctor(existingDoctor);
    }

    @Override
    public Doctor updateDoctorActive(int id, boolean active) {
        Doctor doctor = getDoctorByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        ;
        doctor.setIsActive(active);
        return saveDoctor(doctor);
    }
}
