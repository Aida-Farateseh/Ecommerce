package se.lexicon.ecommerce.systemApplication.mapper;

import se.lexicon.ecommerce.systemApplication.dto.request.CustomerRequest;
import se.lexicon.ecommerce.systemApplication.dto.response.AddressResponse;
import se.lexicon.ecommerce.systemApplication.dto.response.CustomerResponse;
import se.lexicon.ecommerce.systemApplication.entity.Address;
import se.lexicon.ecommerce.systemApplication.entity.Customer;
import org.springframework.stereotype.Component;

@Component // Manage the systemApplication.systemApplication.service.service.mapper as a bean and inject it into other classes
public class CustomerMapper {

    public CustomerResponse toResponse(Customer customer){

        if(customer == null) throw new IllegalArgumentException("Customer cannot be null!");
        AddressResponse addressResponse = null;
        if(customer.getAddress() != null){
            addressResponse = new AddressResponse(
                    customer.getAddress().getId(),
                    customer.getAddress().getStreet(),
                    customer.getAddress().getCity(),
                    customer.getAddress().getZipCode()
            );
        }

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName()+" "+customer.getLastName(),
                customer.getEmail(),
                addressResponse
        );
    }

    public Customer toEntity(CustomerRequest customerRequest){

        if(customerRequest == null) throw new IllegalArgumentException("CustomerRequest cannot be null!");
        Address address = new Address();
        address.setStreet(customerRequest.street());
        address.setCity(customerRequest.city());
        address.setZipCode(customerRequest.zipCode());

        Customer customer = new Customer();
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer.setEmail(customerRequest.email());
        customer.setPassword(customerRequest.password());
        customer.setAddress(address);
        return customer;
    }

}
