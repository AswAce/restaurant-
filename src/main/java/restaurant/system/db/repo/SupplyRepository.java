package restaurant.system.db.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.system.db.enteties.SupplyEntity;

@Repository
public interface SupplyRepository extends JpaRepository<SupplyEntity, Long> {


//    DailySupplieEntity findByItemEntity_IdAndDate(Long itemEntityId, Date date);
}
