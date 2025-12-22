package attendance.system.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserInfoAcquisitionController {
    @GetMapping("/api/list/auth")
    public String showListPage() {
        return "attendance_list";
    }
}
