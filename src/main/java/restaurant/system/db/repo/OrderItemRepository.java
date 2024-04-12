package restaurant.system.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.system.db.enteties.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
