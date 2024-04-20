package restaurant.system.controller.dto;

import restaurant.system.db.enums.ItemTypes;
import restaurant.system.db.enums.ItemTypesSpecifications;

public class ItemQuantityDto {

    private int id;

    private String name;

    private ItemTypesSpecifications itemTypesSpecifications;

    private ItemTypes itemTypes;

    private int currentQuantity;

    public ItemQuantityDto(int id, String name, ItemTypesSpecifications itemTypesSpecifications, ItemTypes itemTypes) {
        this.id = id;
        this.name = name;
        this.itemTypesSpecifications = itemTypesSpecifications;
        this.itemTypes = itemTypes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemTypesSpecifications getItemTypesSpecifications() {
        return itemTypesSpecifications;
    }

    public void setItemTypesSpecifications(ItemTypesSpecifications itemTypesSpecifications) {
        this.itemTypesSpecifications = itemTypesSpecifications;
    }

    public ItemTypes getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(ItemTypes itemTypes) {
        this.itemTypes = itemTypes;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
}
