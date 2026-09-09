package systemApplication.service.impl;


import systemApplication.entity.*;
import systemApplication.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import systemApplication.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import systemApplication.dto.request.OrderItemRequest;
import systemApplication.dto.request.OrderRequest;
import systemApplication.dto.response.OrderResponse;
import systemApplication.repository.CustomerRepository;
import systemApplication.repository.OrderRepository;
import systemApplication.repository.ProductRepository;
import systemApplication.service.OrderService;
import systemApplication.service.PromotionService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final PromotionService promotionService;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        if (orderRequest == null || orderRequest.items() == null || orderRequest.items().isEmpty()) {
            throw new IllegalArgumentException("OrderRequest or items cannot be null/empty");
        }

        // 1. Find customer
        Customer customer = customerRepository.findById(orderRequest.customerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found with id " + orderRequest.customerId()));

        // 2. Create order
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        // 3. Process items
        for (OrderItemRequest itemRequest : orderRequest.items()) {

            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Product not found with id " + itemRequest.productId()));

            // Stock validation
            if (product.getStock() < itemRequest.quantity()) {
                throw new IllegalArgumentException(
                        "Insufficient stock for product: " + product.getName());
            }

            // Discount calculation
            BigDecimal discount = promotionService.calculateDiscount(product);
            BigDecimal finalPrice = product.getPrice().subtract(discount);

            // Order item
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemRequest.quantity());
            item.setPriceAtPurchase(finalPrice);

            orderItems.add(item);

            // Update stock
            product.setStock(product.getStock() - itemRequest.quantity());

            // Total calculation
            totalAmount = totalAmount.add(
                    finalPrice.multiply(BigDecimal.valueOf(itemRequest.quantity()))
            );
        }

        // 4. Attach items + total
        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        // 5. Save order
        Order savedOrder = orderRepository.save(order);

        // 6. Response mapping
        return orderMapper.toResponse(savedOrder);
    }
}