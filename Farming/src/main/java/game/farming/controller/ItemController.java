package game.farming.controller;


import game.farming.controller.form.ItemForm;
import game.farming.domain.Item;
import game.farming.domain.UploadFile;
import game.farming.service.FileStoreService;
import game.farming.service.ItemService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final FileStoreService fileStoreService;



    @GetMapping("/add")
    public String addItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "item/addItem";
    }
    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult.getAllErrors());
            return "item/addItem";
        }
        UploadFile attachFile = fileStoreService.storeFile(form.getAttachFile());
        UploadFile storeImageFiles = fileStoreService.storeFile(form.getImageFiles());
        //데이터저장
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setDescription(form.getDescription());
        item.setAttachFile(attachFile);
        item.setPrice(form.getPrice());
        item.setImageFiles(storeImageFiles);
        itemService.save(item);
        log.info("item={}", item);
        redirectAttributes.addAttribute("itemId", item.getId());
        return "redirect:/item/{itemId}";
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
        return "/item/showItem";
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

    @ResponseBody
    @GetMapping(value="/images/{filename}")
    public Resource downloadImage(@Validated @PathVariable String filename) throws MalformedURLException {
        log.info("Downloading image {}", "file:"+fileStoreService.getFullPath(filename));
        String filePath = fileStoreService.getFullPath(filename);

        return new UrlResource("file:" + filePath);
    }


    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        Item item = itemService.findById(itemId);
        String storeFileName = item.getAttachFile().getStoreFileName();
        String uploadFileName = item.getAttachFile().getUploadFileName();

        UrlResource resource = new UrlResource("file:" + fileStoreService.getFullPath(storeFileName));
        log.info("uploadFileName={}", uploadFileName);

        String encode = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encode + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition)
                .body(resource);
    }


    //@PostConstruct
    public void initItems(){
        Item newItem = new Item("book","old book",10000);
        Item newItem1 = new Item("car","ferrari",10000000);
        itemService.save(newItem);
        itemService.save(newItem1);
    }

}
