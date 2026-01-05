package attendance.system.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    // 既存：ユーザ情報表示画面
    @GetMapping("/api/user/auth")
    public String showUserInfoPage() {
        return "user_info";
    }

    // 追加：ユーザ情報【登録】画面
    @GetMapping("/api/register/auth")
    public String showUserRegisterPage() {
        return "user_register"; // ← user_register.html を返す
    }

    // 追加：ユーザ登録処理（今回は DB 保存なし）
    @PostMapping("/api/register/auth")
    public String registerUser(
        @RequestParam("name") String name,
        @RequestParam("deploy_place") String deployPlace,
        @RequestParam("mail_address") String mail,
        @RequestParam("telephone_num") String tel,
        @RequestParam("emergency_num") String emergency,
        RedirectAttributes redirectAttributes
    ) {

        // ▼本来はサービス → DB 保存だが今回はスキップ
        System.out.println("【登録情報】");
        System.out.println("名前: " + name);
        System.out.println("部署: " + deployPlace);
        System.out.println("メール: " + mail);
        System.out.println("電話番号: " + tel);
        System.out.println("緊急連絡先: " + emergency);

        // 完了メッセージをセット
        redirectAttributes.addFlashAttribute("message", "ユーザ情報を登録しました！");

        // 登録後は勤怠画面へ
        return "redirect:/api/attendance/auth";
    }
}

