package attendance.system.attendance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "deploy_id") // 外部キーと完全一致
    private Deploy deploy;

    @Column(name = "work_place")
    private String workPlace;

    @Column(name = "mail_address")
    private String mailAddress;

    @Column(name = "telephone_num")
    private String telephoneNum;

    @Column(name = "emergency_num")
    private String emergencyNum;

    @Column(name = "insert_time", updatable = false)
    private LocalDateTime insertTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public User() {}

    // Getter / Setter
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Deploy getDeploy() { return deploy; }
    public void setDeploy(Deploy deploy) { this.deploy = deploy; }

    public String getWorkPlace() { return workPlace; }
    public void setWorkPlace(String workPlace) { this.workPlace = workPlace; }

    public String getMailAddress() { return mailAddress; }
    public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }

    public String getTelephoneNum() { return telephoneNum; }
    public void setTelephoneNum(String telephoneNum) { this.telephoneNum = telephoneNum; }

    public String getEmergencyNum() { return emergencyNum; }
    public void setEmergencyNum(String emergencyNum) { this.emergencyNum = emergencyNum; }

    public LocalDateTime getInsertTime() { return insertTime; }
    public void setInsertTime(LocalDateTime insertTime) { this.insertTime = insertTime; }

    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}
