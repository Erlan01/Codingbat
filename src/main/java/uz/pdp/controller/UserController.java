package uz.pdp.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.UserDto;
import uz.pdp.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return userService.getAll();
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody UserDto dto) {
        return userService.create(dto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable(value = "id") Long id, @RequestBody UserDto dto) {
        return userService.edit(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return userService.delete(id);
    }

}
