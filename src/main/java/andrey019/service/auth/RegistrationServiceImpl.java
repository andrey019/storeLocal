package andrey019.service.auth;


import andrey019.model.dao.TodoList;
import andrey019.model.dao.User;
import andrey019.model.dao.UserConfirmation;
import andrey019.repository.UserConfirmationRepository;
import andrey019.repository.UserRepository;
import andrey019.service.MailService;
import andrey019.service.maintenance.LogService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    private final static String MAIL_SUBJECT = "WunderWaffel registration confirmation";
    private final static String MAIL_TEXT_0 = "<html><body>You were trying to register an account on WunderWaffel, " +
            "to confirm please click on the link below...<br>" +
            "<a href=\"http://wunderwaffel-andrey019.rhcloud.com/auth/confirm?code=";
    private final static String MAIL_TEXT_1 = "\" class=\"btn btn-success\">Confirm</a><br><br>" +
            "If you don't know what's happening, just ignore this message.</body></html>";

    private final static String EMAIL_INCORRECT = "Email is incorrect!";
    private final static String EMAIL_IN_USE = "Email is already in use!";
    private final static String OK = "ok";
    private final static String ERROR = "Registration error!";
    private final static String FIRST_LIST = "my first todo list";

    private final static String SOCIAL_PASSWORD = "socialPassword";
    private final static String SOCIAL_FIRST_NAME = "firstName";
    private final static String SOCIAL_LAST_NAME = "lastName";


    @Autowired
    private UserConfirmationRepository userConfirmationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private LogService logService;


    @Override
    public String preRegistrationCheck(String email) {
        if (!isEmailCorrect(email)) {
            return EMAIL_INCORRECT;
        }
        if (isEmailWaiting(email) || isEmailUsed(email)) {
            return EMAIL_IN_USE;
        }
        return OK;
    }

    @Override
    public String registration(String email, String password, String fName, String lName) {
        String check = preRegistrationCheck(email);
        if (!check.equals(OK)) {
            return check;
        }
        if ( fName.isEmpty() || lName.isEmpty() || (password.length() < 6) || (password.length() > 20) ) {
            return ERROR;
        }
        UserConfirmation userConfirmation = new UserConfirmation();
        userConfirmation.setEmail(email);
        userConfirmation.setfName(fName);
        userConfirmation.setlName(lName);
        userConfirmation.setPassword(passwordEncoder.encode(password));
        userConfirmation.setCode(passwordEncoder.encode(email + System.currentTimeMillis()));
        userConfirmation.setDate(System.currentTimeMillis());
        if (userConfirmationRepository.save(userConfirmation) != null) {
            mailService.sendMail(email, MAIL_SUBJECT, getMailText(userConfirmation.getCode()));
            return OK;
        }
        return ERROR;
    }

    @Override
    public boolean socialRegistration(String signInProvider, String email, String fName, String lName) {
        String check = preRegistrationCheck(email);
        if (!check.equals(OK)) {
            return false;
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(SOCIAL_PASSWORD);
        user.setSignInProvider(signInProvider);
        if (fName != null) {
            user.setfName(fName);
        } else {
            user.setfName(SOCIAL_FIRST_NAME);
        }
        if (lName != null) {
            user.setlName(lName);
        } else {
            user.setlName(SOCIAL_LAST_NAME);
        }
        user = userRepository.save(user);
        if (user == null) {
            return false;
        }
        TodoList todoList = new TodoList();
        todoList.setName(FIRST_LIST);
        user.addTodoList(todoList);
        user.addSharedTodoList(todoList);
        if (userRepository.save(user) != null) {
            logService.newUser(user.toString());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean confirmRegistration(String code) {
        UserConfirmation userConfirmation = userConfirmationRepository.findByCode(code);
        if (userConfirmation == null) {
            return false;
        }
        User user = new User();
        user.setUserFromConfirmation(userConfirmation);
        user = userRepository.save(user);
        if (user == null) {
            return false;
        }
        TodoList todoList = new TodoList();
        todoList.setName(FIRST_LIST);
        user.addTodoList(todoList);
        user.addSharedTodoList(todoList);
        if (userRepository.save(user) != null) {
            logService.newUser(user.toString());
            userConfirmationRepository.delete(userConfirmation);
            return true;
        }
        return false;
    }

    private boolean isEmailCorrect(String email) {
        return emailValidator.isValid(email);
    }

    private boolean isEmailWaiting(String email) {
        if (userConfirmationRepository.findByEmail(email) == null) {
            return false;
        }
        return true;
    }

    private boolean isEmailUsed(String email) {
        if (userRepository.findByEmail(email) == null) {
            return false;
        }
        return true;
    }

    private String getMailText(String code) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(MAIL_TEXT_0);
        stringBuilder.append(code);
        stringBuilder.append(MAIL_TEXT_1);
        return stringBuilder.toString();
    }
}
