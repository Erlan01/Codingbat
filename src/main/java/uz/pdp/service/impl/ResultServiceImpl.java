package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.domain.Result;
import uz.pdp.domain.Task;
import uz.pdp.domain.User;
import uz.pdp.model.ResultDto;
import uz.pdp.repository.ResultRepo;
import uz.pdp.repository.TaskRepo;
import uz.pdp.repository.UserRepo;
import uz.pdp.service.ResultService;

import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepo resultRepo;
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    @Autowired
    public ResultServiceImpl(ResultRepo resultRepo, UserRepo userRepo, TaskRepo taskRepo) {
        this.resultRepo = resultRepo;
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }


    @Override
    public ResponseEntity<?> add(ResultDto dto) {
        Optional<User> user = userRepo.findById(dto.getUserId());
        if (!user.isPresent()) {
            return ResponseEntity.status(404).body("User not found");
        }
        Optional<Task> task = taskRepo.findById(dto.getTaskId());
        if (!task.isPresent()) {
            return ResponseEntity.status(404).body("Task not found");
        }
        Result result = Result.builder()
                .task(task.get())
                .user(user.get())
                .responseResult(dto.getResponseResult())
                .isCorrect(dto.isCorrect()).build();
        return ResponseEntity.status(201).body(resultRepo.save(result));
    }

    @Override
    public ResponseEntity<?> edit(Long id, ResultDto dto) {

        Optional<Result> optionalResult = resultRepo.findById(id);
        if (!optionalResult.isPresent()) {
            return ResponseEntity.status(404).body("Result not found");
        }
        Optional<User> user = userRepo.findById(dto.getUserId());
        if (!user.isPresent()) {
            return ResponseEntity.status(404).body("User not found");
        }
        Optional<Task> task = taskRepo.findById(dto.getTaskId());
        if (!task.isPresent()) {
            return ResponseEntity.status(404).body("Task not found");
        }
        Result result = optionalResult.get();
        result.setResponseResult(dto.getResponseResult() != null ? dto.getResponseResult() : result.getResponseResult());
        result.setCorrect(dto.isCorrect());
        result.setTask(task.get());
        result.setUser(user.get());
        return ResponseEntity.status(201).body(resultRepo.save(result));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Result> optionalResult = resultRepo.findById(id);
        if (!optionalResult.isPresent()) {
            return ResponseEntity.status(404).body("Result not found");
        }
        return ResponseEntity.status(200).body(optionalResult.get());
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(resultRepo.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Result> optionalResult = resultRepo.findById(id);
        if (!optionalResult.isPresent()) {
            return ResponseEntity.status(404).body("Result not found");
        }
        resultRepo.delete(optionalResult.get());
        return ResponseEntity.status(200).body("Result deleted");
    }
}
