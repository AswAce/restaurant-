package restaurant.system.web.controllers.dto;

import java.io.Serializable;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto extends ItemDto implements Serializable {
    private String specialPreparation;

    private int itemOrderQuantity;

    public OrderItemDto(String types, String specialType, String name, double price, String description,
            String specialPreparation, int itemOrderQuantity) {
        super(types, specialType, name, price, description);
        this.specialPreparation = specialPreparation;
        this.itemOrderQuantity = itemOrderQuantity;
    }

}
