package app.controllers;

import app.dao.CourierDAO;
import app.models.Courier;
import app.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courier")
public class CourierController {
    private final CourierDAO courierDAO;

    @Autowired
    public CourierController(CourierDAO courierDAO) {
        this.courierDAO = courierDAO;
    }

    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("couriers", courierDAO.listOfCouriers());
        return "courier/all";
    }

    @GetMapping("/{phonenumber}")
    private String getByEmail(@PathVariable("phonenumber") String phonenumber, Model model){
        model.addAttribute("courier", courierDAO.getCourierByPhonenumber(phonenumber));
        return "courier/courierInfo";
    }

    @GetMapping("/new")
    public String newCourier(Model model){
        Courier courier = new Courier();
        model.addAttribute("courier", courier );
        return "courier/add";
    }

    @PostMapping()
    public String registrationOfNewCustomer(@ModelAttribute("courier") Courier courier){
        courierDAO.registrationOfNewCourier(courier);
        return "redirect:/courier/all";
    }

    @GetMapping("/{phonenumber}/edit")
    public String update(Model model, @PathVariable("phonenumber") String phonenumber){
        model.addAttribute("courier", courierDAO.getCourierByPhonenumber(phonenumber));
        return "courier/edit";
    }

    @PatchMapping("/{phonenumber}")
    public String upd(@ModelAttribute("courier") Courier courier, @PathVariable("phonenumber") String phonenumber){
        courierDAO.updateCourier(phonenumber, courier);
        return "redirect:/courier/all";
    }

    @DeleteMapping("/{phonenumber}")
    public String delete(@PathVariable("phonenumber") String phonenumber){
        courierDAO.delete(phonenumber);
        return "redirect:/courier/all";
    }


}
