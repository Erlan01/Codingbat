package uz.pdp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.TaskDto;
import uz.pdp.service.TaskService;

@RestController
@RequestMapping("/api/language")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody TaskDto dto) {
        return taskService.add(dto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable(value = "id") Long id, @RequestBody TaskDto dto) {
        return taskService.edit(id, dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return taskService.getById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return taskService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return taskService.delete(id);
    }

}
