package attendance.system.attendance.service;

import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.repository.DeployRepository;

public class DeployService {

    private final DeployRepository deployRepository;

    public DeployService(DeployRepository deployRepository) {
        this.deployRepository = deployRepository;
    }

    public Deploy getDeploy(Integer deployId) {
    return deployRepository.findById(deployId)
            .orElseThrow(() -> new RuntimeException("部署が存在しません"));
    }
}