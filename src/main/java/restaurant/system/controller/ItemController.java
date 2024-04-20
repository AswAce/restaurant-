package restaurant.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restaurant.system.controller.dto.ItemClientEntityDto;
import restaurant.system.controller.dto.ItemEntityDto;
import restaurant.system.db.enteties.ItemEntity;
import restaurant.system.db.enums.ItemTypesSpecifications;
import restaurant.system.service.ItemService;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<ItemClientEntityDto> getItemsBySpecification(@RequestParam("productType") String productTypeStr) {
        ItemTypesSpecifications specification = ItemTypesSpecifications.valueOf(productTypeStr.toUpperCase());
        return itemService.forClientFindAllByItemTypesSpecifications(specification);
    }
}
