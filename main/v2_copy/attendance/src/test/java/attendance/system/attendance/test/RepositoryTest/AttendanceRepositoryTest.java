package attendance.system.attendance.test.RepositoryTest;

import attendance.system.attendance.model.Attendance;
import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.AttendanceRepository;
import attendance.system.attendance.repository.DeployRepository;
import attendance.system.attendance.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AttendanceRepositoryTest {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeployRepository deployRepository;

    @Test
    void testAttendanceSaveAndFind() {

        // ===== Deploy 作成 =====
        Deploy deploy = new Deploy();
        deploy.setDeployId(1); // 手動セット必須
        deploy.setDeployName("営業部");
        Deploy savedDeploy = deployRepository.save(deploy);

        // ===== User 作成 =====
        User user = new User();
        user.setName("山田太郎");
        user.setPassword("pass123");
        user.setMailAddress("test@example.com");
        user.setDeploy(savedDeploy);
        user.setInsertTime(LocalDateTime.now()); // NOT NULL 対策
        user.setUpdateTime(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        // ===== Attendance 作成 =====
        Attendance attendance = new Attendance();
        attendance.setUser(savedUser);
        attendance.setClockinTime(LocalDateTime.now());

        Attendance savedAttendance = attendanceRepository.save(attendance);

        // ===== findById 確認 =====
        Optional<Attendance> found =
                attendanceRepository.findById(savedAttendance.getAttendanceId());

        assertThat(found).isPresent();
        assertThat(found.get().getUser().getUserId())
                .isEqualTo(savedUser.getUserId());
        assertThat(found.get().getClockinTime()).isNotNull();
    }
}
