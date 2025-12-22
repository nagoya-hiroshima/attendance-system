package attendance.system.attendance.dto;

import jakarta.validation.constraints.NotNull;

public class ClockInRequest {

    @NotNull
    private Long userId;
    private String work_place;

    public ClockInRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWorkPlace() {
        return work_place;
    }

    public void setWorkPlace(String work_place) {
        this.work_place = work_place;
    }
}