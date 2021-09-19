package uz.pdp.service.impl;

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
        Task task = Task.builder()
                .code(dto.getCode())
                .description(dto.getDescription())
                .language(optionalLanguage.get())
                .solution(dto.getSolution()).build();
        return ResponseEntity.status(201).body(taskRepo.save(task));
    }

    @Override
    public ResponseEntity<?> edit(Long id, TaskDto dto) {
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(404).body("Task not found");
        }
        Optional<Language> optionalLanguage = languageRepo.findById(dto.getLanguageId());
        if (!optionalLanguage.isPresent()) {
            return ResponseEntity.status(404).body("Language not found");
        }
        Task task = optionalTask.get();
        task.setCode(dto.getCode() != null ? dto.getCode() : task.getCode());
        task.setDescription(dto.getDescription() != null ? dto.getDescription() : task.getDescription());
        task.setSolution(dto.getSolution() != null ? dto.getSolution() : task.getSolution());
        task.setLanguage(optionalLanguage.get());
        return ResponseEntity.status(201).body(taskRepo.save(task));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(404).body("Task not found");
        }
        return ResponseEntity.status(200).body(optionalTask.get());
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(taskRepo.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(404).body("Task not found");
        }
        taskRepo.delete(optionalTask.get());
        return ResponseEntity.status(200).body("Task deleted");
    }
}
