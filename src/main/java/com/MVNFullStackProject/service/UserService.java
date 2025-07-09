package com.MVNFullStackProject.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MVNFullStackProject.dto.UserDTO;
import com.MVNFullStackProject.entity.User;
import com.MVNFullStackProject.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    

    @Autowired
    private PasswordEncoder encoder;

    public User register(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setRole(dto.getRole());
        return repo.save(user);
    }

    public Optional<User> login(UserDTO dto) {
        Optional<User> userOpt = repo.findByEmail(dto.getEmail());
        return userOpt.filter(user -> encoder.matches(dto.getPassword(), user.getPassword()));       
    }

    public String initiatePasswordReset(String email) {
        Optional<User> userOpt = repo.findByEmail(email);
        if (userOpt.isPresent()) {
            String token = UUID.randomUUID().toString();
            User user = userOpt.get();
            user.setResetToken(token);
            repo.save(user);
            return token;
        }
        return null;
    }

    public boolean resetPassword(String token, String newPassword) {
        Optional<User> userOpt = repo.findByResetToken(token);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(encoder.encode(newPassword));
            user.setResetToken(encoder.encode(newPassword));
            repo.save(user);
            return true;
        }
        return false;
    }
}
