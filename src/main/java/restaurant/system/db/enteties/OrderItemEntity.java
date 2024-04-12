package restaurant.system.db.enteties;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderItemEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_entity_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    private int quantity;



    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
