package com.nsu.agriculturemarketinfosys.service.admin.category;

import com.nsu.agriculturemarketinfosys.dto.CategoryDTO;
import com.nsu.agriculturemarketinfosys.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    List<Category> getAllCategories();
}
