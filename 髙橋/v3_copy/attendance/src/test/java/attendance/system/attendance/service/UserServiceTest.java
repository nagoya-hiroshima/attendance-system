package attendance.system.attendance.service;

import attendance.system.attendance.dto.RegisterUserRequest;
import attendance.system.attendance.dto.UpdateUserRequest;
import attendance.system.attendance.message.MessageCode;
import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.DeployRepository;
import attendance.system.attendance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    DeployRepository deployRepository;

    @InjectMocks
    UserService userService;

    // ===== login =====
    @Test
    void login_正常() {
        User user = new User();
        user.setUserId(1L);
        user.setPassword("pass123");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        User result = userService.login("1", "pass123");

        assertThat(result).isNotNull();
    }

    @Test
    void login_パスワード不一致で例外() {
        User user = new User();
        user.setUserId(1L);
        user.setPassword("pass123");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        assertThatThrownBy(() ->
                userService.login("1", "wrong"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(MessageCode.M0002.getMessage());
    }

    // ===== isFirstRegister =====
    @Test
    void isFirstRegister_true() {
        User user = new User();
        assertThat(userService.isFirstRegister(user)).isTrue();
    }

    // ===== registerUser =====
    @Test
    void registerUser_正常() {
        User user = new User();

        RegisterUserRequest req = new RegisterUserRequest();
        req.setName("山田太郎");
        req.setDeployId(1);
        req.setMailAddress("test@example.com");

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.registerUser(user, req);

        assertThat(result.getName()).isEqualTo("山田太郎");
        assertThat(result.getInsertTime()).isNotNull();
        assertThat(result.getUpdateTime()).isNotNull();
    }

    // ===== getUser =====
    @Test
    void getUser_正常() {
        User user = new User();
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        User result = userService.getUser(1L);

        assertThat(result).isNotNull();
    }

    // ===== updateUser =====
    @Test
    void updateUser_正常() {
        User user = new User();
        user.setName("旧名");

        Deploy deploy = new Deploy();
        deploy.setDeployId(2);

        UpdateUserRequest req = new UpdateUserRequest();
        req.setName("新名");
        req.setDeployId(2);
        req.setMailAddress("new@example.com");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));
        when(deployRepository.findById(2))
                .thenReturn(Optional.of(deploy));
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.updateUser(1L, req);

        assertThat(result.getName()).isEqualTo("新名");
        assertThat(result.getMailAddress()).isEqualTo("new@example.com");
    }
}
