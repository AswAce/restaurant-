package restaurant.system.db.enteties;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
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
    @ToString.Exclude
    private List<ItemEntity> items = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TableBill tableBill = (TableBill) o;
        return getId() != null && Objects.equals(getId(), tableBill.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
