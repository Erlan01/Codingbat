package uz.pdp.service;


import org.springframework.http.ResponseEntity;
import uz.pdp.model.UserDto;

public interface UserService {
    ResponseEntity<?> create(UserDto dto);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getById(Long id);

    ResponseEntity<?> edit(Long id, UserDto dto);

    ResponseEntity<?> delete(Long id);
}
