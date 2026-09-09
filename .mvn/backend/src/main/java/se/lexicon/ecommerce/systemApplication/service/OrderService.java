package se.lexicon.ecommerce.systemApplication.service;

import se.lexicon.ecommerce.systemApplication.dto.request.OrderRequest;
import se.lexicon.ecommerce.systemApplication.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
