package attendance.system.attendance.repository;

import attendance.system.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // ユーザID + 勤務日で勤怠取得（出勤・参照用）
    Optional<Attendance> findByUserUserIdAndWorkDate(Long userId, LocalDate workDate);

    // 退勤未打刻の勤怠取得（日付またぎ退勤対応）
    Optional<Attendance> findByUserUserIdAndClockoutTimeIsNull(Long userId);
}