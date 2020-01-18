package pl.com.store.webstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.services.implementations.CustomerServiceImp;
import pl.com.store.webstore.services.implementations.mappers.CustomerMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private CustomerServiceImp service;

    public CustomerController(CustomerServiceImp service) {
        this.service = service;
    }

//    @PostMapping
//    public Long addCustomer(@RequestBody CustomerDto customerDto) {
//        return service.addCustomer(customerDto);
//    }


    @GetMapping("/all")
    public void findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        List<CustomerDto> customers = service.findAll().stream()
                .map(CustomerMapper::mapToDto)
                .collect(Collectors.toList());
        String url = "/wellcome/admin/findcustomer";
        httpServletRequest.getSession().setAttribute("customers", customers);
        httpServletResponse.setHeader("Location", url);
        httpServletResponse.setStatus(302);
    }

    @GetMapping
    public void findById(@RequestParam Long id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        CustomerDto customerDto = CustomerMapper.mapToDto(service.findById(id));

        String url = "/setcustomer";
        httpServletRequest.getSession().setAttribute("customer", customerDto);
        httpServletResponse.setHeader("Location", url);
        httpServletResponse.setStatus(302);
    }

    @GetMapping("/email")
    public void findByEmail(@RequestParam String email, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        CustomerDto customerDto = CustomerMapper.mapToDto(service.findByEmail(email));

        String url = "/setcustomer";
        httpServletRequest.getSession().setAttribute("customer", customerDto);
        httpServletResponse.setHeader("Location", url);
        httpServletResponse.setStatus(302);
    }


//    @PostMapping(value = "/{id}")
//    public ResponseEntity<CustomerDto> updateCustomer(@ModelAttribute("customerDto") CustomerDto customerDto, @ModelAttribute("address") AddressDto addressDto) throws Exception {
//        return ResponseEntity.ok(CustomerMapper.mapToDto(service.updateCustomer(customerDto, addressDto)));
//    }

//    @PostMapping(value = "/{id}")
//    public ResponseEntity<CustomerDto> updateCustomer(HttpServletRequest httpServletRequest) throws Exception {
//        CustomerDto user =(CustomerDto) httpServletRequest.getAttribute("user");
//        //return ResponseEntity.ok(CustomerMapper.mapToDto(service.updateCustomer(customerDto, addressDto)));
//        return null;
//    }

    @PostMapping
    public void updateCustomer(@ModelAttribute("customerDto") CustomerDto customerDto, @ModelAttribute("addressDto") AddressDto addressDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerDto updated = CustomerMapper.mapToDto(service.updateCustomer(customerDto, addressDto));
        String url="/update";
        request.getSession().setAttribute("customer",updated);
        response.setHeader("Location", url);
        response.setStatus(302);

    }

    @PostMapping("/del")
    public ResponseEntity<String> doDelete(@RequestParam Long deletedId) throws Exception {
        deleteCustomer(deletedId);
        return ResponseEntity.ok("Deleted !");
    }

    @DeleteMapping
    public void deleteCustomer(Long deletedId) throws Exception {
        service.deleteById(deletedId);
    }
}
