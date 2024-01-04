package com.nsu.agriculturemarketinfosys.controller.customer;


import com.nsu.agriculturemarketinfosys.dto.ProductDTO;
import com.nsu.agriculturemarketinfosys.dto.ProductDetailDTO;
import com.nsu.agriculturemarketinfosys.service.customer.CustomerProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO>productDTOS=customerProductService.getAllProduct();
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>>getAllProductByName(@PathVariable String name){
        List<ProductDTO>productDTOS=customerProductService.searchByProductTitle(name);
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDetailDTO>getProductDetailById(@PathVariable Long productId){
        ProductDetailDTO productDetailDTO=customerProductService.getProductDetailById(productId);
        if (productDetailDTO ==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDetailDTO);
    }
}
