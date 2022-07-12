package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.UserRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.UserRoleRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.User;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * @param userName name User
     * @return true if User with name userName present in DB
     */
    public boolean isPresentUser(String userName ) {
        User user = userRepository.getOneByName(userName);
        if(user != null){
            return user.getLogin() != null;
        }
        return false;
    }

    public boolean createUser(String userName, String userPass, long userRole) {
        log.debug("createUser :: login = " + userName);
        log.debug("createUser :: role = " + userRoleRepository.getOneOnlyAuthority(userRole) );
        User user = new User();
        user.setLogin(userName);
        user.setPassword( passwordEncoder.encode( userPass ) );
        user.setAuthority( userRoleRepository.getOneOnlyAuthority(userRole) );

        if(user.validate() && !isPresentUser(userName)){
            userRepository.create(user);
            return true;
        }
        return false;
    }

    public boolean createUser(User user) {
        log.debug("createUser :: input User = " + user.toString());
        if(isPresentUser(user.getLogin())){
            log.debug("createUser :: User is already present ");
            return false;
        }

        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        if (userRepository.getAll().size() > 0) {
            user.setAuthority("ROLE_OFF");
        } else {
            user.setAuthority("ROLE_ADMIN");
        }
        log.debug("createUser :: before save User = " + user.toString());
        userRepository.create(user);
        return true;
    }

    public void updateUser(long userId, String userPass, long userRole) {

        User user = userRepository.getOne(userId);
        user.setAuthority( userRoleRepository.getOneOnlyAuthority(userRole) );

        if(ValidateString.validateString(userPass)){
            user.setPassword( passwordEncoder.encode( userPass ) );
            userRepository.update(user);
        } else {
            userRepository.updateRole(user);
        }
    }

}
