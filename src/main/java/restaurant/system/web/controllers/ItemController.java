package restaurant.system.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restaurant.system.db.enteties.ItemEntity;
import restaurant.system.service.ItemService;
import restaurant.system.web.controllers.dto.ItemDto;

@RestController
@RequestMapping("/menu")
public class ItemController {
    @Autowired
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<ItemDto>> menuItems() {
        List<ItemDto> allItems = itemService.getAllItems();
        if (allItems.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allItems);
    }
}
