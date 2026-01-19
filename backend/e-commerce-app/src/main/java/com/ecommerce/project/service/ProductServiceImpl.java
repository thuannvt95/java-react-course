package com.ecommerce.project.service;

import com.ecommerce.project.exception.ApiException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FileService fileService;

    @Override
    public ProductResponse createProduct(ProductDTO productDTO) {
        if (!StringUtils.hasText(productDTO.getCategoryDTO().getCategoryId())) {
            throw new ApiException("Category ID is mandatory");
        }

        Optional<Category> foundCategory = categoryRepository.findById(productDTO.getCategoryDTO().getCategoryId());

        if (foundCategory.isEmpty()) {
            throw new ResourceNotFoundException("Category", "categoryId", productDTO.getCategoryDTO().getCategoryId());
        }

        Product product = modelMapper.map(productDTO, Product.class);
        double specialPrice = product.getPrice() - (product.getPrice() * product.getDiscount()) / 100;
        product.setSpecialPrice(specialPrice);

        productRepository.save(product);

        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public Page<ProductResponse> getAllProducts(String productName,
                                                String categoryId,
                                                String categoryName,
                                                Pageable pageable) {
        Page<Product> products = productRepository.findByKeyword(productName,
                                                                 categoryId,
                                                                 categoryName,
                                                                 pageable);
        if (products.isEmpty()) {
            throw new ApiException("No product found");
        }
        List<ProductResponse> productResponses = products.getContent()
                                                          .stream()
                                                          .map(c -> modelMapper.map(c, ProductResponse.class))
                                                          .toList();

        return new PageImpl<>(productResponses, products.getPageable(), products.getTotalElements());
    }

    @Override
    public ProductResponse updateProduct(String id, ProductDTO productDTO) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isEmpty()) {
            throw new ResourceNotFoundException("Product", "productId", id);
        }
        if (StringUtils.hasText(productDTO.getProductName())) {
            foundProduct.get().setProductName(productDTO.getProductName());
        }
        if (StringUtils.hasText(productDTO.getDescription())) {
            foundProduct.get().setDescription(productDTO.getDescription());
        }
        if (Objects.nonNull(productDTO.getPrice())) {
            foundProduct.get().setPrice(productDTO.getPrice());
        }
        if (Objects.nonNull(productDTO.getDiscount())) {
            foundProduct.get().setDiscount(productDTO.getDiscount());
        }
        if (Objects.nonNull(productDTO.getCategoryDTO()) && StringUtils.hasText(productDTO.getCategoryDTO().getCategoryId())) {
            Optional<Category> foundCategory = categoryRepository.findById(productDTO.getCategoryDTO().getCategoryId());
            if (foundCategory.isEmpty()) {
                throw new ResourceNotFoundException("Category", "categoryId", productDTO.getCategoryDTO().getCategoryId());
            }
            foundProduct.get().setCategory(foundCategory.get());
        }

        double specialPrice = foundProduct.get().getPrice() - (foundProduct.get().getPrice() * foundProduct.get().getDiscount()) / 100;
        foundProduct.get().setSpecialPrice(specialPrice);
        productRepository.save(foundProduct.get());
        return modelMapper.map(foundProduct.get(), ProductResponse.class);
    }

    @Override
    public void deleteProduct(String id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isEmpty()) {
            throw new ResourceNotFoundException("Product", "productId", id);
        }
        productRepository.delete(foundProduct.get());
    }

    @Override
    public ProductResponse uploadProductImage(String id, MultipartFile image) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isEmpty()) {
            throw new ResourceNotFoundException("Product", "productId", id);
        }
        String fileName = fileService.uploadImage("image/", image);
        foundProduct.get().setImage(fileName);

        productRepository.save(foundProduct.get());

        return modelMapper.map(foundProduct.get(), ProductResponse.class);
    }


}
