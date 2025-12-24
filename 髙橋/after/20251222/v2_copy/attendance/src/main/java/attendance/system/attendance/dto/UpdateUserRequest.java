package attendance.system.attendance.dto;

public class UpdateUserRequest {

    private Long userId;
    private String name;
    private Integer deployId;
    private String workPlace;
    private String mailAddress;
    private String telephoneNum;
    private String emergencyNum;

    public UpdateUserRequest() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getDeployId() { return deployId; }
    public void setDeployId(Integer deployId) { this.deployId = deployId; }

    public String getWorkPlace() { return workPlace; }
    public void setWorkPlace(String workPlace) { this.workPlace = workPlace; }

    public String getMailAddress() { return mailAddress; }
    public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }

    public String getTelephoneNum() { return telephoneNum; }
    public void setTelephoneNum(String telephoneNum) { this.telephoneNum = telephoneNum; }

    public String getEmergencyNum() { return emergencyNum; }
    public void setEmergencyNum(String emergencyNum) { this.emergencyNum = emergencyNum; }
}
