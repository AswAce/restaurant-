package restaurant.system.web.controllers.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private  int id;
    private int tableNumber;

    private double price;

    private boolean paid;

    private LocalDateTime dateTimeOfTheBill;

    private List<OrderDto> orders = new ArrayList<>();
}
