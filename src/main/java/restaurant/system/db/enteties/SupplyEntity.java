package restaurant.system.db.enteties;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SupplyEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    @Column(name = "start_quantity")
    private int quantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "sold_items")
    private int sold;

    private int leftover;
}
