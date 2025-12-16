package attendance.system.attendance.service;

import attendance.system.attendance.dto.ClockInRequest;
import attendance.system.attendance.dto.ClockOutRequest;
import attendance.system.attendance.model.Attendance;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.AttendanceRepository;
import attendance.system.attendance.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            UserRepository userRepository
    ) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    // 出勤打刻
    public Attendance clockIn(ClockInRequest req) {
        Long userId = req.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

        // すでに出勤中かチェック
        attendanceRepository
                .findByUserUserIdAndClockoutTimeIsNull(userId)
                .ifPresent(a -> {
                    throw new RuntimeException("すでに出勤しています");
                });

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setClockinTime(LocalDateTime.now());

        return attendanceRepository.save(attendance);
    }

    // 退勤打刻
    public Attendance clockOut(ClockOutRequest req) {
        Long userId = req.getUserId();

        Attendance attendance = attendanceRepository
                .findByUserUserIdAndClockoutTimeIsNull(userId)
                .orElseThrow(() -> new RuntimeException("退勤対象の出勤が存在しません"));

        attendance.setClockoutTime(LocalDateTime.now());
        return attendanceRepository.save(attendance);
    }

    // 当日の勤怠取得
    public Attendance getAttendance(Long userId) {
        LocalDate today = LocalDate.now();

        return attendanceRepository
                .findByUserUserIdAndClockinTimeBetween(
                        userId,
                        today.atStartOfDay(),
                        today.plusDays(1).atStartOfDay()
                )
                .orElseThrow(() -> new RuntimeException("本日の勤怠が存在しません"));
    }
}