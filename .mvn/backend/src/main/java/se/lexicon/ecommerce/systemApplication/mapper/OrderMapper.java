package se.lexicon.ecommerce.systemApplication.mapper;

import se.lexicon.ecommerce.systemApplication.dto.response.OrderItemResponse;
import se.lexicon.ecommerce.systemApplication.dto.response.OrderResponse;
import se.lexicon.ecommerce.systemApplication.entity.Order;
import se.lexicon.ecommerce.systemApplication.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    private final ProductMapper productMapper;

    public OrderMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public OrderResponse toResponse(Order order){
        List<OrderItemResponse> items =
                order.getItems()
                        .stream()
                        .map(this::toOrderItemResponse)
                        .toList();

        return new OrderResponse(
                order.getId(),
                order.getOrderDate(),
                order.getStatus(),
                items
        );
    }

    private OrderItemResponse toOrderItemResponse(OrderItem item) {

        return new OrderItemResponse(
                item.getId(),
                item.getQuantity(),
                item.getPriceAtPurchase(),
                productMapper.toResponse(item.getProduct())
        );
    }

}
