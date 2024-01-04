package com.nsu.agriculturemarketinfosys.controller.admin;

import com.nsu.agriculturemarketinfosys.dto.CategoryDTO;
import com.nsu.agriculturemarketinfosys.entity.Category;
import com.nsu.agriculturemarketinfosys.service.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCatgoryController {

    private final CategoryService categoryService;


    @PostMapping("/category")
    public ResponseEntity<Category>createCateory(@RequestBody CategoryDTO categoryDTO){
        System.out.println("Authenticated user: " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("User roles: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        System.out.println("Received request in AdminCatgoryController");
        Category category=categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("/categories")
   public ResponseEntity<List<Category>>getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
