package restaurant.system.db.products.enteties;

import jakarta.persistence.Entity;
import lombok.*;
import restaurant.system.db.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Item extends BaseEntity {

    private String name;

    private double price;

    private String description;

    private int quantity;
}
