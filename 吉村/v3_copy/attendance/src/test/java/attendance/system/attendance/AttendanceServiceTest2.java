package attendance.system.attendance;

import attendance.system.attendance.dto.UserAttendanceStatusDto;
import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.AttendanceRepository;
import attendance.system.attendance.repository.UserRepository;
import attendance.system.attendance.service.AttendanceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest2 {

    // ===== モック =====
    @Mock
    AttendanceRepository attendanceRepository;

    @Mock
    UserRepository userRepository;

    // ===== テスト対象 =====
    @InjectMocks
    AttendanceService attendanceService;

    // =====================================================
    // 同部署ユーザ + 出勤状態取得（正常系）
    // =====================================================
    @Test
    void getUsersByDeployWithStatus_success() {

        // -------- 基準ユーザ --------
        Deploy deploy = new Deploy();
        deploy.setDeployId(1);

        User baseUser = new User();
        baseUser.setUserId(1L);
        baseUser.setName("山田太郎");
        baseUser.setMailAddress("yamada@test.com");
        baseUser.setTelephoneNum("090-0000-0001");
        baseUser.setDeploy(deploy);

        // -------- 同部署ユーザ --------
        User otherUser = new User();

        otherUser.setUserId(2L);
        otherUser.setName("佐藤花子");
        otherUser.setMailAddress("sato@test.com");
        otherUser.setTelephoneNum("090-0000-0002");

         otherUser.setDeploy(deploy);

        // -------- モック定義 --------
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(baseUser));

        when(userRepository.findByDeployDeployId(1))
                .thenReturn(List.of(baseUser, otherUser));

        when(attendanceRepository
                .existsByUserUserIdAndClockoutTimeIsNull(1L))
                .thenReturn(true);   // 山田は出勤中

        when(attendanceRepository
                .existsByUserUserIdAndClockoutTimeIsNull(2L))
                .thenReturn(false);  // 佐藤は未出勤

        // -------- 実行 --------
        List<UserAttendanceStatusDto> result =
                attendanceService.getUsersByDeployWithStatus(1L);

        // -------- 検証 --------
        assertEquals(2, result.size());

        UserAttendanceStatusDto user1 = result.get(0);
        assertEquals(1L, user1.getUserId());
        assertEquals("山田太郎", user1.getName());
        assertEquals("yamada@test.com", user1.getMailAddress());
        assertEquals("090-0000-0001", user1.getTelephoneNum());
        assertTrue(user1.isClockedIn());

        UserAttendanceStatusDto user2 = result.get(1);
        assertEquals(2L, user2.getUserId());
        assertEquals("佐藤花子", user2.getName());
        assertEquals("sato@test.com", user2.getMailAddress());
        assertEquals("090-0000-0002", user2.getTelephoneNum());
        assertFalse(user2.isClockedIn());
    }
}