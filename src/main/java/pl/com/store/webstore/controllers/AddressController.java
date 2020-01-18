package pl.com.store.webstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.services.AddressService;
import pl.com.store.webstore.services.implementations.AddressServiceImp;
import pl.com.store.webstore.services.implementations.mappers.AddressMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

    private AddressService service;

    public AddressController(AddressServiceImp service) {
        this.service = service;
    }

    @PostMapping
    public Long addAddress(@RequestBody AddressDto addressDto, @RequestBody CustomerDto customerDto) {
        return service.addAddress(addressDto,customerDto);
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(AddressMapper::mapToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(AddressMapper.mapToDto(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto) throws Exception {
        return ResponseEntity.ok(AddressMapper.mapToDto(service.updateAddress(id, addressDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) throws Exception {
        service.deleteById(id);
        return ResponseEntity.ok("Deleted !");
    }
}
