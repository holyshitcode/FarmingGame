package game.farming.controller;


import game.farming.domain.Item;
import game.farming.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/add")
    public String addItemForm() {
        return "item/addItem";
    }
    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item) {
        Item item1 = new Item();
        item1.setName(item.getName());
        item1.setPrice(item.getPrice());
        item1.setDescription(item.getDescription());
        itemService.save(item1);
        return "redirect:/item/list";
    }
    @GetMapping("/list")
    public String listItems(Model model) {
        model.addAttribute("items", itemService.findAll());

        return "item/list";
    }
}
