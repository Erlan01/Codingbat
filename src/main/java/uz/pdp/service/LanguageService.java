package uz.pdp.service;


import org.springframework.http.ResponseEntity;
import uz.pdp.model.LanguageDto;

public interface LanguageService {

    ResponseEntity<?> add(LanguageDto dto);

    ResponseEntity<?> edit(Long id, LanguageDto dto);

    ResponseEntity<?> getById(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> delete(Long id);
}
