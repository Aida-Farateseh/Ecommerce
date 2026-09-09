package systemApplication.repository;

import systemApplication.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /* Required Queries */
    // Find a category by name (case-insensitive)
    Optional<Category> findByNameIgnoreCase(String name);

    // Check if a category exists by name
    boolean existsByNameIgnoreCase(String name);

    /* Advanced Queries */
    // Find categories whose name contains a given keyword
    List<Category> findByNameContainingIgnoreCase(String keyword);

    Optional<Category> findByName(String name);

    // Count how many categories exist
    // count() is inherited from JpaRepository
}
