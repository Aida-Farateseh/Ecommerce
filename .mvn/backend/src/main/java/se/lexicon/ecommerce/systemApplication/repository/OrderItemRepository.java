package se.lexicon.ecommerce.systemApplication.repository;

import se.lexicon.ecommerce.systemApplication.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /* Advanced Queries */
    //Find all order items belonging to a specific order ID.
    List<OrderItem> findByOrder_Id(Long orderId);

    //Find all order items for a specific product ID.
    List<OrderItem> findByProduct_Id(Long productId);

    //Find order items where quantity is greater than a given value.
    List<OrderItem> findByQuantityGreaterThan(Integer quantity);
}
