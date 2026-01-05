package attendance.system.attendance.dto;

import jakarta.validation.constraints.NotNull;

public class ClockInRequest {

    @NotNull
    private Long userId;
    
    @NotNull
    private String workPlace;

    public ClockInRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }
}
