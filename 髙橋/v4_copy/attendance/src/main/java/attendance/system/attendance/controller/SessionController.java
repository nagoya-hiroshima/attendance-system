package attendance.system.attendance.controller;

import attendance.system.attendance.model.User;
import attendance.system.attendance.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/login")
public class SessionController {

    private final UserService userService;

    public SessionController(UserService userService) {
        this.userService = userService;
    }

    // ログイン画面表示
    @GetMapping("/auth")
    public String showLoginPage() {
        return "index";
    }

    // ログイン処理
    @PostMapping("/auth")
    public String login(@RequestParam("user_id") String userId,
                        @RequestParam("password") String password,
                        HttpSession session) {

        try {
            // DB認証（Serviceに委譲）
            User user = userService.login(userId, password);

            // ログイン成功
            session.removeAttribute("loginError");
            session.setAttribute("loginUser", user);

            // 初回登録なら登録画面へ
            if (userService.isFirstRegister(user)) {
                return "redirect:/api/register/auth";
            }

            // 2回目ログイン以降は勤怠画面へ
            return "redirect:/api/attendance/auth";

        } catch (RuntimeException e) {
            // ログイン失敗
            session.setAttribute("loginError", e.getMessage());
            return "redirect:/api/login/auth";
        }
    }

    // ログアウト処理
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/api/login/auth";
    }
}