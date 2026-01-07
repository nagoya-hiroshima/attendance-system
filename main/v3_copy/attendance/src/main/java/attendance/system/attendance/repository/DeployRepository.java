package attendance.system.attendance.repository;

import attendance.system.attendance.model.Deploy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeployRepository extends JpaRepository<Deploy, Integer> {
}