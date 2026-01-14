package attendance.system.attendance.controller;

import attendance.system.attendance.dto.RegisterUserRequest;
import attendance.system.attendance.dto.UpdateUserRequest;
import attendance.system.attendance.message.MessageCode;
import attendance.system.attendance.model.User;
import attendance.system.attendance.service.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 既存：ユーザ情報表示画面
    @GetMapping("/api/user/auth")
    public String showUserInfoPage(HttpSession session, Model model) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
        return "redirect:/api/login/auth";
        }

        User user = userService.getUser(loginUser.getUserId());
        model.addAttribute("user", user);

        return "user_info";
    }

    // ユーザ情報【登録】画面表示
    @GetMapping("/api/register/auth")
    public String showUserRegisterPage(HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/api/login/auth";
        }
        // すでに登録済みなら戻す
        if (!userService.isFirstRegister(loginUser)) {
            return "redirect:/api/attendance/auth";
        }

        return "user_register";
    }

    // ユーザ情報【登録】処理
    @PostMapping("/api/register/auth")
    public String registerUser(
            RegisterUserRequest req,
            HttpSession session
    ) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/api/login/auth";
        }

        User updatedUser = userService.registerUser(loginUser, req);

        // セッション更新
        session.setAttribute("loginUser", updatedUser);

        return "redirect:/api/attendance/auth";
    }

    // ユーザ情報【更新】処理
    @PostMapping("/api/user/update")
    @ResponseBody
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest req,
                                             HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(MessageCode.M0002.getMessage());
        }
        try {
            userService.updateUser(loginUser.getUserId(), req);

            // セッション更新
            session.setAttribute(
                    "loginUser",
                    userService.getUser(loginUser.getUserId())
            );

            return ResponseEntity.ok(
                    MessageCode.M0014.getMessage()
            );

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
