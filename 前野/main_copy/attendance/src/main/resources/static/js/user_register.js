package attendance.system.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AttendanceController {

    // 勤怠入力画面
    @GetMapping("/api/attendance/auth")
    public String showAttendancePage() {
        return "attendance";
    }

    @PostMapping("/clock/in")
    public String clockIn(@RequestParam String work_place, RedirectAttributes ra) {
        ra.addAttribute("msg", "IN"); // 出勤しました
        return "redirect:/api/attendance/auth";
    }

    @PostMapping("/clock/out")
    public String clockOut(RedirectAttributes ra) {
        ra.addAttribute("msg", "OUT"); // 退勤しました
        return "redirect:/api/attendance/auth";
    }
}
