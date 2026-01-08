package attendance.system.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_busho")
public class Deploy {

    @Id
    @Column(name = "deploy_id")
    private Integer deployId;

    @Column(name = "deploy_name")
    private String deployName;

    public Deploy() {}

    public Deploy(Integer deployId, String deployName) {
        this.deployId = deployId;
        this.deployName = deployName;
    }

    public Integer getDeployId() { return deployId; }
    public void setDeployId(Integer deployId) { this.deployId = deployId; }

    public String getDeployName() { return deployName; }
    public void setDeployName(String deployName) { this.deployName = deployName; }
}
