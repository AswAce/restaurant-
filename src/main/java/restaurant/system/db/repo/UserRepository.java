package restaurant.system.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.system.db.enteties.UserEntity;
import restaurant.system.db.enums.RoleTypes;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByRole(RoleTypes type);
}
