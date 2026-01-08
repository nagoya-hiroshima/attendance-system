package attendance.system.attendance;

import attendance.system.attendance.dto.ClockInRequest;
import attendance.system.attendance.dto.ClockOutRequest;
import attendance.system.attendance.model.Attendance;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.AttendanceRepository;
import attendance.system.attendance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import attendance.system.attendance.service.AttendanceService;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // ← Mockitoを使う宣言
class AttendanceServiceTest {

    // ===== モック（偽物）=====
    @Mock
    AttendanceRepository attendanceRepository;

    @Mock
    UserRepository userRepository;

    // ===== テスト対象（モックが注入される）=====
    @InjectMocks
    AttendanceService attendanceService;

    // ===============================
    // 出勤打刻：正常系
    // ===============================
    @Test
    void clockIn_success() {
        ClockInRequest req = new ClockInRequest();
        req.setUserId(1L);

        User user = new User();

        // モックの動作定義
        when(attendanceRepository.existsByUserUserIdAndClockoutTimeIsNull(1L))
                .thenReturn(false);
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(attendanceRepository
                .findByUserUserIdAndClockoutTimeIsNull(1L))
                .thenReturn(Optional.empty());

        when(attendanceRepository.save(any(Attendance.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Attendance result = attendanceService.clockIn(req);

        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertNotNull(result.getClockinTime());
    }

    // ===============================
    // 出勤打刻：ユーザが存在しない
    // ===============================
    @Test
    void clockIn_userNotFound() {
        ClockInRequest req = new ClockInRequest();
        req.setUserId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> attendanceService.clockIn(req)
        );

        assertEquals("ユーザーが見つかりません", ex.getMessage());
    }

    // ===============================
    // 出勤打刻：すでに出勤中
    // ===============================
    @Test
    void clockIn_alreadyClockedIn() {
        ClockInRequest req = new ClockInRequest();
        req.setUserId(1L);

        User user = new User();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(attendanceRepository.existsByUserUserIdAndClockoutTimeIsNull(1L))
                .thenReturn(true);


        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> attendanceService.clockIn(req)
        );

        assertEquals("すでに出勤しています", ex.getMessage());
    }

    // ===============================
    // 退勤打刻：正常系
    // ===============================
    @Test
    void clockOut_success() {
        ClockOutRequest req = new ClockOutRequest();
        req.setUserId(1L);

        Attendance attendance = new Attendance();

        when(attendanceRepository
                .findByUserUserIdAndClockoutTimeIsNull(1L))
                .thenReturn(Optional.of(attendance));

        when(attendanceRepository.save(attendance))
                .thenReturn(attendance);

        Attendance result = attendanceService.clockOut(req);

        assertNotNull(result.getClockoutTime());
    }

    // ===============================
    // 退勤打刻：対象データなし
    // ===============================
    @Test
    void clockOut_notFound() {
        ClockOutRequest req = new ClockOutRequest();
        req.setUserId(1L);

        when(attendanceRepository
                .findByUserUserIdAndClockoutTimeIsNull(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> attendanceService.clockOut(req)
        );

        assertEquals("退勤対象の出勤が存在しません", ex.getMessage());
    }
}