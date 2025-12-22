package attendance.system.attendance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_KINTAI")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "user_id") // 外部キーと完全一致
    private User user;

    @Column(name = "clockin_time")
    private LocalDateTime clockinTime;

    @Column(name = "clockout_time")
    private LocalDateTime clockoutTime;

    public Attendance() {}

    public Long getAttendanceId() { return attendanceId; }
    public void setAttendanceId(Long attendanceId) { this.attendanceId = attendanceId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getClockinTime() { return clockinTime; }
    public void setClockinTime(LocalDateTime clockinTime) { this.clockinTime = clockinTime; }

    public LocalDateTime getClockoutTime() { return clockoutTime; }
    public void setClockoutTime(LocalDateTime clockoutTime) { this.clockoutTime = clockoutTime; }

    public void setWorkPlace(String workPlace) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setWorkPlace'");
    }
}
