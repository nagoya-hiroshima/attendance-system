package attendance.system.attendance.dto;

import jakarta.validation.constraints.NotNull;

public class ClockInRequest {

    @NotNull
    private Long userId;

    public ClockInRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}