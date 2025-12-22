package attendance.system.attendance.service;

import attendance.system.attendance.dto.RegisterUserRequest;
import attendance.system.attendance.dto.UpdateUserRequest;
import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.DeployRepository;
import attendance.system.attendance.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DeployRepository deployRepository;

    public UserService(
            UserRepository userRepository,
            DeployRepository deployRepository
    ) {
        this.userRepository = userRepository;
        this.deployRepository = deployRepository;
    }

    public User register(RegisterUserRequest request) {

        Deploy deploy = deployRepository.findById(request.getDeployId())
                .orElseThrow(() -> new RuntimeException("部署が存在しません"));

        User user = new User();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setDeploy(deploy);
        user.setMailAddress(request.getMailAddress());

        return userRepository.save(user);
    }

    // ユーザー登録
    public User registerUser(RegisterUserRequest req) {

        Deploy deploy = deployRepository.findById(req.getDeployId())
                .orElseThrow(() -> new RuntimeException("部署が存在しません"));

        User user = new User();
        user.setName(req.getName());
        user.setPassword(req.getPassword());
        user.setDeploy(deploy);
        user.setWorkPlace(req.getWorkPlace());
        user.setMailAddress(req.getMailAddress());
        user.setTelephoneNum(req.getTelephoneNum());
        user.setEmergencyNum(req.getEmergencyNum());

        return userRepository.save(user);
    }

    // ユーザー更新
    public User updateUser(UpdateUserRequest req) {

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

        Deploy deploy = deployRepository.findById(req.getDeployId())
                .orElseThrow(() -> new RuntimeException("部署が存在しません"));

        user.setName(req.getName());
        user.setDeploy(deploy);
        user.setWorkPlace(req.getWorkPlace());
        user.setMailAddress(req.getMailAddress());
        user.setTelephoneNum(req.getTelephoneNum());
        user.setEmergencyNum(req.getEmergencyNum());

        return userRepository.save(user);
    }

    // ユーザー取得
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));
    }

    // ログイン（超シンプル版）
    public User login(Long userId, String password) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("パスワードが違います");
        }

        return user;
    }
}
