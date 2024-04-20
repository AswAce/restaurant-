package restaurant.system.controller;

import jakarta.persistence.Convert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.system.controller.dto.AddDailySupplierDto;
import restaurant.system.controller.dto.CreateItemDto;
import restaurant.system.controller.dto.ItemEntityDto;
import restaurant.system.controller.dto.ItemQuantityDto;
import restaurant.system.db.enums.ItemTypes;
import restaurant.system.db.enums.ItemTypesSpecifications;
import restaurant.system.service.ItemService;
import restaurant.system.webResponse.ResponseMessage;

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
    public ResponseEntity<ResponseMessage> createItem(@RequestBody CreateItemDto createItemDto) {
        ItemEntityDto createdItem = itemService.createItem(createItemDto);
        if (createdItem != null) {
            return ResponseEntity.ok().body(new ResponseMessage("Item created successfully!", createdItem));
        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage("Failed to create item"));
        }
    }

    @PostMapping("/addDailySupplier")
    public ItemEntityDto addDailySupplier(@RequestBody AddDailySupplierDto addDailySupplierDto) {
        return itemService.addDailySupplier(addDailySupplierDto);
    }

    @PostMapping("/addAllDailySupplier")
    public List<ItemEntityDto> addAllDailySupplier(@RequestBody List<AddDailySupplierDto> addDailySupplierDto) {
        System.out.println(addDailySupplierDto.size());
        for (AddDailySupplierDto dailySupplierDto : addDailySupplierDto) {
            System.out.println("name" + dailySupplierDto.getItemName() + "qan" + dailySupplierDto.getQuantity());
        }
        return itemService.addALlDailySupplier(addDailySupplierDto);
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
        System.out.println("types:");
        for (String type : types) {
            System.out.println(type);
        }
        return types;
    }

    @GetMapping("/get-item-types")
    public List<String> getItemTypes() {
        List<String> types = new ArrayList<>();
        Arrays.stream(ItemTypes.values()).forEach(t -> types.add(t.name()));
        System.out.println("types:");
        for (String type : types) {
            System.out.println(type);
        }
        return types;
    }

    @GetMapping("/get-all-item")
    public List<ItemEntityDto> getAllItems() {
        return itemService.findAllItems();
    }

    @GetMapping("/get-all-item-quantities")
    public List<ItemQuantityDto> getAllItemsDailyQauntity() {
        List<ItemQuantityDto> allItemsDailyQauntity = itemService.findAllItemsDailyQauntity();
        for (ItemQuantityDto itemQuantityDto : allItemsDailyQauntity) {
            System.out.println("name:" + itemQuantityDto.getName() + "qantity:" + itemQuantityDto.getCurrentQuantity());
        }
        return allItemsDailyQauntity;
    }

}
