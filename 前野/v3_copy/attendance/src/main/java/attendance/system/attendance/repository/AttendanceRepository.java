package attendance.system.attendance.repository;

import attendance.system.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByUserUserIdAndClockoutTimeIsNull(Long userId);
    boolean existsByUserUserIdAndClockoutTimeIsNull(Long userId);
}
