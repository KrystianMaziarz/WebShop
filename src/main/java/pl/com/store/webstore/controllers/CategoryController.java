package pl.com.store.webstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.store.webstore.controllers.dtos.CategoryDto;
import pl.com.store.webstore.services.CategoryService;
import pl.com.store.webstore.services.implementations.CategoryServiceImp;
import pl.com.store.webstore.services.implementations.mappers.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public Long addCategory(@RequestBody CategoryDto categoryDto) {
        return service.addCategory(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(CategoryMapper::mapToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(CategoryMapper.mapToDto(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCustomer(@PathVariable Long id, @RequestBody CategoryDto categoryDto) throws Exception {
        return ResponseEntity.ok(CategoryMapper.mapToDto(service.updateCategory(id, categoryDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws Exception {
        service.deleteById(id);
        return ResponseEntity.ok("Deleted !");
    }
}
