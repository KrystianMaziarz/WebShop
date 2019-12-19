package pl.com.store.webstore.controllers;

import com.google.common.collect.Maps;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.services.implementations.CustomerServiceImp;
import pl.com.store.webstore.services.implementations.mappers.CustomerMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private CustomerServiceImp service;

    public CustomerController(CustomerServiceImp service) {
        this.service = service;
    }

    @PostMapping
    public Long addCustomer(@RequestBody CustomerDto customerDto) {
        return service.addCustomer(customerDto);
    }


    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(CustomerMapper::mapToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public RedirectView findById(@RequestParam Long id, RedirectAttributes redirAtt) throws Exception {
        CustomerDto customerDto = CustomerMapper.mapToDto(service.findById(id));
        RedirectView redirectView= new RedirectView("/wellcome/admin/setcustomer",true);
        redirAtt.addFlashAttribute("customer",customerDto);

        return redirectView;
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
