package se.lexicon.ecommerce.systemApplication.service;


import se.lexicon.ecommerce.systemApplication.dto.request.CustomerRequest;
import se.lexicon.ecommerce.systemApplication.dto.response.CustomerResponse;


public interface CustomerService {

    CustomerResponse register(CustomerRequest customerRequest);
    CustomerResponse findById(Long id);
    CustomerResponse update(Long id, CustomerRequest customerRequest);


}
