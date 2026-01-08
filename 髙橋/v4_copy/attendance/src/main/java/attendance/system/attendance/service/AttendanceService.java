package attendance.system.attendance.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import attendance.system.attendance.dto.ClockInRequest;
import attendance.system.attendance.dto.ClockOutRequest;
import attendance.system.attendance.dto.UserAttendanceStatusDto;
import attendance.system.attendance.model.Attendance;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.AttendanceRepository;
import attendance.system.attendance.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    // 出勤中かどうか判定
    public boolean isClockedIn(Long userId) {
        return attendanceRepository.existsByUserUserIdAndClockoutTimeIsNull(userId);
    }

    // 出勤打刻
    public Attendance clockIn(ClockInRequest req) {
        Long userId = req.getUserId();

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));

        if (attendanceRepository.existsByUserUserIdAndClockoutTimeIsNull(userId)) {
            throw new RuntimeException("すでに出勤しています");
        }

        Attendance attendance = attendanceRepository
                .findByUserUserIdAndClockoutTimeIsNull(userId)
                .orElse(new Attendance());

        if (attendance.getAttendanceId() == null) {
            attendance.setUser(user);
            attendance.setClockinTime(LocalDateTime.now());
        }

        attendance.setWorkPlace(req.getWorkPlace());
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

    // 勤怠状況一覧画面の表示（出勤中1件）
    public Attendance getAttendance(Long userId) {
        return attendanceRepository
                .findByUserUserIdAndClockoutTimeIsNull(userId)
                .orElse(null);
    }

    // =====================================================
    // ★ 追加：同部署ユーザ + 出勤状態取得
    // =====================================================
    public List<UserAttendanceStatusDto> getUsersByDeployWithStatus(Long userId) {

        // 基準ユーザ取得
        User baseUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));

        Integer deployId = baseUser.getDeploy().getDeployId();

        // 同部署ユーザ一覧取得
        List<User> users = userRepository.findByDeployDeployId(deployId);

        // DTOへ変換
        return users.stream().map(user -> {
            UserAttendanceStatusDto dto = new UserAttendanceStatusDto();
            dto.setUserId(user.getUserId());
            dto.setName(user.getName());
            dto.setDeployId(user.getDeploy().getDeployId());
            dto.setMailAddress(user.getMailAddress());
            dto.setTelephoneNum(user.getTelephoneNum());

            boolean clockedIn =
                    attendanceRepository.existsByUserUserIdAndClockoutTimeIsNull(user.getUserId());
            dto.setClockedIn(clockedIn);

            return dto;
        }).collect(Collectors.toList());
    }
}