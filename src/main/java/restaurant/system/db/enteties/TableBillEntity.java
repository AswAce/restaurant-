package restaurant.system.db.enteties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class TableBillEntity extends BaseEntity {

    private int tableNumber;

    private double price;

    private boolean paid;

    private LocalDateTime dateTimeOfTheBill;

    @OneToMany(mappedBy = "tableBill", cascade = CascadeType.ALL)
    private List<OrderEntity> items = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TableBillEntity tableBill = (TableBillEntity) o;
        return getId() != null && Objects.equals(getId(), tableBill.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
