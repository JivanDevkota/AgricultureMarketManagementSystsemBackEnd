package com.nsu.agriculturemarketinfosys.repository;

import com.nsu.agriculturemarketinfosys.entity.Category;
import com.nsu.agriculturemarketinfosys.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
//    Page<Product> findByCategory(String category, Pageable pageable);

    List<Product>findAllByNameContaining(String title);

    Page<Product> findByCategory(Category category, Pageable pageable);
}
