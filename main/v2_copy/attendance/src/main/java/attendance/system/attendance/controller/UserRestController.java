package attendance.system.attendance.controller;

import attendance.system.attendance.dto.RegisterUserRequest;
import attendance.system.attendance.dto.UpdateUserRequest;
import attendance.system.attendance.model.User;
import attendance.system.attendance.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // ユーザー登録
    @PostMapping
    public User register(@RequestBody RegisterUserRequest request) {
        return userService.register(request);
    }

    // ユーザー取得
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    // ユーザー更新
    @PutMapping("/{userId}")
    public User update(
        @PathVariable Long userId,
        @RequestBody UpdateUserRequest request
    ) {
        request.setUserId(userId);
        return userService.updateUser(request);
    }

    // ログイン処理
    @PostMapping("/login")
    public User login(
            @RequestParam Long userId,
            @RequestParam String password
    ) {
        return userService.login(userId, password);
    }


}