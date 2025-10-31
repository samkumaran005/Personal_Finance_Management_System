package com.finance.personalfinancemanager.controller;

import com.finance.personalfinancemanager.model.User;
import com.finance.personalfinancemanager.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLoginPage() {
        return "login"; // login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = userService.validateUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // remove all session attributes
        return "redirect:/";  // redirect to login page
    }
}
