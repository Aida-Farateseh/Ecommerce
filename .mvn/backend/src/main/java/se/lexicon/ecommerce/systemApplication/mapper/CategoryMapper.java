package systemApplication.mapper;

import systemApplication.dto.response.CategoryResponse;
import systemApplication.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category){
        if(category == null) throw new IllegalArgumentException("Category cannot be null!");

        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }

}
