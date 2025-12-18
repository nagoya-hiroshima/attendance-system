package attendance.system.attendance.dto;

public class ClockOutRequest {
    private Long userId;

    public Long getUserId(){ return userId; }
    public void setUserId(Long userId){ this.userId = userId; }
}