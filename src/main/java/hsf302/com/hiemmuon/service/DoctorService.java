package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.pojo.Doctor;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService {
    Doctor loginDoctor(String email, String password);
    }

