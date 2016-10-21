package andrey019.controller;

import andrey019.model.dao.tests.TestModel1;
import andrey019.model.json.JsonTestModel1;
import andrey019.repository.TestModel1Repo;
import andrey019.service.maintenance.LogService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final static String TEXT_UTF8 = "text/plain;charset=UTF-8";
    private final static String JSON_UTF8 = "application/json;charset=UTF-8";

    @Autowired
    private LogService logService;

    @Autowired
    @Qualifier("staticPath")
    private String staticPath;

    @Autowired
    private TestModel1Repo testModel1Repo;


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

    @RequestMapping("/generateTestModel1")
    @ResponseBody
    public String generateTestModel1() {
        TestModel1 testModel1 = new TestModel1();
        String id = RandomStringUtils.random(10, true, true);
        while (testModel1Repo.findByProductId(id) != null) {
            id = RandomStringUtils.random(10, true, true);
        }
        testModel1.setProductId(id);
        testModel1.setNumber(Long.valueOf(RandomStringUtils.random(3, false, true)));
        testModel1.setText(RandomStringUtils.random(50, true, false));
        if (testModel1Repo.save(testModel1) == null) {
            System.out.println("fail");
            return "fail" + testModel1Repo.count();
        } else {
            System.out.println("ok");
            return "ok" + testModel1Repo.count();
        }
    }

    @RequestMapping(value = "/getTestModel1", produces = JSON_UTF8)
    @ResponseBody
    public List<TestModel1> getTestModel1() {
        System.out.println("testModel1 records");
        return testModel1Repo.findAll();
    }

    @RequestMapping("/testModel1")
    public String getTestModel1Page() {
        System.out.println("testModel1 page");
        return "testModel1";
    }

    @RequestMapping("/ok")
    public String responseOk() {
        return "ok";
    }

    private String checkAuthentication(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        }
        return null;
    }
}