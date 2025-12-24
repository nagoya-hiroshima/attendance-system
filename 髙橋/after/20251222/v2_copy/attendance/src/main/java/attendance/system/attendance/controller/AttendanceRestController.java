package attendance.system.attendance.controller;

import attendance.system.attendance.dto.ClockInRequest;
import attendance.system.attendance.dto.ClockOutRequest;
import attendance.system.attendance.model.Attendance;
import attendance.system.attendance.service.AttendanceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceRestController {

    private final AttendanceService attendanceService;

    public AttendanceRestController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // 出勤打刻
    @PostMapping("/clock-in")
    public ResponseEntity<Attendance> clockIn(
            @RequestBody ClockInRequest request
    ) {
        Attendance attendance = attendanceService.clockIn(request);
        return ResponseEntity.ok(attendance);
    }

    // 退勤打刻
    @PostMapping("/clock-out")
    public ResponseEntity<Attendance> clockOut(
            @RequestBody ClockOutRequest request
    ) {
        Attendance attendance = attendanceService.clockOut(request);
        return ResponseEntity.ok(attendance);
    }

    // 当日の勤怠取得
    @GetMapping("/{userId}")
    public ResponseEntity<Attendance> getAttendance(
            @PathVariable Long userId
    ) {
        Attendance attendance = attendanceService.getAttendance(userId);
        return ResponseEntity.ok(attendance);
    }
}
