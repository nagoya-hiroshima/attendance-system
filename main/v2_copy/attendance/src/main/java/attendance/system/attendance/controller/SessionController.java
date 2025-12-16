package attendance.system.attendance.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/login")
public class SessionController {

    private static final String DEMO_USER_ID = "1001";
    private static final String DEMO_PASSWORD = "password";

    @GetMapping("/auth")
    public String showLoginPage() {
        return "index";
    }

    @PostMapping("/auth")
    public String login(@RequestParam("user_id") String userId,
                        @RequestParam("password") String password,
                        HttpSession session) {

        if (DEMO_USER_ID.equals(userId) && DEMO_PASSWORD.equals(password)) {
            session.setAttribute("userId", userId);
            return "redirect:/api/attendance/auth";
        } else {
            session.setAttribute("loginError", "ユーザーIDかパスワードが間違っています");
            return "redirect:/api/login/auth";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/api/login/auth";
    }
}

