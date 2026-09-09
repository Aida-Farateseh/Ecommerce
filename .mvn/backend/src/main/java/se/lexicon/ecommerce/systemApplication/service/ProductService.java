package systemApplication.service;


import systemApplication.dto.request.ProductRequest;
import systemApplication.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest productRequest);



    List<ProductResponse> findAll();
    List<ProductResponse> searchByName(String name);

    ProductResponse findById(Long id);
    ProductResponse update (Long id, ProductRequest productRequest);
    void delete(Long id);
}
