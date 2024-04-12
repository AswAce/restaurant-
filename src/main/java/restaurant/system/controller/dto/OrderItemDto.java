package restaurant.system.controller.dto;

public class OrderItemDto {
    private Long id;
    private Long itemId;  // References the associated ItemEntity
    private int quantity;

    // Constructors, Getters, and Setters
    public OrderItemDto() {}

    public OrderItemDto(Long id, Long itemId, int quantity) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

