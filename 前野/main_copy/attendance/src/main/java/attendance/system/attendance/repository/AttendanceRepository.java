package attendance.system.attendance.repository;

import attendance.system.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // 退勤していない勤怠を取得（出勤中）
    Optional<Attendance> findByUserUserIdAndClockoutTimeIsNull(Long userId);
}
