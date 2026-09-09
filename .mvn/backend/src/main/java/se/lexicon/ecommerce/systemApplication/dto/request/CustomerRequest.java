package se.lexicon.ecommerce.systemApplication.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        @NotBlank(message = "First name cannot be empty!")
        @Size(min = 2, max = 100)
        String firstName,

        @NotBlank(message = "Last name cannot be empty!")
        @Size(min = 2, max = 100)
        String lastName,

        @NotBlank(message = "Email cannot be empty!")
        @Email(message = "Invalid email format!")
        String email,

        @NotBlank(message = "Password cannot be empty!")
        @Size(min = 8, max = 20)
        String password,

        @NotBlank(message = "Street cannot be empty!")
        @Size(min = 2, max = 100)
        String street,

        @NotBlank(message = "City cannot be empty!")
        @Size(min = 2, max = 100)
        String city,

        @NotBlank(message = "Postal code cannot be empty!")
        @Size(min = 2, max = 100)
        String zipCode
) {
}
