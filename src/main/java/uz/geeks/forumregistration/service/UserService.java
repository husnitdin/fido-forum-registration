package uz.geeks.forumregistration.service;

import uz.geeks.forumregistration.model.User;
import uz.geeks.forumregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return "fail: user already exists";
        }
        User user = new User(username, password);
        userRepository.save(user);
        return "success: new user added";
    }

    public String login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return "fail: no such user";
        }

        User user = optionalUser.get();
        if (!user.getPassword().equals(password)) {
            return "fail: incorrect password";
        }

        return "success: user logged in";
    }

    public String logout(String username) {
        if (userRepository.findByUsername(username).isEmpty()) {
            return "fail: no such user";
        }
        return "success: user logged out";
    }
}
