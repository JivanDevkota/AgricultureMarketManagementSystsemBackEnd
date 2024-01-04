package com.nsu.agriculturemarketinfosys.service.customer;

import com.nsu.agriculturemarketinfosys.dto.ProductDTO;
import com.nsu.agriculturemarketinfosys.dto.ProductDetailDTO;
import com.nsu.agriculturemarketinfosys.entity.Category;
import com.nsu.agriculturemarketinfosys.entity.FAQ;
import com.nsu.agriculturemarketinfosys.entity.Product;
import com.nsu.agriculturemarketinfosys.entity.Review;
import com.nsu.agriculturemarketinfosys.repository.CategoryRepository;
import com.nsu.agriculturemarketinfosys.repository.FAQRepository;
import com.nsu.agriculturemarketinfosys.repository.ProductRepository;
import com.nsu.agriculturemarketinfosys.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{

    private final ProductRepository productRepository;
    private final FAQRepository faqRepository;
    private final ReviewRepository reviewRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProduct(){
        List<Product>products=productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDTO>searchByProductTitle(String name){
        List<Product>products=productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public ProductDetailDTO getProductDetailById(Long productId){
        Optional<Product>optionalProduct=productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            List<FAQ>faqList=faqRepository.findAllByProductId(productId);
            List<Review>reviewList=reviewRepository.findAllByProductId(productId);

            ProductDetailDTO productDetailDTO=new ProductDetailDTO();
            productDetailDTO.setProductDTO(optionalProduct.get().getDto());
            productDetailDTO.setFaqdtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
            productDetailDTO.setReviewDTOList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));

            return productDetailDTO;
        }
        return null;
    }

    public Page<ProductDTO> getProductsByCategory(String name, Pageable pageable) {
        Category selectedCategory = categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category not found: " + name));

        Page<Product> productsByCategory = productRepository.findByCategory(selectedCategory, pageable);

        return productsByCategory.map(Product::getDto);
    }

}
