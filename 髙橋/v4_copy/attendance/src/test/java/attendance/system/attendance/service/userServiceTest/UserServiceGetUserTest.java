package attendance.system.attendance.service.userServiceTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.DeployRepository;
import attendance.system.attendance.repository.UserRepository;
import attendance.system.attendance.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceGetUserTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DeployRepository deployRepository;

    private User user;
    private Deploy deploy;

    @BeforeEach
    void setUp() {
        // 部署データ
        deploy = new Deploy();
        deploy.setDeployId(1);
        deploy.setDeployName("開発部");

        // ユーザデータ
        user = new User();
        user.setUserId(123456L);
        user.setName("testuser");
        user.setPassword("password");
        user.setTelephoneNum("09012345678");
        user.setEmergencyNum("09098765432");
        user.setMailAddress("test@example.com");
        user.setDeploy(deploy);
    }

    /**
     * 正常系
     * 指定したユーザIDのユーザ情報を取得できること
     */
    @Test
    void getUser_normal() {

        // --- モックの振る舞い定義 ---
        when(userRepository.findById(123456L))
                .thenReturn(Optional.of(user));

        // --- 実行 & 検証 ---
        assertDoesNotThrow(() -> {
            User result = userService.getUser(123456L);

            assertEquals(123456L, result.getUserId());
            assertEquals("testuser", result.getName());
            assertEquals("09012345678", result.getTelephoneNum());
            assertEquals("09098765432", result.getEmergencyNum());
            assertEquals("test@example.com", result.getMailAddress());
            assertEquals("開発部", result.getDeploy().getDeployName());
        });
    }
}