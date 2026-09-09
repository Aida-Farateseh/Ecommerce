package se.lexicon.ecommerce.systemApplication.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Category name cannot be empty!")
        String name
) {
}
