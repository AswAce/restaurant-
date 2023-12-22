package restaurant.system.db.enteties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TableBill extends BaseEntity {

    private int tableNumber;

    private double price;

    private boolean paid;

    private LocalDateTime dateTimeOfTheBill;

    @ManyToMany
    private List<Item> items = new ArrayList<>();

}
