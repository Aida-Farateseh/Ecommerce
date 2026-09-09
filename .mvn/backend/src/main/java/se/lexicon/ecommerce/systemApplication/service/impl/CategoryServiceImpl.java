package systemApplication.service.impl;


import systemApplication.dto.request.CategoryRequest;
import systemApplication.dto.response.CategoryResponse;
import systemApplication.entity.Category;
import systemApplication.exception.CategoryAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import systemApplication.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systemApplication.repository.CategoryRepository;
import systemApplication.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest categoryRequest) {
        if(categoryRequest.name() == null || categoryRequest.name().isBlank()) throw new IllegalArgumentException("Category name cannot be null!");

        // Create a new category after checking if it already exists
        if(categoryRepository.existsByNameIgnoreCase(categoryRequest.name())){
            throw new CategoryAlreadyExistsException("Category already exists!");
        }

        Category category = new Category();
        category.setName(categoryRequest.name());

        return categoryMapper.toResponse(
                categoryRepository.save(category)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }
}
