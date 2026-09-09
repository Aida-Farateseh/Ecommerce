package se.lexicon.ecommerce.systemApplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.lexicon.ecommerce.systemApplication.dto.request.ProductRequest;
import se.lexicon.ecommerce.systemApplication.dto.response.ProductResponse;
import se.lexicon.ecommerce.systemApplication.service.ProductService;
import java.util.List;

/**
 * REST systemApplication.systemApplication.service.service.controller for managing products.
 * Provides endpoints for creating, retrieving, updating and deleting products.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Creates a new product.
     *
     * @param request the product details to create
     * @return the created product with HTTP 201 status
     */

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody ProductRequest request) {
        ProductResponse response = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retrieves all products.
     * @return list of all products with HTTP 200 status
     */

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    /**
     * Searches products by name (case-insensitive)
     *
     * @param name the search term to filter products by name
     * @return list of matching products with HTTP 200 status
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchByName(
            @RequestParam String name ) {
        List<ProductResponse> results = productService.searchByName(name);
        return ResponseEntity.ok(results);
    }

    /**
     * Finds product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the found product with HTTP 200 status
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        ProductResponse response = productService.findById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing product.
     *
     * @param id the ID of the product to update
     * @param request the new product details
     * @return the updates product with HTTP 200 status
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        ProductResponse updated = productService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return HTTP 204 no Content on successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
