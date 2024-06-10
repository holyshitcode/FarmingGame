package game.farming.controller;


import game.farming.domain.Item;
import game.farming.service.ItemService;
import game.farming.validators.ItemValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemValidator itemValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(itemValidator);
    }

    @GetMapping("/add")
    public String addItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "item/addItem";
    }
    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult.getAllErrors());
            return "item/addItem";
        }
        log.info("errors={}", bindingResult.getAllErrors());

        itemService.save(item);
        return "redirect:/item/list";
    }
    @GetMapping("/list")
    public String listItems(Model model) {
        model.addAttribute("items", itemService.findAll());

        return "item/list";
    }
}
