package attendance.system.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendanceController {

    // 勤怠入力画面にアクセス
    @GetMapping("/api/attendance/auth")
    public String showAttendancePage() {
        return "attendance";
    }
    
}
