package se.lexicon.ecommerce.systemApplication.service.impl;

import se.lexicon.ecommerce.systemApplication.dto.request.CustomerRequest;
import se.lexicon.ecommerce.systemApplication.dto.response.CustomerResponse;
import se.lexicon.ecommerce.systemApplication.entity.Address;
import se.lexicon.ecommerce.systemApplication.entity.Customer;
import se.lexicon.ecommerce.systemApplication.exception.EmailAlreadyExistsException;
import se.lexicon.ecommerce.systemApplication.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import se.lexicon.ecommerce.systemApplication.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ecommerce.systemApplication.repository.CustomerRepository;
import se.lexicon.ecommerce.systemApplication.service.CustomerService;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerResponse register(CustomerRequest customerRequest) {
        if(customerRequest == null) throw new IllegalArgumentException("CustomerRequest cannot be null!");

        // Check if email already exists
        if(customerRepository.existsByEmail(customerRequest.email())){
            throw new EmailAlreadyExistsException("Email already exists!"+ customerRequest.email());
        }

        Customer customer = customerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer id not found with id"+ id));

        return customerMapper.toResponse(customer);
    }

    @Transactional
    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer id not found with id"+ id));

        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer.setEmail(customerRequest.email());
        customer.setPassword(customerRequest.password());

        Address address = customer.getAddress();

        if (address == null) {
            address = new Address();
        }
        address.setStreet(customerRequest.street());
        address.setCity(customerRequest.city());
        address.setZipCode(customerRequest.zipCode());

        customer.setAddress(address);

        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(updatedCustomer);
    }
}
