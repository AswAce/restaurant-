package restaurant.system.web.controllers.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {

    private int tableNumber;

    private int orderNumber;

    private int BillID;

    private List<OrderItemDto> orderItems = new ArrayList<>();

    private Boolean orderStatusKitchen = false;
    private Boolean orderStatusBar = false;

}
