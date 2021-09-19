package uz.pdp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.LanguageDto;
import uz.pdp.service.LanguageService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody LanguageDto dto) {
        return languageService.add(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> edit(@PathVariable(value = "id") Long id, @RequestBody LanguageDto dto) {
        return languageService.edit(id, dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return languageService.getById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return languageService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return languageService.delete(id);
    }

}
