package pl.com.store.webstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.services.CustomerService;


@Controller
public class RegisterController {

    private CustomerService customerService;

   public RegisterController (CustomerService customerService)
   {
       this.customerService = customerService;
   }

   @GetMapping("/register")
    public String showRegisterForm (Model model){

       CustomerDto customerDto = new CustomerDto();
       model.addAttribute("customer", customerDto);
       return "register";
   }

   @PostMapping("/register")
    public String registeruser (@ModelAttribute("customer") CustomerDto customerDto , BindingResult bindingResult) {

       if (bindingResult.hasErrors()) {
           return "register";
   }
       customerService.addCustomer(customerDto);
       return "redirect:/login";
   }


}
