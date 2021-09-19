package uz.pdp.service;
import org.springframework.http.ResponseEntity;
import uz.pdp.model.TaskDto;

public interface TaskService {
    ResponseEntity<?> add(TaskDto dto);

    ResponseEntity<?> edit(Long id, TaskDto dto);

    ResponseEntity<?> getById(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> delete(Long id);
}
