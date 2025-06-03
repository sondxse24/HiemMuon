package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.pojo.Doctor;
import hsf302.com.hiemmuon.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService {
    Doctor loginDoctor(String email, String password);
    Doctor createDoctor(User user, String description, int experience) throws Exception;
    Doctor getDoctorByUserId(int userId) throws Exception;
    }

