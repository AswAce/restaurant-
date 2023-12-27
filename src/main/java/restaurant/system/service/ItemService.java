package restaurant.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.system.db.enteties.ItemEntity;
import restaurant.system.db.enums.BeerEnum;
import restaurant.system.db.enums.ItemTypes;
import restaurant.system.db.enums.ItemTypesSpecifications;
import restaurant.system.db.repo.ItemRepository;

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

    String updateItemPrice(Enum<?> name, double price) {
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

    String updateItemQuantity(Enum<?> name, int quantity) {
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
}
