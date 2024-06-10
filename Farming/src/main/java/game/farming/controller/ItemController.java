package game.farming.controller;


import game.farming.domain.Item;
import game.farming.repository.ItemRepository;
import game.farming.repository.MemoryItemRepository;
import game.farming.service.ItemService;
import game.farming.validators.ItemValidator;
import jakarta.annotation.PostConstruct;
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
    @GetMapping("/edit/{itemId}")
    public String editItemForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemService.findById(itemId);
        model.addAttribute("item", findItem);
        return "item/editItem";
    }
    @PostMapping("/edit/{itemId}")
    public String editItem(@PathVariable Long itemId,@Validated @ModelAttribute Item item, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult.getAllErrors());
            return "item/editItem";
        }
        itemService.update(itemId,item);
        return "redirect:/item/list";
    }
    @GetMapping("/{itemId}")
    public String showItemForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemService.findById(itemId);
        model.addAttribute("item", findItem);
        return "item/showItem";
    }
    @GetMapping("/list")
    public String listItems(Model model) {
        model.addAttribute("items", itemService.findAll());

        return "item/list";
    }
    @PostMapping("/delete")
    public String deleteItem(@RequestParam("id") Long itemId, RedirectAttributes redirectAttributes) {
        itemService.delete(itemId);
        redirectAttributes.addFlashAttribute("message", "Item deleted");
        return "redirect:/item/list";
    }
    @PostConstruct
    public void initItems(){
        Item newItem = new Item("book","old book",10000);
        Item newItem1 = new Item("car","ferrari",10000000);
        itemService.save(newItem);
        itemService.save(newItem1);
    }

}
