package restaurant.system.controller;

import jakarta.persistence.Convert;
import org.springframework.web.bind.annotation.*;
import restaurant.system.controller.dto.AddDailySupplierDto;
import restaurant.system.controller.dto.CreateItemDto;
import restaurant.system.controller.dto.ItemEntityDto;
import restaurant.system.db.enteties.ItemEntity;
import restaurant.system.db.enums.ItemTypes;
import restaurant.system.db.enums.ItemTypesSpecifications;
import restaurant.system.service.ItemService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/admin")
public class AdminController {

    private final ItemService itemService;

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create-item")
    public ItemEntityDto createSashimi(@RequestBody CreateItemDto createItemDto) {
        return itemService.createItem(createItemDto);
    }

    @PostMapping("/addDailySupplier")
    public ItemEntityDto addDailySupplier(@RequestBody AddDailySupplierDto addDailySupplierDto) {
        return itemService.addDailySupplier(addDailySupplierDto);
    }

    @PostMapping("/update-item")
    public ItemEntityDto updateItem(@RequestBody ItemEntityDto itemEntityDto) {
        return itemService.updateItem(itemEntityDto);
    }

    @GetMapping("/item/{name}")
    public ItemEntityDto getItem(@PathVariable String itemName) {
        return itemService.findByItemNameDto(itemName);
    }

    @GetMapping("/get-item-special-types")
    public List<String> getItemSpecialTypes() {
        List<String> types = new ArrayList<>();
        Arrays.stream(ItemTypesSpecifications.values()).forEach(t -> types.add(t.name()));
        return types;
    }

    @GetMapping("/get-item-types")
    public List<String> getItemTypes() {
        List<String> types = new ArrayList<>();
        Arrays.stream(ItemTypes.values()).forEach(t -> types.add(t.name()));
        return types;
    }

}
