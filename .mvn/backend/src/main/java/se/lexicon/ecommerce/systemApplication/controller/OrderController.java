package systemApplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import systemApplication.dto.request.OrderRequest;
import systemApplication.dto.response.OrderResponse;
import systemApplication.service.OrderService;

/**
 * REST systemApplication.systemApplication.service.service.controller responsible for handling order-related operations.
 * <p>
 * Provides endpoints for customers to place orders in the e-commerce system.
 * It receives order requests, validates the incoming data, and delegates
 * business logic execution to the {@link OrderService}.
 * </p>
 *
 * @author Manjula
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    /**
     * Service responsible for processing order-related business logic.
     */
    private final OrderService orderService;

    /**
     * Creates a new order based on the provided order details.
     * <p>
     * The request body is validated before processing. If the request is valid,
     * the order is placed successfully and the created order details are returned
     * with HTTP status {@code 201 Created}.
     * </p>
     *
     * @param orderRequest the order information received from the client
     * @return a {@link ResponseEntity} containing the created order details
     * and HTTP status {@code 201 Created}
     */
    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(
            @Valid @RequestBody OrderRequest orderRequest
    ) {

        OrderResponse orderResponse = orderService.placeOrder(orderRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderResponse);
    }
}