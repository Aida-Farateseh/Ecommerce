package systemApplication.mapper;

import systemApplication.dto.request.ProductRequest;
import systemApplication.dto.response.ProductResponse;
import systemApplication.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product){
        if(product == null) throw new IllegalArgumentException("Product cannot be null");
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getStock(),
                product.getPrice(),
                product.getCategory().getName()
        );
    }

    public Product toEntity(ProductRequest productRequest){

        if(productRequest == null) throw new IllegalArgumentException("ProductRequest cannot be null!");

        Product product = new Product();
        product.setName(productRequest.name());
        product.setStock(productRequest.stock());
        product.setPrice(productRequest.price());

        return product;
    }

}
