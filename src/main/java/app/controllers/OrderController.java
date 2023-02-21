package app.controllers;

import app.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderDAO orderDAO;
    @Autowired
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping("/{customers_phonenumber}/all")
    private String getAllCustomerOrders(Model model,@PathVariable("customers_phonenumber") String customers_phonenumber){
        model.addAttribute("orders", orderDAO.listOfCourierOrders(customers_phonenumber));
        return "order/customerAll";
    }

    @GetMapping("/{couriers_phonenumber}/all")
    private String getAllCouriersOrders(Model model,@PathVariable("couriers_phonenumber") String couriers_phonenumber){
        model.addAttribute("orders", orderDAO.listOfCourierOrders(couriers_phonenumber));
        return "order/courierAll";
    }
}
