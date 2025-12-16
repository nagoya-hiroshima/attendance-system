package attendance.system.attendance.repository;

import attendance.system.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long>{
    //ユーザID+日付で勤怠レコードを取得(出勤/退勤で必須)
    Optional<Attendance> findByUserUserIdAndClockoutTimeIsNull(Long userId);

    Optional<Attendance> findByUserUserIdAndWorkDate(Long userId, LocalDate workDate);   
}