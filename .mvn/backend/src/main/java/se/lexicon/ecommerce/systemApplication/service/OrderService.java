package systemApplication.service;

import systemApplication.dto.request.OrderRequest;
import systemApplication.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
