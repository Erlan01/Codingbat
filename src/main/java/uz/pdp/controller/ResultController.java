package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.ResultDto;
import uz.pdp.service.ResultService;

@RestController
@RequestMapping("/api/result")
public class ResultController {

    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ResultDto dto) {
        return resultService.add(dto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable(value = "id") Long id, @RequestBody ResultDto dto) {
        return resultService.edit(id, dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return resultService.getById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return resultService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return resultService.delete(id);
    }

}
