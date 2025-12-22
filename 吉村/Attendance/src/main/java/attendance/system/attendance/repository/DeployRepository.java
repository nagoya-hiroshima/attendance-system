package attendance.system.attendance.repository;

import attendance.system.attendance.model.Deploy;

public interface DeployRepository {

    Deploy findById(Integer deployId);

}