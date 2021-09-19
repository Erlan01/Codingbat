package uz.pdp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.domain.User;
import uz.pdp.model.UserDto;
import uz.pdp.repository.UserRepo;
import uz.pdp.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public ResponseEntity<?> create(UserDto dto) {
        if (userRepo.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(409).body("Email already exists");
        }
        User user = User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword()).build();
        return ResponseEntity.status(201).body(userRepo.save(user));
    }

    @Override
    public ResponseEntity<?> edit(Long id, UserDto dto) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.status(404).body("User not found");
        }
        if (userRepo.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(409).body("Email already exists");
        }
        User user = optionalUser.get();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword() != null ? dto.getPassword() : user.getPassword());
        return ResponseEntity.status(201).body(userRepo.save(user));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.status(200).body(optionalUser.get());
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(userRepo.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent())
            return ResponseEntity.status(404).body("User not found");
        userRepo.delete(optionalUser.get());
        return ResponseEntity.status(200).body("User deleted");
    }
}
