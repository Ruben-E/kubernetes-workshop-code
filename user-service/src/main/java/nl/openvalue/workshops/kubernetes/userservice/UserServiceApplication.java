package nl.openvalue.workshops.kubernetes.userservice;

import nl.openvalue.workshops.kubernetes.userservice.data.UserRepository;
import nl.openvalue.workshops.kubernetes.userservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.UUID;

@SpringBootApplication
public class UserServiceApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @PostConstruct
    public void createUsers() {
        userRepository.save(new User(UUID.randomUUID().toString(), "Foo"));
        userRepository.save(new User(UUID.randomUUID().toString(), "Bar"));
    }
}
