package uz.pdp.service.impl;

import com.codingbat.entity.Language;
import com.codingbat.entity.Task;
import com.codingbat.mapper.MapstructMapper;
import com.codingbat.model.TaskDto;
import com.codingbat.repository.LanguageRepo;
import com.codingbat.repository.TaskRepo;
import com.codingbat.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.domain.Language;
import uz.pdp.domain.Task;
import uz.pdp.model.TaskDto;
import uz.pdp.repository.LanguageRepo;
import uz.pdp.repository.TaskRepo;
import uz.pdp.service.TaskService;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {


    private final LanguageRepo languageRepo;
    private final TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(LanguageRepo languageRepo, TaskRepo taskRepo) {
        this.languageRepo = languageRepo;
        this.taskRepo = taskRepo;
    }


    @Override
    public ResponseEntity<?> add(TaskDto dto) {
        Optional<Language> optionalLanguage = languageRepo.findById(dto.getLanguageId());
        if (optionalLanguage.isPresent()) {
            return ResponseEntity.status(404).body("Language not found");
        }
        Task task = Task.builder().build();
        return ResponseEntity.status(201).body(dto);
    }

    @Override
    public ResponseEntity<?> edit(Long id, TaskDto dto) {

        Optional<Task> optionalTask = taskRepo.findById(id);
        if (optionalTask.isEmpty())
            return ResponseEntity.status(404).body("Task not found");
        Optional<Language> optionalLanguage = languageRepo.findById(dto.getLanguageId());
        if (optionalLanguage.isEmpty())
            return ResponseEntity.status(404).body("Language not found");
        taskRepo.save(mapstructMapper.toTask(optionalTask.get(), dto));
        return ResponseEntity.status(201).body(dto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (optionalTask.isEmpty())
            return ResponseEntity.status(404).body("Task not found");
        return ResponseEntity.status(200).body(optionalTask.get());
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(taskRepo.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (optionalTask.isEmpty())
            return ResponseEntity.status(404).body("Task not found");
        taskRepo.delete(optionalTask.get());
        return ResponseEntity.status(200).body(mapstructMapper.toTaskDto(optionalTask.get()));
    }
}
