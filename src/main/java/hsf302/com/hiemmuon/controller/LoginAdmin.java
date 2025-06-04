package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.pojo.User;
import hsf302.com.hiemmuon.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginAdmin {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/loginAdmin")
    public String getLoginAdmin() {
        return "loginForAdmin";
    }

    @PostMapping("/loginAdmin")
    public String postLoginAdmin(@RequestParam String email,
                                 @RequestParam String password,
                                 HttpSession session,
                                 Model model) {

        if (!userService.isValidUser(email, password)) {
            model.addAttribute("error", "Sai email hoặc mật khẩu!");
            return "loginForAdmin";
        }

        User user = userService.getUserByEmail(email);

        if (user.getRole() == null || user.getRole().getRoleId() != 1) {
            model.addAttribute("error", "Tài khoản không có quyền quản trị!");
            return "loginForAdmin";
        }

        session.setAttribute("loggedInAdmin", user);
        return "menuAdmin";
    }
}
