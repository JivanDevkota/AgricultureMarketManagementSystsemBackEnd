package com.nsu.agriculturemarketinfosys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsu.agriculturemarketinfosys.dto.ProductDTO;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long price;

    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[]img;


    //we want many product in one category
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public ProductDTO getDto(){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(id);
        productDTO.setName(name);
        productDTO.setPrice(price);
        productDTO.setDescription(description);
        productDTO.setByteImg(img);
        productDTO.setCategoryId(category.getCategory_id());
        productDTO.setCategoryName(category.getName());
        return productDTO;
    }
}
