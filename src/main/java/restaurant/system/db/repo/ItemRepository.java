package restaurant.system.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurant.system.db.enteties.ItemEntity;
import restaurant.system.db.enums.ItemTypesSpecifications;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findItemEntityByName(String name);

    List<ItemEntity> findAllBySpecialType( ItemTypesSpecifications itemSpecification);

    @Query("SELECT i FROM ItemEntity i JOIN i.supplyEntities s WHERE i.specialType = :specType AND s.date = CURRENT_DATE AND s.quantity > 0")
    List<ItemEntity> findAllBySpecialTypeAndTodaySupply(@Param("specType") ItemTypesSpecifications itemSpecification);
}