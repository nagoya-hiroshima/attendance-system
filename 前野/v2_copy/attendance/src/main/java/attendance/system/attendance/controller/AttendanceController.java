package attendance.system.attendance.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AttendanceController {
    @GetMapping("/attendance")
    public String attendancePage() {
        return "attendance";
    }
}
