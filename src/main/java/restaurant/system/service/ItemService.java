package restaurant.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant.system.db.enteties.ItemEntity;
import restaurant.system.db.enums.*;
import restaurant.system.db.repo.ItemRepository;
import restaurant.system.web.controllers.dto.ItemDto;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;
    public void createBeers(BeerEnum beerEnum, double price) {
        ItemEntity itemEntityByName = itemRepository.findItemEntityByName(beerEnum.name()).orElse(null);
        if (itemEntityByName != null) {
            ItemEntity beer = new ItemEntity();
            beer.setName(beerEnum.name());
            beer.setTypes(ItemTypes.DRINKS);
            beer.setSpecialType(ItemTypesSpecifications.BEER);
            beer.setPrice(price);
            itemRepository.save(beer);
        }
    }

    public void createVodka(VodkaEnum alcohol, double price) {
        ItemEntity itemEntityByName = itemRepository.findItemEntityByName(alcohol.name()).orElse(null);
        if (itemEntityByName == null) {
            ItemEntity alcoholEntity = new ItemEntity();
            alcoholEntity.setName(alcohol.name());
            alcoholEntity.setTypes(ItemTypes.DRINKS);
            alcoholEntity.setSpecialType(ItemTypesSpecifications.VODKA);
            alcoholEntity.setPrice(price);
            itemRepository.save(alcoholEntity);
        }
    }

    public void createWhiskey(WhiskeyEnum alcohol, double price) {
        ItemEntity itemEntityByName = itemRepository.findItemEntityByName(alcohol.name()).orElse(null);
        if (itemEntityByName == null) {
            ItemEntity alcoholEntity = new ItemEntity();
            alcoholEntity.setName(alcohol.name());
            alcoholEntity.setTypes(ItemTypes.DRINKS);
            alcoholEntity.setSpecialType(ItemTypesSpecifications.WHISKEY);
            alcoholEntity.setPrice(price);
            itemRepository.save(alcoholEntity);
        }
    }

    public void createBurger(BurgerEnum burger, double price, String description) {
        ItemEntity itemEntityByName = itemRepository.findItemEntityByName(burger.name()).orElse(null);
        if (itemEntityByName == null) {
            ItemEntity food = new ItemEntity();
            food.setName(burger.name());
            food.setTypes(ItemTypes.FOOD);
            food.setSpecialType(ItemTypesSpecifications.BURGERS);
            food.setPrice(price);
            food.setDescription(description);
            itemRepository.save(food);
        }
    }

    public void createAppetizers(AppetizersEnum appetizersEnum, double price, String description) {
        ItemEntity itemEntityByName = itemRepository.findItemEntityByName(appetizersEnum.name()).orElse(null);
        if (itemEntityByName == null) {
            ItemEntity food = new ItemEntity();
            food.setName(appetizersEnum.name());
            food.setTypes(ItemTypes.FOOD);
            food.setSpecialType(ItemTypesSpecifications.APPETIZERS);
            food.setPrice(price);
            food.setDescription(description);
            itemRepository.save(food);
        }
    }

    public String updateItemDescription(Enum<?> name, String description) {
        ItemEntity itemEntityByName = itemRepository.findItemEntityByName(name.name()).orElse(null);
        if (itemEntityByName != null) {
            itemEntityByName.setDescription(description);
            itemRepository.save(itemEntityByName);
            return "Item " + name.name() + "new description:" + description;
        }
        return "Item is not found:" + name;
    }

    public String updateItemPrice(Enum<?> name, double price) {
        if (price <= 0) {
            return "Price cannot be lower than 0";
        }
        ItemEntity itemEntityByName = itemRepository.findItemEntityByName(name.name()).orElse(null);
        if (itemEntityByName != null) {
            itemEntityByName.setPrice(price);
            itemRepository.save(itemEntityByName);
            return "Item " + name.name() + " price is set to:" + price;
        }
        return "Item is not found:" + name;
    }

    public String updateItemQuantity(Enum<?> name, int quantity) {
        ItemEntity itemEntityByName = itemRepository.findItemEntityByName(name.name()).orElse(null);
        if (itemEntityByName != null) {
            String updateQuantity = itemEntityByName.updateQuantity(quantity);
            if (updateQuantity.startsWith("Quantity")) {
                itemRepository.saveAndFlush(itemEntityByName);
                return updateQuantity;
            }
            return updateQuantity;
        }
        return "Item is not found:" + name;
    }

    public List<ItemDto> getAllItems() {
        List<ItemEntity> all = itemRepository.findAll();
        List<ItemEntity> filteredList = all.stream().filter(itemEntity -> itemEntity.getQuantity() > 0)
                .collect(Collectors.toList());

        List<ItemDto> itemDtoList = new ArrayList<>();
        for (ItemEntity allItem : filteredList) {
            ItemDto itemDto = mapDTO(allItem);
            itemDtoList.add(itemDto);

        }
        return itemDtoList;
    }
    private ItemDto mapDTO(ItemEntity itemEntity) {
        ItemDto itemDto = new ItemDto();
        itemDto.setTypes(itemEntity.getTypes().name());
        itemDto.setSpecialType(itemEntity.getSpecialType().name());
        itemDto.setPrice(itemEntity.getPrice());
        itemDto.setDescription(itemEntity.getDescription());
        return itemDto;
    }
}
