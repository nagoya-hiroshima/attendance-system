package attendance.system.attendance.controller;

import attendance.system.attendance.dto.ClockInRequest;
import attendance.system.attendance.dto.ClockOutRequest;
import attendance.system.attendance.model.Attendance;
import attendance.system.attendance.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * 出勤打刻
     */
    @PostMapping("/clock-in")
    public Attendance clockIn(@RequestBody ClockInRequest request) {
        return attendanceService.clockIn(request);
    }

    /**
     * 退勤打刻
     */
    @PostMapping("/clock-out")
    public Attendance clockOut(@RequestBody ClockOutRequest request) {
        return attendanceService.clockOut(request);
    }

    /**
     * 本日の勤怠取得
     */
    @GetMapping("/{userId}")
    public Attendance getAttendance(@PathVariable Long userId) {
        return attendanceService.getAttendance(userId);
    }
}