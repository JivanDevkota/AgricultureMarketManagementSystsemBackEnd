package com.nsu.agriculturemarketinfosys.service.admin.product;

import com.nsu.agriculturemarketinfosys.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDTO addProduct(ProductDTO productDTO)throws IOException;

    List<ProductDTO> getAllProduct();

    List<ProductDTO>getAllProductByName(String name);

    boolean deleteProduct(Long id);

    ProductDTO getProductById(Long productId);

    ProductDTO updateProduct(Long productId,ProductDTO productDTO)throws IOException;

}
