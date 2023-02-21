package app.controllers;

import app.dao.DishDAO;
import app.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dish")
public class DishController {
    private final DishDAO dishDAO;

    @Autowired
    public DishController(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("dishes", dishDAO.listOfDishesName());
        return "dish/all";
    }

    @GetMapping("/{name}")
    private String getByName(@PathVariable("name") String name, Model model){
        model.addAttribute("dish", dishDAO.getDishInfoByDishName(name));
        return "dish/dishInfo";
    }

    @GetMapping("/new")
    public String newDish(Model model){
        Dish dish = new Dish();
        model.addAttribute("dish", dish );
        return "dish/add";
    }

    @PostMapping()
    public String createNewDish(@ModelAttribute("dish") Dish dish){
        dishDAO.addNewDish(dish);
        return "redirect:/dish/all";
    }

    @GetMapping("/{name}/edit")
    public String update(Model model, @PathVariable("name") String name){
        model.addAttribute("dish", dishDAO.getDishInfoByDishName(name));
        return "dish/edit";
    }

    @PatchMapping("/{name}")
    public String upd(@ModelAttribute("dish") Dish dish, @PathVariable("name") String name){
        dishDAO.updateDish(name, dish);
        return "redirect:/dish/all";
    }

    @DeleteMapping("/{name}")
    public String delete(@PathVariable("name") String name){
        dishDAO.delete(name);
        return "redirect:/dish/all";
    }

}
