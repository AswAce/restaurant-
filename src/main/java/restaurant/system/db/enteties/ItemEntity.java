package restaurant.system.db.enteties;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import restaurant.system.db.enums.ItemTypes;
import restaurant.system.db.enums.ItemTypesSpecifications;

import java.util.*;

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

    @Min(value = 0, message = "price should be higher than 0")
    private double price;

    private String description;

    private String imageURL;

    @Min(value = 0, message = "Quantity must be 0 or higher")
    private int pieces;

    private int grams;

    @OneToMany(mappedBy = "itemEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SupplyEntity> supplyEntities = new ArrayList<>();

    public ItemEntity(ItemTypes types, ItemTypesSpecifications specialType, String name, double price, String description, String imageURL, int pieces, int grams) {
        this.types = types;
        this.specialType = specialType;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
        this.pieces = pieces;
        this.grams = grams;
    }

    public void addDailySupplier(SupplyEntity dailySupplieEntity) {
        supplyEntities.add(dailySupplieEntity);
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
