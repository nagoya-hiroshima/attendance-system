package attendance.system.attendance.service.userServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import attendance.system.attendance.model.Attendance;
import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.AttendanceRepository;
import attendance.system.attendance.repository.DeployRepository;
import attendance.system.attendance.repository.UserRepository;
import attendance.system.attendance.service.UserService;
import attendance.system.attendance.dto.UpdateUserRequest;

@ExtendWith(MockitoExtension.class)
class UserServiceUpdateUserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User baseUser;
    private UpdateUserRequest baseReq;

    @Mock
    private DeployRepository deployRepository;

    @Mock
    private AttendanceRepository attendanceRepository;

    @BeforeEach
    void setUp() {
        // 既存ユーザ（更新対象）
        baseUser = new User();
        baseUser.setUserId(1L);
        baseUser.setName("山田太郎");
        baseUser.setWorkPlace("リモート");
        baseUser.setMailAddress("user@gmail.com");
        baseUser.setTelephoneNum("01234567890");
        baseUser.setEmergencyNum("09876543210");

        // 共通リクエスト
        baseReq = new UpdateUserRequest();
        baseReq.setName("山田太郎");
        baseReq.setDeployId(1);
        baseReq.setWorkPlace("リモート");
        baseReq.setMailAddress("user@gmail.com");
        baseReq.setTelephoneNum("01234567890");
        baseReq.setEmergencyNum("09876543210");
    }

    // =====================
    // No.1 正常系
    // =====================
    @Test
    @DisplayName("updateUser: 正常系_正しい入力")
    void update_normal() {

        UpdateUserRequest req = new UpdateUserRequest();
        req.setName("updatedName");
        req.setDeployId(1);
        req.setWorkPlace("リモート");
        req.setMailAddress("user@gmail.com");
        req.setTelephoneNum("09012345678");
        req.setEmergencyNum("09098765432");
    

        User user = new User();
        Deploy deploy = new Deploy();
        Attendance attendance = new Attendance();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));
        when(deployRepository.findById(1))
                .thenReturn(Optional.of(deploy));

        when(attendanceRepository
                .findByUserUserIdAndClockoutTimeIsNull(1L))
                .thenReturn(Optional.of(attendance));

        assertDoesNotThrow(() ->
                userService.updateUser(1L, req)
        );
    }

    // =====================
    // No.2 異常系：電話番号にハイフン
    // =====================
    @Test
    @DisplayName("updateUser: 異常系_電話番号にハイフン")
    void update_telephoneWithHyphen() {
        baseReq.setTelephoneNum("012-3456-7890");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(baseUser));

        assertThrows(RuntimeException.class, () ->
                userService.updateUser(1L, baseReq)
        );
    }

    // =====================
    // No.3 異常系：電話番号に文字
    // =====================
    @Test
    @DisplayName("updateUser: 異常系_電話番号に文字")
    void update_telephoneWithChar() {
        baseReq.setTelephoneNum("aaabbbcccc");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(baseUser));

        assertThrows(RuntimeException.class, () ->
                userService.updateUser(1L, baseReq)
        );
    }

    // =====================
    // No.4 異常系：電話番号が全角
    // =====================
    @Test
    @DisplayName("updateUser: 異常系_電話番号が全角")
    void update_telephoneFullWidth() {
        baseReq.setTelephoneNum("０１２３４５６７８９０");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(baseUser));

        assertThrows(RuntimeException.class, () ->
                userService.updateUser(1L, baseReq)
        );
    }

    // =====================
    // No.5 異常系：緊急連絡先にハイフン
    // =====================
    @Test
    @DisplayName("updateUser: 異常系_緊急連絡先にハイフン")
    void update_emergencyWithHyphen() {
        baseReq.setEmergencyNum("098-7654-3210");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(baseUser));

        assertThrows(RuntimeException.class, () ->
                userService.updateUser(1L, baseReq)
        );
    }

    // =====================
    // No.6 異常系：緊急連絡先に文字
    // =====================
    @Test
    @DisplayName("updateUser: 異常系_緊急連絡先に文字")
    void update_emergencyWithChar() {
        baseReq.setEmergencyNum("aaabbbcccc");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(baseUser));

        assertThrows(RuntimeException.class, () ->
                userService.updateUser(1L, baseReq)
        );
    }

    // =====================
    // No.7 異常系：緊急連絡先が全角
    // =====================
    @Test
    @DisplayName("updateUser: 異常系_緊急連絡先が全角")
    void update_emergencyFullWidth() {
        baseReq.setEmergencyNum("０９８７６５４３２１０");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(baseUser));

        assertThrows(RuntimeException.class, () ->
                userService.updateUser(1L, baseReq)
        );
    }

    // =====================
    // No.8 異常系：メールアドレスに@なし
    // =====================
    @Test
    @DisplayName("updateUser: 異常系_メールアドレスに@がない")
    void update_mailWithoutAt() {
        baseReq.setMailAddress("usergmail.com");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(baseUser));

        assertThrows(RuntimeException.class, () ->
                userService.updateUser(1L, baseReq)
        );
    }
}
