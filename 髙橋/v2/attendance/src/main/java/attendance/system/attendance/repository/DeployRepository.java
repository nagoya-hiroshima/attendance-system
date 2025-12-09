package attendance.system.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import attendance.system.attendance.model.Deploy;

@Repository
public interface DeployRepository extends JpaRepository<Deploy, Integer> {
}