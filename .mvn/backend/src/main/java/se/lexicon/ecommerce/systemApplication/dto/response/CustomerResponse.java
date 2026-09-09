package se.lexicon.ecommerce.systemApplication.dto.response;
public record CustomerResponse(
        Long id,
        String fullName,
        String email,
        AddressResponse addressResponse
) {
}
