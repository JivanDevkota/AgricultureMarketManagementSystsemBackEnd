package com.nsu.agriculturemarketinfosys.dto;

import com.nsu.agriculturemarketinfosys.entity.Product;
import com.nsu.agriculturemarketinfosys.entity.User;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
public class ReviewDTO {

    private Long id;

    private Long rating;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;

    private Long userId;

    private Long productId;

    private String username;

}
