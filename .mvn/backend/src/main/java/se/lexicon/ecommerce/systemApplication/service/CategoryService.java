package se.lexicon.ecommerce.systemApplication.service;


import se.lexicon.ecommerce.systemApplication.dto.request.CategoryRequest;
import se.lexicon.ecommerce.systemApplication.dto.response.CategoryResponse;


import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest categoryRequest);



    List<CategoryResponse> findAll();
}
