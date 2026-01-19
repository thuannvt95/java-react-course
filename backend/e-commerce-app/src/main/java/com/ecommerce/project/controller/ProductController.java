package com.ecommerce.project.controller;

import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts(@RequestParam(name = "productName", required = false) String productName,
                                            @RequestParam(name = "categoryId", required = false) String categoryId,
                                            @RequestParam(name = "categoryName", required = false) String categoryName,
                                            Pageable pageable) {
        return new ResponseEntity<>(productService.getAllProducts(productName,
                                                                  categoryId,
                                                                  categoryName,
                                                                  pageable),
                                    HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id,
                                           @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProduct(id, productDTO), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}/image", method = RequestMethod.POST)
    public ResponseEntity<?> uploadProductImage(@PathVariable("id") String id,
                                                @RequestParam("image") MultipartFile image) {
        return new ResponseEntity<>(productService.uploadProductImage(id, image), HttpStatus.OK);
    }
}
