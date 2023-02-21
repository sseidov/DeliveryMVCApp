package app.controllers;

import app.dao.CustomerDAO;
import app.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerDAO customerDAO;
    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("customers", customerDAO.listOfCustomersEmail());
        return "customer/all";
    }

    @GetMapping("/{email}")
    private String getByEmail(@PathVariable("email") String email, Model model){
        model.addAttribute("customer", customerDAO.getCustomerByEmail(email));
        return "customer/customerInfo";
    }

    @GetMapping("/new")
    public String newCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer );
        return "customer/add";
    }

    @PostMapping()
    public String registrNewCustomer(@ModelAttribute("customer") Customer customer){
        customerDAO.registrNewCust(customer);
        return "redirect:/customer/all";
    }

    @GetMapping("/{email}/edit")
    public String update(Model model, @PathVariable("email") String email){
        model.addAttribute("customer", customerDAO.getCustomerByEmail(email));
        return "customer/edit";
    }

    @PatchMapping("/{email}")
    public String upd(@ModelAttribute("customer") Customer customer, @PathVariable("email") String email){
        customerDAO.updateCustomer(email, customer);
        return "redirect:/customer/all";
    }

    @DeleteMapping("/{email}")
    public String delete(@PathVariable("email") String email){
        customerDAO.delete(email);
        return "redirect:/customer/all";
    }

}
