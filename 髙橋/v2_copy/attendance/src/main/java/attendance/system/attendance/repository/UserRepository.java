package attendance.system.attendance.repository;

import attendance.system.attendance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
