package com.nsu.agriculturemarketinfosys.service.admin.category;

import com.nsu.agriculturemarketinfosys.dto.CategoryDTO;
import com.nsu.agriculturemarketinfosys.entity.Category;
import com.nsu.agriculturemarketinfosys.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryDTO categoryDTO){
        Category category=new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return categoryRepository.save(category);
    }

    public List<Category>getAllCategories(){
        return categoryRepository.findAll();
    }
}
