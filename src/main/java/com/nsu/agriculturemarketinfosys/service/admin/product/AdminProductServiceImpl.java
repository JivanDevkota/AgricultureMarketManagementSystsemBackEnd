package com.nsu.agriculturemarketinfosys.service.admin.product;

import com.nsu.agriculturemarketinfosys.dto.ProductDTO;
import com.nsu.agriculturemarketinfosys.entity.Category;
import com.nsu.agriculturemarketinfosys.entity.Product;
import com.nsu.agriculturemarketinfosys.repository.CategoryRepository;
import com.nsu.agriculturemarketinfosys.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductDTO addProduct(ProductDTO productDTO)throws IOException {
        Product product=new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImg(productDTO.getImg().getBytes());

//        Category category=categoryRepository.findById(productDTO.getCategoryId()).orElseThrow();
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found for ID: " + productDTO.getCategoryId()));

        product.setCategory(category);
        return productRepository.save(product).getDto();

    }

    public List<ProductDTO>getAllProduct(){
        List<Product>products=productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDTO>getAllProductByName(String name){
        List<Product>products=productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id){
        Optional<Product>optionalProduct=productRepository.findById(id);
        if (optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProductDTO getProductById(Long productId){
        Optional<Product>optionalProduct=productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            return optionalProduct.get().getDto();
        }else {
            return null;
        }
    }

    public ProductDTO updateProduct(Long productId,ProductDTO productDTO)throws IOException{
        Optional<Product>optionalProduct=productRepository.findById(productId);
        Optional<Category>optionalCategory=categoryRepository.findById(productDTO.getCategoryId());

        if (optionalProduct.isPresent() &&optionalCategory.isPresent()){
            Product product=optionalProduct.get();

            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setCategory(optionalCategory.get());

            if (productDTO.getImg() !=null){
                product.setImg(productDTO.getImg().getBytes());
            }
            return productRepository.save(product).getDto();
        }else {
            return null;
        }
    }

}
