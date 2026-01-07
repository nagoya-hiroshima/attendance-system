package attendance.system.attendance.controller;

import attendance.system.attendance.dto.ClockInRequest;
import attendance.system.attendance.dto.ClockOutRequest;
import attendance.system.attendance.message.MessageCode;
import attendance.system.attendance.model.User;
import attendance.system.attendance.service.AttendanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // 勤怠入力画面
    @GetMapping("/api/attendance/auth")
    public String showAttendancePage(HttpSession session, Model model) {

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/api/login/auth";
        }
        boolean isClockedIn = attendanceService.isClockedIn(loginUser.getUserId());

        model.addAttribute("isClockedIn", isClockedIn);
        model.addAttribute("user", loginUser);

        // 出退勤メッセージ
        model.addAttribute("M0010", MessageCode.M0010.getMessage());
        model.addAttribute("M0012", MessageCode.M0012.getMessage());
        // ログアウトメッセージ
        model.addAttribute("M0013", MessageCode.M0013.getMessage());
        
        return "attendance";
    }

    // 出勤
    @PostMapping("/clock/in")
    public String clockIn(@RequestParam("work_place") String workPlace,
                          HttpSession session,
                          RedirectAttributes ra) {

        User loginUser = (User) session.getAttribute("loginUser");

        // ログインチェック
        if (loginUser == null) {
            return "redirect:/api/login/auth";
        }

        // 現状はフロントで出勤ボタンを制御しているため
        // M0011("勤務場所を選択してください")は未使用

        ClockInRequest req = new ClockInRequest();
        req.setUserId(loginUser.getUserId());
        req.setWorkPlace(workPlace);

        attendanceService.clockIn(req);

        ra.addAttribute("msg", "IN");
        return "redirect:/api/attendance/auth";
    }

    // 退勤
    @PostMapping("/clock/out")
    public String clockOut(HttpSession session,
                           RedirectAttributes ra) {

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/api/login/auth";
        }

        ClockOutRequest req = new ClockOutRequest();
        req.setUserId(loginUser.getUserId());

        attendanceService.clockOut(req);

        ra.addAttribute("msg", "OUT");
        return "redirect:/api/attendance/auth";
    }
}