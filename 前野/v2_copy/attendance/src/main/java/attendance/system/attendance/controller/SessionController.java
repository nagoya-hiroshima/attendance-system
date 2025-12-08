package attendance.system.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/login")
public class SessionController {

    /** ① ログイン画面表示 */
    @GetMapping("/auth")
    public String showLoginPage() {
        return "login";
    }

    /** ② ログイン処理 */
    @PostMapping("/auth")
    public String login(
            @RequestParam("user_id") Long user_id,
            @RequestParam("password") String password) {

        System.out.println("ログイン試行: user_id=" + user_id + ", password=" + password);

        return "redirect:/attendance";
    }

    /** ③ ログアウト処理 */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/api/login/auth"; // ← 修正！
    }
}
