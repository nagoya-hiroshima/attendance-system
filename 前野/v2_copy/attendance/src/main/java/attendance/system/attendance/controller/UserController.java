package attendance.system.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/api/user/auth")
    public String showUserInfoPage() {
        return "user_info";
    }
}
