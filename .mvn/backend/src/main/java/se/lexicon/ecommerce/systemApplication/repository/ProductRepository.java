package systemApplication.repository;

import systemApplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /* Required Queries */
    //Find products by their category name.
    List<Product> findByCategory_Name(String categoryName);

    //Find products within a specific price range.
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    /* Advanced Queries */
    //Find products whose name contains a given keyword.
    List<Product> findByNameContainingIgnoreCase(String keyword);

    //Find products cheaper than a given price.
    List<Product> findByPriceLessThan(BigDecimal price);

    //Find products ordered by price (ascending or descending).
    List<Product> findProductByOrderByPriceAsc();

    //Count how many products exist in a specific category.
    long countByCategory_Id(Long id);

    //Find products by category ID.
    List<Product> findByCategory_Id(Long id);
}
