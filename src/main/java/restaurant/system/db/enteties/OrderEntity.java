package restaurant.system.db.enteties;


import jakarta.persistence.*;
import lombok.*;
import restaurant.system.db.enums.OrderStatus;
import restaurant.system.db.enums.PaidType;;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderEntity extends BaseEntity {

    private double finalPrice;

    @Enumerated(EnumType.STRING)
    private PaidType paidType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private boolean isReal;

    private String phoneNumber;

    private String deliveryAddress;

    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems = new ArrayList<>();
}
