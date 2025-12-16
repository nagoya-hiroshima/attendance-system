package attendance.system.attendance.repository;

import attendance.system.attendance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 今回は標準の findById / save だけ使うため、追加定義は不要
}