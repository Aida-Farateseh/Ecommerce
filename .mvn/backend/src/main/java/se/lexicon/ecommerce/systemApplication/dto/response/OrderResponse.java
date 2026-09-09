package se.lexicon.ecommerce.systemApplication.dto.response;


import se.lexicon.ecommerce.systemApplication.entity.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderResponse(
        Long id,
        Instant orderDate,
        OrderStatus orderStatus,
        List<OrderItemResponse> items
) {
}
