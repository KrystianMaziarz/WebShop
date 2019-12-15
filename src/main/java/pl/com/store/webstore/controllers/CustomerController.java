package pl.com.store.webstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.store.webstore.services.implementations.CustomerServiceImp;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

    private CustomerServiceImp service;

    public CustomerController(CustomerServiceImp service) {
        this.service = service;
    }

    @PostMapping
    public Long addUser(@RequestBody UserDto userDto) throws PasswordStrengthException, WrongEmailFormatException {
        return service.addUser(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(UserMapper::mapToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(UserMapper.mapToDto(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(UserMapper.mapToDto(service.updateUser(id, userDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws Exception {
        service.deleteById(id);
        return ResponseEntity.ok("Deleted !");
    }
}
