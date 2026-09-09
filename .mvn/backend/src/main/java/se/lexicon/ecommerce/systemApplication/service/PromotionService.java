package systemApplication.service;


import systemApplication.dto.request.PromotionRequest;
import systemApplication.dto.response.PromotionResponse;
import systemApplication.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface PromotionService {
    PromotionResponse create(PromotionRequest promotionRequest);
    List<PromotionResponse> getActivePromotions();
    BigDecimal calculateDiscount(Product product);
}
