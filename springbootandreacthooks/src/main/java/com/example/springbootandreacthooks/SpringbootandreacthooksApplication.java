package com.example.springbootandreacthooks;

import com.example.springbootandreacthooks.model.User;
import com.example.springbootandreacthooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootandreacthooksApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootandreacthooksApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setUsername("Minh");
//        user.setPassword("9898");
//        userRepository.save(user);
//
//
//        User user2 = new User();
//        user2.setUsername("Mai");
//        user2.setPassword("9494");
//        userRepository.save(user2);
    }
}
