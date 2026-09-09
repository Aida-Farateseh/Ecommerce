package se.lexicon.ecommerce.systemApplication.service;


import se.lexicon.ecommerce.systemApplication.dto.request.PromotionRequest;
import se.lexicon.ecommerce.systemApplication.dto.response.PromotionResponse;
import se.lexicon.ecommerce.systemApplication.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface PromotionService {
    PromotionResponse create(PromotionRequest promotionRequest);
    List<PromotionResponse> getActivePromotions();
    BigDecimal calculateDiscount(Product product);
}
