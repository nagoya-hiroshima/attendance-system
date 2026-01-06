package attendance.system.attendance.repository;

import attendance.system.attendance.model.Deploy;
import java.util.Optional;

public interface DeployRepository {

    Optional<Deploy> findById(Integer deployId);

}