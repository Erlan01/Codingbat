package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.domain.Language;
import uz.pdp.model.LanguageDto;
import uz.pdp.repository.LanguageRepo;
import uz.pdp.service.LanguageService;

import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepo languageRepo;

    @Autowired
    public LanguageServiceImpl(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
    }


    @Override
    public ResponseEntity<?> add(LanguageDto dto) {
        if (languageRepo.existsByName(dto.getName())) {
            return ResponseEntity.status(409).body("Name already exists");
        }
        Language language = Language.builder()
                .name(dto.getName())
                .status(dto.isStatus()).build();

        return ResponseEntity.status(201).body(languageRepo.save(language));
    }

    @Override
    public ResponseEntity<?> edit(Long id, LanguageDto dto) {
        Optional<Language> optionalLanguage = languageRepo.findById(id);
        if (!optionalLanguage.isPresent())
            return ResponseEntity.status(404).body("Language not found");
        if (languageRepo.existsByName(dto.getName())) {
            return ResponseEntity.status(409).body("Name already exists");
        }
        Language language = optionalLanguage.get();
        language.setName(dto.getName());
        language.setStatus(dto.isStatus());
        return ResponseEntity.status(201).body(languageRepo.save(language));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Language> optionalLanguage = languageRepo.findById(id);
        if (!optionalLanguage.isPresent()) {
            return ResponseEntity.status(404).body("Language not found");
        }
        return ResponseEntity.status(200).body(optionalLanguage.get());
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(languageRepo.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Language> optionalLanguage = languageRepo.findById(id);
        if (!optionalLanguage.isPresent()) {
            return ResponseEntity.status(404).body("Language not found");
        }
        languageRepo.delete(optionalLanguage.get());
        return ResponseEntity.status(200).body("Language deleted");
    }
}
