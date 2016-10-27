package andrey019.controller;


import andrey019.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping({"/", ""})
    public String adminPage() {
        return "admin";
    }

    @RequestMapping(value = "/createManager", method = RequestMethod.GET)
    public String createManagerPage() {
        return "createManager";
    }

    @RequestMapping(value = "/createManager", method = RequestMethod.POST)
    public String createManager(@RequestParam("username") String username,
                                @RequestParam("password") String password) {
        System.out.println(username + ", " + password);
        if (adminService.createUser(username, password)) {
            return "redirect:/admin/createManager?ok";
        }
        return "redirect:/admin/createManager?error";
    }
}
