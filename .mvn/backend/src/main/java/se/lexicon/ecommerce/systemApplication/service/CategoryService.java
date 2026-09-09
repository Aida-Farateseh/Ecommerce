package systemApplication.service;

import systemApplication.dto.request.CategoryRequest;
import systemApplication.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest categoryRequest);
    List<CategoryResponse> findAll();
}
