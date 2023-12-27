package restaurant.system.db.enteties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import org.hibernate.annotations.Type;
import restaurant.system.db.enums.ItemTypes;
import restaurant.system.db.enums.ItemTypesSpecifications;

import java.util.Objects;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ItemEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ItemTypes types;

    @Enumerated(EnumType.STRING)
    private ItemTypesSpecifications specialType;

    @Column(unique = true)

    private String name;

    @Min( value = 0 ,message = "price should be higher than 0")
    private double price;

    private String description;

    @Min(value = 0, message = "Quantity must be 0 or higher")
    private int quantity;

    public String updateQuantity(int quantity){
        if (this.getQuantity()+quantity<0){
         return  "Cannot update quantity for "+this.getName() + "current quantity is " +
                 this.getQuantity() + "and we cannot deduct:"+quantity;
        }
        this.setQuantity(this.getQuantity()+quantity);
        return "Quantity for "+this.getName() + "is successfully set to:"+this.getQuantity();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemEntity that = (ItemEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
