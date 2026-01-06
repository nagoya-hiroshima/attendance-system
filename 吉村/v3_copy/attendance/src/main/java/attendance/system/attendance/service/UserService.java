package attendance.system.attendance.service;

import attendance.system.attendance.dto.RegisterUserRequest;
import attendance.system.attendance.dto.UpdateUserRequest;
import attendance.system.attendance.message.MessageCode;
import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.DeployRepository;
import attendance.system.attendance.repository.UserRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DeployRepository deployRepository;

    public UserService(UserRepository userRepository,
                       DeployRepository deployRepository) {
        this.userRepository = userRepository;
        this.deployRepository = deployRepository;
    }

    // ログイン処理
    public User login(String userId, String password) {

        // 未入力チェック
        if (userId == null || password == null || password.isEmpty()) {
            throw new RuntimeException(MessageCode.M0001.getMessage());
        }
        // ID形式チェック（半角数字のみ）
        if (!userId.matches("\\d+")) {
            throw new RuntimeException(MessageCode.M0003.getMessage());
        }
        // パスワード形式チェック（半角英数字）
        if (!password.matches("[a-zA-Z0-9]+")) {
            throw new RuntimeException(MessageCode.M0004.getMessage());
        }

        // String → Long 変換
        Long userIdLong = Long.valueOf(userId);

        // ユーザー存在チェック
        User user = userRepository.findById(userIdLong)
                .orElseThrow(() ->
                        new RuntimeException(MessageCode.M0002.getMessage()));

        // パスワードチェック
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException(MessageCode.M0002.getMessage());
        }

        return user;
    }

    // 初回登録判定
    public boolean isFirstRegister(User user) {
        // insert_time が未設定なら初回登録
        return user.getInsertTime() == null;
    }

    // ユーザ登録処理
    @Transactional
    public User registerUser(User user, RegisterUserRequest req) {
        // 未入力チェック
        if (req.getName() == null || req.getName().isBlank()
            || req.getDeployId() == null
            || req.getMailAddress() == null || req.getMailAddress().isBlank()) {
            throw new RuntimeException(MessageCode.M0009.getMessage());
        }
        // 部署IDチェック
        if (!String.valueOf(req.getDeployId()).matches("\\d+")) {
            throw new RuntimeException(MessageCode.M0007.getMessage());
        }
        // メール形式チェック
        if (!req.getMailAddress().matches("^[^@]+@[^@]+\\.[^@]+$")) {
            throw new RuntimeException(MessageCode.M0008.getMessage());
        }

        user.setName(req.getName());

        // 部署ID → Deploy エンティティ
        Deploy deploy = new Deploy();
        deploy.setDeployId(req.getDeployId());
        user.setDeploy(deploy);
        user.setMailAddress(req.getMailAddress());
        user.setTelephoneNum(req.getTelephoneNum());
        user.setEmergencyNum(req.getEmergencyNum());

        // 初回登録のみ insertTime
        if (user.getInsertTime() == null) {
            user.setInsertTime(LocalDateTime.now());
        }

        // 更新時刻は毎回
        user.setUpdateTime(LocalDateTime.now());
        return userRepository.save(user);
    }

    // ユーザ情報取得
    public User getUser(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));
    }

    // ユーザ情報更新処理
    @Transactional
    public User updateUser(Long userId, UpdateUserRequest req) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

        // 名前（必須）
        if (req.getName() == null || req.getName().isBlank()) {
            throw new RuntimeException(MessageCode.M0009.getMessage());
        }
        user.setName(req.getName());
        // 部署（変更があったときだけ）
        if (req.getDeployId() != null) {
            Deploy deploy = deployRepository.findById(req.getDeployId())
                .orElseThrow(() -> new RuntimeException("部署が存在しません"));
            user.setDeploy(deploy);
        }

        // メール（入力があるときだけ）
        if (req.getMailAddress() != null && !req.getMailAddress().isBlank()) {
            if (!req.getMailAddress().matches("^[^@]+@[^@]+\\.[^@]+$")) {
                throw new RuntimeException(MessageCode.M0016.getMessage());
            }
            user.setMailAddress(req.getMailAddress());
        }

        user.setWorkPlace(req.getWorkPlace());
        user.setTelephoneNum(req.getTelephoneNum());
        user.setEmergencyNum(req.getEmergencyNum());

        return userRepository.save(user);
    }
    
    // 同部署ユーザ取得
    public List<User> getUsersByDeployId(Integer deployId) {
        return userRepository.findByDeployDeployId(deployId);
    }
}

