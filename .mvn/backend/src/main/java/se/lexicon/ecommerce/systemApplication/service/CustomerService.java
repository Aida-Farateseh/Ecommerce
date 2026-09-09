package systemApplication.service;


import systemApplication.dto.request.CustomerRequest;
import systemApplication.dto.response.CustomerResponse;


public interface CustomerService {

    CustomerResponse register(CustomerRequest customerRequest);
    CustomerResponse findById(Long id);
    CustomerResponse update(Long id, CustomerRequest customerRequest);


}
