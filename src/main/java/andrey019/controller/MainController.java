package andrey019.controller;

import andrey019.service.maintenance.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private LogService logService;

    @Autowired
    @Qualifier("staticPath")
    private String staticPath;


	@RequestMapping("/")
    @ResponseBody
	public String listAdvs(HttpServletRequest request) {
		logService.accessToPage("main, ip = " + request.getRemoteAddr());
        System.out.println(staticPath);
		return staticPath;
	}

	@RequestMapping("/favicon.ico")
    public String favicon() {
        logService.accessToPage("favicon.ico");
        return "forward:/resources/images/favicon.ico";
    }

    private String checkAuthentication(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        }
        return null;
    }
}