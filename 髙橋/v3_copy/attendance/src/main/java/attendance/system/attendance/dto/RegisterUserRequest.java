package attendance.system.attendance.dto;

public class RegisterUserRequest {

    private String name;
    private String password;
    private Integer deployId;
    private String workPlace;
    private String mailAddress;
    private String telephoneNum;
    private String emergencyNum;

    public RegisterUserRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

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
