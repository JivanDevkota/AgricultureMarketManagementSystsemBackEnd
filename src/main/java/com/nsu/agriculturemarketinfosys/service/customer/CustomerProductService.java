package com.nsu.agriculturemarketinfosys.service.customer;

import com.nsu.agriculturemarketinfosys.dto.ProductDTO;
import com.nsu.agriculturemarketinfosys.dto.ProductDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerProductService {

    List<ProductDTO> getAllProduct();

    List<ProductDTO>searchByProductTitle(String name);

    ProductDetailDTO getProductDetailById(Long productId);

    Page<ProductDTO> getProductsByCategory(String name, Pageable pageable);
}
