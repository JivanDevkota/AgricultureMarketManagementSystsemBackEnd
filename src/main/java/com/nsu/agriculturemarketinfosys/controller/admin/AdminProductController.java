package com.nsu.agriculturemarketinfosys.controller.admin;

import com.nsu.agriculturemarketinfosys.dto.FAQDTO;
import com.nsu.agriculturemarketinfosys.dto.ProductDTO;
import com.nsu.agriculturemarketinfosys.service.admin.faq.FAQService;
import com.nsu.agriculturemarketinfosys.service.admin.product.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    private final FAQService faqService;

    @PostMapping("/product")
    public ResponseEntity<ProductDTO>addProduct(@ModelAttribute ProductDTO productDTO)throws IOException {
    ProductDTO productDTO1=adminProductService.addProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO1);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>>getAllProducts(){
        List<ProductDTO>productDTOS=adminProductService.getAllProduct();
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>>getAllProductByName(@PathVariable String name){
        List<ProductDTO>productDTOS=adminProductService.getAllProductByName(name);
        return ResponseEntity.ok(productDTOS);
    }

    @DeleteMapping("product/{productId}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Long productId){
        boolean deleted=adminProductService.deleteProduct(productId);
        if (deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/faq/{productId}")
    public ResponseEntity<FAQDTO>postFAQ(@PathVariable Long productId,@RequestBody FAQDTO faqdto){
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId, faqdto));

    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDTO>getProductById(@PathVariable Long productId){
        ProductDTO productDTO=adminProductService.getProductById(productId);
        if (productDTO!=null){
            return ResponseEntity.ok(productDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDTO>updateProduct(@PathVariable Long productId,@ModelAttribute ProductDTO productDTO)throws IOException{
        ProductDTO updateProduct=adminProductService.updateProduct(productId,productDTO);
        if (updateProduct!=null){
            return ResponseEntity.ok(updateProduct);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
