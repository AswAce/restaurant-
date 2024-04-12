package restaurant.system.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.system.db.enteties.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
