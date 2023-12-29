package restaurant.system.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restaurant.system.db.enteties.TableBillEntity;
@Repository
public interface BillRepository extends JpaRepository<TableBillEntity, Long> {
}
