package attendance.system.attendance.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import attendance.system.attendance.model.User;
import attendance.system.attendance.service.AttendanceService;
import attendance.system.attendance.service.UserService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserInfoAcquisitionController {
    private final AttendanceService attendanceService;
    private final UserService userService;

    public UserInfoAcquisitionController(
            AttendanceService attendanceService,
            UserService userService
    ) {
        this.attendanceService = attendanceService;
        this.userService = userService;
    }

    @GetMapping("/api/list/auth")
    public String showListPage(HttpSession session, Model model) {

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/api/login/auth";
        }

        // 同部署ユーザ取得（UserService）
        List<User> users =
                userService.getUsersByDeployId(
                        loginUser.getDeploy().getDeployId()
                );

        model.addAttribute("users", users);

        return "attendance_list";
    }
}
