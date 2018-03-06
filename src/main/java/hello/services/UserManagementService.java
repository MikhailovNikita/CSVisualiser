package hello.services;

import hello.db.datasets.User;
import hello.db.dbhelper.SignInResult;
import hello.db.dbhelper.SignUpResult;
import hello.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserManagementService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public SignUpResult signUpUser(User user) {
        boolean usernameAvailable = Objects.
                isNull(userRepository.findTopByUsername(user.getUsername()));
        boolean emailAvailable = Objects.
                isNull(userRepository.findTopByEmail(user.getEmail()));

        if(!usernameAvailable) return SignUpResult.USERNAME_IS_TAKEN;
        if(!emailAvailable) return SignUpResult.EMAIL_IS_TAKEN;

        userRepository.save(user);
        return SignUpResult.SUCCESS;
    }

    public SignInResult signIn(User user) {
        User userFromDb = userRepository.findTopByUsername(user.getUsername());
        if (Objects.isNull(userFromDb)) {
            return SignInResult.USER_NOT_FOUND;
        }

        if (!userFromDb.getPassword().equals(user.getPassword())) {
            return SignInResult.WRONG_PASSWORD;
        }

        return SignInResult.SUCCESS;
    }


}
