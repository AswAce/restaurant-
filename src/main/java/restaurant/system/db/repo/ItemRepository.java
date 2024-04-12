package restaurant.system.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.system.db.enteties.ItemEntity;
import restaurant.system.db.enums.ItemTypesSpecifications;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findItemEntityByName(String name);

    List<ItemEntity> findAllBySpecialType( ItemTypesSpecifications itemSpecification);
}
//    List<ItemEntity> findAllByItemTypeSpecification(ItemTypesSpecifications itemTypeSpecification);}
