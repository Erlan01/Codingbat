package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.model.ResultDto;

public interface ResultService {

    ResponseEntity<?> add(ResultDto dto);

    ResponseEntity<?> edit(Long id, ResultDto dto);

    ResponseEntity<?> getById(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> delete(Long id);
}
