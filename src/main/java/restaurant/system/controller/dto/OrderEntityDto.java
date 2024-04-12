package restaurant.system.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderEntityDto {
    private Long id;
    private double finalPrice;
    private String orderStatus;  // Could also be an enum if you want to enforce specific values
    private String phoneNumber;
    private String deliveryAddress;
    private LocalDateTime orderDate;
    private List<OrderItemDto> orderItems;

    // Constructors, Getters, and Setters
    public OrderEntityDto() {}

    public OrderEntityDto(Long id, double finalPrice, String orderStatus, String phoneNumber, String deliveryAddress, LocalDateTime orderDate, List<OrderItemDto> orderItems) {
        this.id = id;
        this.finalPrice = finalPrice;
        this.orderStatus = orderStatus;
        this.phoneNumber = phoneNumber;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getFinalPrice() { return finalPrice; }
    public void setFinalPrice(double finalPrice) { this.finalPrice = finalPrice; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public List<OrderItemDto> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemDto> orderItems) { this.orderItems = orderItems; }
}
