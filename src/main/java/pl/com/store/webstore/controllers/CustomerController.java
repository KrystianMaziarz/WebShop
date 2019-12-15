package pl.com.store.webstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.services.implementations.CustomerServiceImp;
import pl.com.store.webstore.services.implementations.mappers.CustomerMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

    private CustomerServiceImp service;

    public CustomerController(CustomerServiceImp service) {
        this.service = service;
    }

    @PostMapping
    public Long addCustomer(@RequestBody CustomerDto customerDto) {
        return service.addCustomer(customerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(CustomerMapper::mapToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(CustomerMapper.mapToDto(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) throws Exception {
        return ResponseEntity.ok(CustomerMapper.mapToDto(service.updateCustomer(id, customerDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) throws Exception {
        service.deleteById(id);
        return ResponseEntity.ok("Deleted !");
    }
}
