package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.UserRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.User;

@Service
public class UserService {

    private static final LoggerMsgService logger = new LoggerMsgService(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(User user) {
        logger.msgDebug("createUser :: input User = " + user.toString());
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );

        if (userRepository.getAll().size() > 0) {
            user.setAuthority("ROLE_OFF");
        } else {
            user.setAuthority("ROLE_ADMIN");
        }

        logger.msgDebug("createUser :: before save User = " + user.toString());
        userRepository.create(user);
    }

}
