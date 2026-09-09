package se.lexicon.ecommerce.systemApplication.controller;


import se.lexicon.ecommerce.systemApplication.dto.request.CustomerRequest;
import se.lexicon.ecommerce.systemApplication.dto.response.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.ecommerce.systemApplication.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Controller", description = "APIs for managing customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Creates a new customer in the system.
     *
     * @param customerRequest the customer data used for registration
     * @return the created customer along with HTTP 201 Created
     */
    @PostMapping
    @Operation(summary = "Create a new customer")
    public ResponseEntity<CustomerResponse> register(@Valid @RequestBody CustomerRequest customerRequest){
        IO.println("Request body: "+customerRequest);

        CustomerResponse customerResponse = customerService.register(customerRequest);
        IO.println("Response body: "+ customerResponse);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerResponse);
    }

    /**
     * Retrieves a customer by its unique identifier.
     *
     * @param id the customer ID
     * @return the customer details
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID")
    public ResponseEntity<CustomerResponse> findById(@PathVariable @Positive Long id){
        IO.println("Id: "+id);

        CustomerResponse customerResponse = customerService.findById(id);
        IO.println("Response body: "+customerResponse);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerResponse);
    }

    /**
     * Updates an existing customer.
     *
     * @param id the ID of the customer to update
     * @param customerRequest the updated customer information
     * @return the updated customer details
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing customer")
    public  ResponseEntity<CustomerResponse> update(@PathVariable Long id, @Valid @RequestBody CustomerRequest customerRequest){
        IO.println("Id: "+id);

        CustomerResponse customerResponse = customerService.update(id, customerRequest);
        IO.println("Response body: "+customerResponse);
        return ResponseEntity
        .status(HttpStatus.OK)
                .body(customerResponse);
    }
}
