package attendance.system.attendance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_kintai")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "clockin_time")
    private LocalDateTime clockinTime;

    @Column(name = "clockout_time")
    private LocalDateTime clockoutTime;

    @Column(name = "work_place")
    private String workPlace;

    public Attendance() {}

    public Long getAttendanceId() { return attendanceId; }
    public void setAttendanceId(Long attendanceId) { this.attendanceId = attendanceId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getClockinTime() { return clockinTime; }
    public void setClockinTime(LocalDateTime clockinTime) { this.clockinTime = clockinTime; }

    public LocalDateTime getClockoutTime() { return clockoutTime; }
    public void setClockoutTime(LocalDateTime clockoutTime) { this.clockoutTime = clockoutTime; }

    public String getWorkPlace() {return workPlace;}
    public void setWorkPlace(String workPlace) {this.workPlace = workPlace;}
}
