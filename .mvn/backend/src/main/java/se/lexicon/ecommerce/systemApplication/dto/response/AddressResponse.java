package se.lexicon.ecommerce.systemApplication.dto.response;

public record AddressResponse(
        Long id,
        String street,
        String city,
        String zipCode
) {
}
