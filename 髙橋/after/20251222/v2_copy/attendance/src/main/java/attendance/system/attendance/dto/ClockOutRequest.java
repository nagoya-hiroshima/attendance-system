package attendance.system.attendance.dto;

import jakarta.validation.constraints.NotNull;

public class ClockOutRequest {

    @NotNull
    private Long userId;

    public ClockOutRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}