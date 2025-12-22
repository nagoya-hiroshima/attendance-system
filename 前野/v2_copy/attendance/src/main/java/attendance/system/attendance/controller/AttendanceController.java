package attendance.system.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import attendance.system.attendance.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Controller
public class AttendanceController {

    @GetMapping("/api/attendance/auth")
    public String showAttendancePage(HttpSession session, Model model) {

        // セッションからログインユーザ取得
        User loginUser = (User) session.getAttribute("loginUser");

        // 未ログインならログイン画面へ
        if (loginUser == null) {
            return "redirect:/api/login/auth";
        }

        // ★ これが無かったのが原因
        model.addAttribute("user", loginUser);

        return "attendance";
    }

    @PostMapping("/clock/in")
    public String clockIn(@RequestParam String work_place,
                          HttpSession session,
                          RedirectAttributes ra) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/api/login/auth";
        }

        ra.addAttribute("msg", "IN");
        return "redirect:/api/attendance/auth";
    }

    @PostMapping("/clock/out")
    public String clockOut(HttpSession session,
                           RedirectAttributes ra) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/api/login/auth";
        }

        ra.addAttribute("msg", "OUT");
        return "redirect:/api/attendance/auth";
    }
}

