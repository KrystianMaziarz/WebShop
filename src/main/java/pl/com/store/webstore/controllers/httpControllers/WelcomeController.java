package pl.com.store.webstore.controllers.httpControllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.exceptions.ItemNotExists;
import pl.com.store.webstore.services.CustomerService;
import pl.com.store.webstore.services.ItemService;
import pl.com.store.webstore.services.implementations.mappers.ItemMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WelcomeController {

    private boolean logged = false;
    private boolean isAdmin = false;
    private ItemService service;
    private CustomerService customerService;


    public WelcomeController(ItemService service, CustomerService customerService) {
        this.service = service;
        this.customerService = customerService;
    }

    @GetMapping({"/wellcome", "/"})
    public String showWelcomeForm(Model model) throws ItemNotExists {
        List<ItemDto> items = getItemDtos();
        model.addAttribute("items", items);
        model.addAttribute("logged", logged = false);
        model.addAttribute("admin", isAdmin = isAdmin());
        return "wellcome";
    }

    @GetMapping({"/wellcome/logged"})
    public String showWelcomeFormAfterLogging(Model model, HttpServletRequest request) throws ItemNotExists {
        String email = request.getUserPrincipal().getName();
        Customer customer = customerService.findByEmail(email);
        Long id = customer.getId();
        Object numberOfitemsAtBasket = request.getSession().getAttribute("count");
        List<ItemDto> items = getItemDtos();
        model.addAttribute("items", items);
        model.addAttribute("logged", logged = true);
        model.addAttribute("admin", isAdmin = isAdmin());
        model.addAttribute("customerId", id);
        model.addAttribute("count", numberOfitemsAtBasket);
        return "wellcome";
    }

    @GetMapping({"/wellcome/admin"})
    public String showWelcomeWithAdminPanel(Model model) throws ItemNotExists {
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
        List customers = (List) httpServletRequest.getSession().getAttribute("customers");
        model.addAttribute("customers", customers);
        return "/findcustomer";
    }

    private List<ItemDto> getItemDtos() throws ItemNotExists {
        List<ItemDto> items;
        if (service.findAllItems().isEmpty()) {

            throw new ItemNotExists();

        } else {
            items = service.findAllItems().stream()
                    .map(ItemMapper::mapToDto)
                    .collect(Collectors.toList());
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
