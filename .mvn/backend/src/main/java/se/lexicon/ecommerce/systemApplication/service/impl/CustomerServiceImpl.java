package systemApplication.service.impl;

import systemApplication.dto.request.CustomerRequest;
import systemApplication.dto.response.CustomerResponse;
import systemApplication.entity.Address;
import systemApplication.entity.Customer;
import systemApplication.exception.EmailAlreadyExistsException;
import systemApplication.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import systemApplication.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systemApplication.repository.CustomerRepository;
import systemApplication.service.CustomerService;


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
