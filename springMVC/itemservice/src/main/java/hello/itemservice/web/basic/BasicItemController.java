package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "basic/items";
    }

    @GetMapping("/{itemId}") // http://localhost:8080/basic/items/1
    public String itemDetail(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemRepository.findById(itemId));
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    // @RequestParam 사용
    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    // @ModelAttribute 사용
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);

        // @ModelAttribute("item") -> 해당 어노테이션의 영향으로 model에 자동 추가
        //model.addAttribute("item", item); // 생략 가능하다

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);

        // @ModelAttribute -> 해당 어노테이션의 영향으로 model에 자동 추가(Item -> item으로 model에 들어감)
        //model.addAttribute("item", item); // 생략 가능하다

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);

        // @ModelAttribute 생략 가능 -> 해당 어노테이션의 영향으로 model에 자동 추가(Item -> item으로 model에 들어감)
        //model.addAttribute("item", item); // 생략 가능하다

        return "basic/item";
    }

    /**
    * PRG - Post/Redirect/Get
    */
    //@PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item) {
        itemRepository.save(item);

        // @ModelAttribute -> 해당 어노테이션의 영향으로 model에 자동 추가(Item -> item으로 model에 들어감)
        //model.addAttribute("item", item); // 생략 가능하다

        return "redirect:/basic/items/" + item.getId();
    }

    /**
     * RedirectAttributes
     */
    @PostMapping("/add")
    public String addItemV6(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        // @ModelAttribute -> 해당 어노테이션의 영향으로 model에 자동 추가(Item -> item으로 model에 들어감)
        //model.addAttribute("item", item); // 생략 가능하다

        return "redirect:/basic/items/{itemId}"; // http://localhost:9090/basic/items/3?status=true
    }

    @GetMapping("/{itemId}/edit")
    public String editFormGet(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemRepository.findById(itemId));
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editFormPost(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용(미리 데이터 넣어놓기)
     */
    @PostConstruct
    public void init() {
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
    }
}
