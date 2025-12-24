package attendance.system.attendance.test.RepositoryTest;

import attendance.system.attendance.model.User;
import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.repository.UserRepository;
import attendance.system.attendance.repository.DeployRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // ★追加
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeployRepository deployRepository;

    @Test
    void testUserSaveAndFindAndDelete() {

        // 部署を先に作る（Deploy は外部キーなので）
        Deploy deploy = new Deploy();
        deploy.setDeployId(1);          // ★手動でIDセット（必須）
        deploy.setDeployName("営業部");
        Deploy savedDeploy = deployRepository.save(deploy);

        // User を作成
        User user = new User();
        user.setName("山田太郎");
        user.setPassword("pass123");
        user.setMailAddress("test@example.com");
        user.setDeploy(savedDeploy);

        User savedUser = userRepository.save(user);

        // findById の確認
        Optional<User> found = userRepository.findById(savedUser.getUserId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("山田太郎");

        // delete の確認
        userRepository.delete(found.get());

        Optional<User> afterDelete = userRepository.findById(savedUser.getUserId());
        assertThat(afterDelete).isEmpty();
    }
}
