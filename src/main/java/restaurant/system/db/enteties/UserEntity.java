package restaurant.system.db.enteties;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Entity
public class UserEntity extends BaseEntity {

    @Column
    public String username;
    @Column(nullable = false)
    public String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role implements GrantedAuthority {
        ADMIN, BARMAN, CHEF, USER;

        @Override
        public String getAuthority() {
            return name();
        }
    }
}
