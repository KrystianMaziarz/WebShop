package pl.com.store.webstore.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.services.CustomerService;
import pl.com.store.webstore.services.ItemService;
import pl.com.store.webstore.services.implementations.mappers.ItemMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WelcomeController {

    private boolean logged;
    private boolean isAdmin;
    private ItemService service;
    private CustomerService customerService;


    public WelcomeController(ItemService service,CustomerService customerService) {
        this.service = service;
        this.customerService=customerService;
    }

    @GetMapping({"/wellcome", "/"})
    public String showWelcomeForm(Model model) {
        List<ItemDto> items = getItemDtos();
        model.addAttribute("items", items);
        model.addAttribute("logged", logged = false);
        model.addAttribute("admin", isAdmin = isAdmin());
        return "wellcome";
    }

    @GetMapping({"/wellcome/logged"})
    public String showWelcomeFormAfterLogging(Model model,HttpServletRequest request) {
        String email = request.getUserPrincipal().getName();
        Customer customer = customerService.findByEmail(email);
        Long id = customer.getId();
        Object count = request.getSession().getAttribute("count");
        List<ItemDto> items = getItemDtos();
        model.addAttribute("items", items);
        model.addAttribute("logged", logged = true);
        model.addAttribute("admin", isAdmin = isAdmin());
        model.addAttribute("customerId", id);
        model.addAttribute("count",count);
        return "wellcome";
    }

    @GetMapping({"/wellcome/admin"})
    public String showWelcomeWithAdminPanel(Model model) {
        List<ItemDto> items = getItemDtos();
        model.addAttribute("items", items);
        model.addAttribute("logged", logged = true);
        model.addAttribute("admin", isAdmin = isAdmin());
        return "wellcome";
    }

    @GetMapping({"/wellcome/admin/additem"})
    public String addItemPanel() {
        return "additem";
    }

    @GetMapping({"/wellcome/admin/findcustomer"})
    public String findCustomerPanel(HttpServletRequest httpServletRequest, Model model) {
       List customers =(List)httpServletRequest.getSession().getAttribute("customers");
        model.addAttribute("customers",customers);
        return "/findcustomer";
    }

    @GetMapping({"/setcustomer"})
    public String setCustomerPanel(HttpServletRequest httpServletRequest, Model model) {
        CustomerDto user = (CustomerDto) httpServletRequest.getSession().getAttribute("customer");
        model.addAttribute("customer", user);
        return "/setcustomer";
    }

    @GetMapping({"/payment"})
    public String setpaymentPanel(HttpServletRequest httpServletRequest, Model model) {

        Object itemDto = httpServletRequest.getSession().getAttribute("itemDto");
        Object orderDto = httpServletRequest.getSession().getAttribute("orderDto");
        model.addAttribute("item", itemDto);
        model.addAttribute("orderDto",orderDto);
        return "/payment";
    }

    private List<ItemDto> getItemDtos() {
        List<ItemDto> items = new ArrayList<>();
        if (!service.findAllItems().isEmpty()) {
            items = service.findAllItems().stream()
                    .map(ItemMapper::mapToDto)
                    .collect(Collectors.toList());
        } else {
            ItemDto itemDto = new ItemDto();
            itemDto.setDescription("Nie znaleziono żadnego przedmiotu");
            items.add(itemDto);
        }
        return items;
    }

    private boolean isAdmin() {
        String role = "ROLE_ADMIN";
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return false;
        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return false;
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.equals(auth.getAuthority()))
                return true;
        }
        return false;
    }
}
