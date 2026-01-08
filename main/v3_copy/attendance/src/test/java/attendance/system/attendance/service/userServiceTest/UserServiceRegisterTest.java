package attendance.system.attendance.service.userServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.UserRepository;
import attendance.system.attendance.service.UserService;
import attendance.system.attendance.dto.RegisterUserRequest;

@ExtendWith(MockitoExtension.class)
class UserServiceRegisterTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private RegisterUserRequest baseReq;
    private User loginUser;

    /**
     * 共通条件（デフォルト入力値）
     */
    @BeforeEach
    void setUp() {
        loginUser = new User();
        loginUser.setUserId(999L);
        loginUser.setName("管理者");

        baseReq = new RegisterUserRequest();
        baseReq.setName("山田太郎");
        baseReq.setDeployId(1);
        baseReq.setWorkPlace("リモート");
        baseReq.setMailAddress("user@gmail.com");
        baseReq.setTelephoneNum("01234567890");
        baseReq.setEmergencyNum("09876543210");
    }

    // --------------------------------------------------
    // No.1 正常：ユーザ情報を正しい書式で入力
    // --------------------------------------------------
    @Test
    @DisplayName("registerUser: 正常系_正しい入力")
    void register_success() {
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> userService.registerUser(loginUser, baseReq));
    }

    // --------------------------------------------------
    // No.2 異常：電話番号にハイフン
    // --------------------------------------------------
    @Test
    @DisplayName("registerUser: 異常系_電話番号にハイフン")
    void register_telephoneWithHyphen() {
        baseReq.setTelephoneNum("012-3456-7890");

        assertThrows(RuntimeException.class,
                () -> userService.registerUser(loginUser, baseReq));
    }

    // --------------------------------------------------
    // No.3 異常：電話番号に文字
    // --------------------------------------------------
    @Test
    @DisplayName("registerUser: 異常系_電話番号に文字")
    void register_telephoneWithChar() {
        baseReq.setTelephoneNum("aaabbbcccc");

        assertThrows(RuntimeException.class,
                () -> userService.registerUser(loginUser, baseReq));
    }

    // --------------------------------------------------
    // No.4 異常：電話番号が全角
    // --------------------------------------------------
    @Test
    @DisplayName("registerUser: 異常系_電話番号が全角")
    void register_telephoneFullWidth() {
        baseReq.setTelephoneNum("０１２３４５６７８９０");

        assertThrows(RuntimeException.class,
                () -> userService.registerUser(loginUser, baseReq));
    }

    // --------------------------------------------------
    // No.5 異常：緊急連絡先にハイフン
    // --------------------------------------------------
    @Test
    @DisplayName("registerUser: 異常系_緊急連絡先にハイフン")
    void register_emergencyWithHyphen() {
        baseReq.setEmergencyNum("098-7654-3210");

        assertThrows(RuntimeException.class,
                () -> userService.registerUser(loginUser, baseReq));
    }

    // --------------------------------------------------
    // No.6 異常：緊急連絡先に文字
    // --------------------------------------------------
    @Test
    @DisplayName("registerUser: 異常系_緊急連絡先に文字")
    void register_emergencyWithChar() {
        baseReq.setEmergencyNum("aaabbbcccc");

        assertThrows(RuntimeException.class,
                () -> userService.registerUser(loginUser, baseReq));
    }

    // --------------------------------------------------
    // No.7 異常：緊急連絡先が全角
    // --------------------------------------------------
    @Test
    @DisplayName("registerUser: 異常系_緊急連絡先が全角")
    void register_emergencyFullWidth() {
        baseReq.setEmergencyNum("０１２３４５６７８９０");

        assertThrows(RuntimeException.class,
                () -> userService.registerUser(loginUser, baseReq));
    }

    // --------------------------------------------------
    // No.8 異常：@の無いメールアドレス
    // --------------------------------------------------
    @Test
    @DisplayName("registerUser: 異常系_メールアドレス形式不正")
    void register_mailInvalid() {
        baseReq.setMailAddress("usergmail.com");

        assertThrows(RuntimeException.class,
                () -> userService.registerUser(loginUser, baseReq));
    }
}
