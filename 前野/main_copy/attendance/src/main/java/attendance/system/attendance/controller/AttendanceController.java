package attendance.system.attendance.controller;

import attendance.system.attendance.dto.ClockInRequest;
import attendance.system.attendance.dto.ClockOutRequest;
import attendance.system.attendance.service.AttendanceService;
import attendance.system.attendance.message.MessageCode;
import org.springframework.stereotype.Controller;
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
    public String showAttendancePage() {
        return "attendance";
    }

    // 出勤
    @PostMapping("/clock/in")
    public String clockIn(
            @RequestParam String work_place,
            RedirectAttributes ra
    ) {
        try {
            ClockInRequest req = new ClockInRequest();
            req.setUserId(1L);              // ※ ログイン連携前の仮ID
            req.setWorkPlace(work_place);

            attendanceService.clockIn(req);

            ra.addAttribute("msg", MessageCode.M0010.name()); // 出勤しました
        } catch (Exception e) {
            ra.addAttribute("msg", MessageCode.M0011.name()); // 勤務場所エラー等
        }

        return "redirect:/api/attendance/auth";
    }

    // 退勤
    @PostMapping("/clock/out")
    public String clockOut(RedirectAttributes ra) {
        try {
            ClockOutRequest req = new ClockOutRequest();
            req.setUserId(1L); // ※ ログイン連携前の仮ID

            attendanceService.clockOut(req);

            ra.addAttribute("msg", MessageCode.M0012.name()); // 退勤しました
        } catch (Exception e) {
            ra.addAttribute("msg", MessageCode.M0012.name());
        }

        return "redirect:/api/attendance/auth";
    }
}
