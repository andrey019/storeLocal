package andrey019.service;


import andrey019.model.dao.Role;
import andrey019.model.dao.State;
import andrey019.model.dao.User;
import andrey019.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public boolean createUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.MANAGER.getRole());
        user.setState(State.ACTIVE.getState());
        userRepository.save(user);
        return true;
    }
}
