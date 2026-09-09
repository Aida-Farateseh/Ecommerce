package systemApplication.dto.response;



import systemApplication.entity.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderResponse(
        Long id,
        Instant orderDate,
        OrderStatus orderStatus,
        List<OrderItemResponse> items
) {
}
