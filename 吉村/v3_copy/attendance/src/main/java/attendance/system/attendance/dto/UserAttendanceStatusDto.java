package attendance.system.attendance.dto;

public class UserAttendanceStatusDto {

    private Long userId;
    private String name;
    private Integer deployId;
    private String mailAddress;
    private String telephoneNum;
    private boolean clockedIn;

    public UserAttendanceStatusDto() {}

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeployId() {
        return deployId;
    }
    public void setDeployId(Integer deployId) {
        this.deployId = deployId;
    }

    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }
    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public boolean isClockedIn() {
        return clockedIn;
    }
    public void setClockedIn(boolean clockedIn) {
        this.clockedIn = clockedIn;
    }
}