package restaurant.system.db.enteties;

import jakarta.persistence.Entity;
import lombok.*;

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
