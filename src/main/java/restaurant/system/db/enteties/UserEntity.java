package restaurant.system.db.enteties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant.system.db.enums.RoleTypes;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Entity
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    public int password;

    @Enumerated(EnumType.STRING)
    private RoleTypes role;

}
