package pl.com.store.webstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.entities.Address;
import pl.com.store.webstore.services.AddressService;
import pl.com.store.webstore.services.CustomerService;

import javax.validation.Valid;


@Controller
public class RegisterController {

    private CustomerService customerService;
    private AddressService addressService;

   public RegisterController (CustomerService customerService,AddressService addressService)
   {
       this.customerService = customerService;
       this.addressService = addressService;
   }

   @GetMapping("/register")
    public String showRegisterForm (Model model){

       CustomerDto customerDto = new CustomerDto();
       model.addAttribute("customer", customerDto);
       return "/register";
   }

   @PostMapping("/register")
    public String registerCustomer (@Valid @ModelAttribute("customer") CustomerDto customerDto , @ModelAttribute("address") AddressDto addressDto, BindingResult bindingResult) {

       if (bindingResult.hasErrors()) {
           return "/register";
   }
       addressService.addAddress(addressDto,customerDto);
       return "redirect:/login";
   }


}
