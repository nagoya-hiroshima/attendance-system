package attendance.system.attendance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SessionController {
    /** ① ログイン画面表示 */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // login.html
    }

    /** ② ログイン処理 */
    @PostMapping("/login")
    public String login(
            @RequestParam("user_id") Long user_id,
            @RequestParam("password") String password) {

        // --- 本来はService呼び出して認証 ---
        // if (authService.login(user_id, password)) { ... }

        System.out.println("ログイン試行: user_id=" + user_id + ", password=" + password);

        // 認証OK → メニュー画面へ
        return "redirect:/menu";
    }

    /** ③ ログアウト処理 */
    @GetMapping("/logout")
    public String logout(@RequestParam(value = "user_id", required = false) Long user_id) {

        System.out.println("ログアウト: user_id=" + user_id);

        // セッション削除などここで行う

        return "redirect:/login";
    }
}
