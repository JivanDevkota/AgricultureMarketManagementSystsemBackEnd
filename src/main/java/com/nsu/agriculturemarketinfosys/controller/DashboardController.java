package com.nsu.agriculturemarketinfosys.controller;

import com.nsu.agriculturemarketinfosys.dto.ProductDTO;
import com.nsu.agriculturemarketinfosys.repository.CategoryRepository;
import com.nsu.agriculturemarketinfosys.repository.ProductRepository;
import com.nsu.agriculturemarketinfosys.service.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/global")
@RequiredArgsConstructor
public class DashboardController {

    private final CustomerProductService customerProductService;

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


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



    @GetMapping("/products/{category}/{page}")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategoryAndPage(
            @PathVariable String category, @PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 20); // Set the page size as 20

        Page<ProductDTO> products = customerProductService.getProductsByCategory(category, pageable);
        return ResponseEntity.ok(products);
    }
}
