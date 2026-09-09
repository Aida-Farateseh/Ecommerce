package se.lexicon.ecommerce.systemApplication.service.impl;
import se.lexicon.ecommerce.systemApplication.dto.request.ProductRequest;
import se.lexicon.ecommerce.systemApplication.dto.response.ProductResponse;
import se.lexicon.ecommerce.systemApplication.entity.Category;
import se.lexicon.ecommerce.systemApplication.entity.Product;
import se.lexicon.ecommerce.systemApplication.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import se.lexicon.ecommerce.systemApplication.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ecommerce.systemApplication.repository.CategoryRepository;
import se.lexicon.ecommerce.systemApplication.repository.ProductRepository;
import se.lexicon.ecommerce.systemApplication.service.ProductService;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public ProductResponse create(ProductRequest productRequest) {
        if (productRequest == null) throw new IllegalArgumentException("Product Request cannot be empty!");

        Category category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category cannot found with "+ productRequest.categoryId()));

        Product product = productMapper.toEntity(productRequest);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> searchByName(String name) {

        if (name == null) throw new IllegalArgumentException("Name cannot be empty!");

        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id));
        return productMapper.toResponse(product);

    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest productRequest) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Category category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + productRequest.categoryId()));

        existing.setName(productRequest.name());
        existing.setPrice(productRequest.price());
        existing.setCategory(category);

        Product updated = productRepository.save(existing);
        return productMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete (Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                     "Product not found with id: " + id));
        productRepository.delete(product);
    }
}
