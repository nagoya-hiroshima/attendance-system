package attendance.system.attendance.service.userServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

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

@ExtendWith(MockitoExtension.class)
class UserServiceLoginTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User baseUser;

    @BeforeEach
    void setUp() {
        // 共通で使う正常ユーザ
        baseUser = new User();
        baseUser.setUserId(123456L);
        baseUser.setPassword("truepassword");
    }

    // No.1 正常：存在するユーザ、正しいパスワード
    @Test
    @DisplayName("login: 正常系_存在するユーザかつ正しいパスワード")
    void login_success() {
        when(userRepository.findById(123456L))
                .thenReturn(Optional.of(baseUser));

        User result = userService.login("123456", "truepassword");

        assertNotNull(result);
    }

    // No.2 異常：存在するユーザ、誤ったパスワード
    @Test
    @DisplayName("login: 異常系_存在するユーザだがパスワード誤り")
    void login_wrongPassword() {
        when(userRepository.findById(123456L))
                .thenReturn(Optional.of(baseUser));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.login("123456", "falsepassword");
        });
    }

    // No.3 異常：存在しないユーザ、正しいパスワード
    @Test
    @DisplayName("login: 異常系_存在しないユーザ")
    void login_userNotFound() {
        when(userRepository.findById(1234567L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.login("1234567", "truepassword");
        });
    }

    // No.4 異常：ユーザID入力（半角英字）
    @Test
    @DisplayName("login: 異常系_ユーザIDが数値でない（半角英字）")
    void login_userIdAlphabet() {
        // 呼び出し前のバリデーションで false になる想定
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.login(null, "truepassword");
        });

        verifyNoInteractions(userRepository);
    }

    // No.5 異常：ユーザID入力（全角文字）
    @Test
    @DisplayName("login: 異常系_ユーザIDが全角文字")
    void login_userIdFullWidth() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.login(null, "truepassword");
        });

        verifyNoInteractions(userRepository);
    }

    // No.6 異常：パスワード入力（全角文字）
    @Test
    @DisplayName("login: 異常系_パスワードが全角文字")
    void login_passwordFullWidth() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.login("123456", "ＴＲＵＥＰＡＳＳＷＯＲＤ");
        });
    }
}
