package restaurant.system.controller.dto;

import restaurant.system.db.enums.ItemTypes;
import restaurant.system.db.enums.ItemTypesSpecifications;

public class CreateItemDto {


    private ItemTypes itemTypes;
    private ItemTypesSpecifications itemTypesSpecifications;
    private String name;
    private String description;
    private String imageURL;
    private double price;
    private int pieces;
    private int grams;

    public CreateItemDto() {
    }
    public CreateItemDto(ItemTypes itemTypes, ItemTypesSpecifications itemTypesSpecifications, String name, String description, String imageURL, double price, int pieces, int grams) {
        this.itemTypes = itemTypes;
        this.itemTypesSpecifications = itemTypesSpecifications;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
        this.pieces = pieces;
        this.grams = grams;
    }

    public ItemTypes getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(ItemTypes itemTypes) {
        this.itemTypes = itemTypes;
    }

    public ItemTypesSpecifications getItemTypesSpecifications() {
        return itemTypesSpecifications;
    }

    public void setItemTypesSpecifications(ItemTypesSpecifications itemTypesSpecifications) {
        this.itemTypesSpecifications = itemTypesSpecifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }
}
