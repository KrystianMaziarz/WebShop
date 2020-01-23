package pl.com.store.webstore.controllers.restControllers;

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

    @GetMapping("/all")
    public void findAll(HttpServletRequest request, HttpServletResponse response) {
        List<CustomerDto> customers = getCustomerDtos();
        redirectWithAttribute(request, response, "customers", customers, "/wellcome/admin/findcustomer");
    }

    private List<CustomerDto> getCustomerDtos() {
        return service.findAll().stream()
                    .map(CustomerMapper::mapToDto)
                    .collect(Collectors.toList());
    }

    @GetMapping
    public void findById(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerDto customerDto = CustomerMapper.mapToDto(service.findById(id));
        redirectWithAttribute(request, response, "customer", customerDto, "/setcustomer");
    }


    @GetMapping("/email")
    public void findByEmail(@RequestParam String email, HttpServletRequest request, HttpServletResponse response) {
        CustomerDto customerDto = CustomerMapper.mapToDto(service.findByEmail(email));
        redirectWithAttribute(request, response, "customer", customerDto, "/setcustomer");
    }

    @PostMapping
    public void updateCustomer(@ModelAttribute("customerDto") CustomerDto customerDto, @ModelAttribute("addressDto") AddressDto addressDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        customerDto.setAddressDto(addressDto);
        CustomerDto updated = CustomerMapper.mapToDto(service.updateCustomer(customerDto));
        redirectWithAttribute(request, response, "customer", updated, "/setcustomer");

    }

    @PostMapping("/del")
    public void doDelete(@RequestParam Long deletedId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        service.deleteById(deletedId);
        List<CustomerDto> customerDtos = getCustomerDtos();
        redirectWithAttribute(request, response, "customers", customerDtos, "/wellcome/admin/findcustomer");
    }

    private void redirectWithAttribute(HttpServletRequest request, HttpServletResponse response, String attrName, Object customerDto, String url) {

        request.getSession().setAttribute(attrName, customerDto);
        response.setHeader("Location", url);
        response.setStatus(302);
    }

}
