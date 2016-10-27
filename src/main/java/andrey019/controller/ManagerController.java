package andrey019.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/managers")
public class ManagerController {


    @RequestMapping({"/", ""})
    @ResponseBody
    public String manager() {
        return "manager ok";
    }
}
