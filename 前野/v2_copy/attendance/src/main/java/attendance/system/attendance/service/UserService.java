package attendance.system.attendance.service;

import attendance.system.attendance.message.MessageCode;
import attendance.system.attendance.model.User;
import attendance.system.attendance.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(Long userId, String password) {

        // 未入力チェック
        if (userId == null || password == null || password.isEmpty()) {
            throw new RuntimeException(MessageCode.M0001.getMessage());
        }

        // ユーザー存在チェック
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(MessageCode.M0002.getMessage()));

        // パスワードチェック
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException(MessageCode.M0002.getMessage());
        }

        return user;
    }
}

