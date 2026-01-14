package attendance.system.attendance.repository;

import attendance.system.attendance.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByDeployDeployId(Integer deployId);
}

