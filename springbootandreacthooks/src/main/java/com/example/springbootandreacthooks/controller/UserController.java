package com.example.springbootandreacthooks.controller;

import com.example.springbootandreacthooks.exception.ResourceNotFoundException;
import com.example.springbootandreacthooks.model.User;
import com.example.springbootandreacthooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception{
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        String pathDirectory = new ClassPathResource("src/main/resources/static/images").getFile().getAbsolutePath();
        Files.copy(file.getInputStream(), Paths.get(pathDirectory+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        return "Update Success";
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id:" + id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id:" + id));
        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        updateUser.setImage(user.getImage());
        userRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id:" + id));
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
