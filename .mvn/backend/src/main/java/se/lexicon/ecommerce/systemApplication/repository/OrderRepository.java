package systemApplication.repository;

import systemApplication.entity.Order;
import systemApplication.entity.OrderStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /* Required Queries */
    // Find all orders belonging to a specific customer ID.
    List<Order> findByCustomer_Id(Long customerId);

    // Find orders by status and use a strategy to avoid the N+1 problem (loading order items in the same query).
    @EntityGraph(attributePaths = "items")
    List<Order> findByStatus(OrderStatus status);

    /* Advanced Queries */
    // Find orders created after a specific date.
    List<Order> findByOrderDateAfter(Instant date);

    // Find orders created between two dates.
    List<Order> findByOrderDateBetween(Instant startDate, Instant endDate);

    // Find orders that contain a specific product.
    @Query("""
        SELECT DISTINCT o
        FROM Order o
        JOIN o.items i
        JOIN i.product p
        WHERE p.id = :productId
        """)
    List<Order> findByProductId(@Param("productId") Long productId);

    // Count orders by status.
    long countByStatus(OrderStatus status);

    // Find orders by customer ID and status.
    List<Order> findByCustomer_IdAndStatus(Long customerId, OrderStatus orderStatus);

    @EntityGraph(attributePaths = {"customer", "items", "items.product"})
    List<Order> findAll();
}
