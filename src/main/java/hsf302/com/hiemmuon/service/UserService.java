package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserByEmail(String email);
    boolean isValidUser(String email, String password);
}
