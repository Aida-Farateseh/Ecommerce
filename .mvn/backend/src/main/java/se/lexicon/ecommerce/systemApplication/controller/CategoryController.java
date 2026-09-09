package se.lexicon.ecommerce.systemApplication.controller;

import se.lexicon.ecommerce.systemApplication.dto.request.CategoryRequest;
import se.lexicon.ecommerce.systemApplication.dto.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.ecommerce.systemApplication.service.CategoryService;

import java.util.List;


//POST: Create a new category. Status: 201 Created.
//GET: List all categories. Status: 200 OK.
@Tag(name = "CategoryController", description = "ApIs for managing categories")
@RequestMapping("/api/v1/categories")
@RestController

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
   }





    /**
     * Creates a new category.
     *
     * @param request the category request containing the name
     * @return ResponseEntity containing the created CategoryResponse with status 201 Created
     */

    @PostMapping
    @Operation(summary = "Create a new category")
    @ApiResponse(responseCode = "201", description = "Category created successfully")

    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse categoryResponse = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    /**
     * Retrieves all categories.
     *
     * @return ResponseEntity containing list of CategoryResponse with status 200 OK
     */
    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<CategoryResponse>>findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }



}
