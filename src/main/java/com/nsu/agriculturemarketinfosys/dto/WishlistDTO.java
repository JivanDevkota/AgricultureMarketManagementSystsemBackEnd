package com.nsu.agriculturemarketinfosys.dto;

import lombok.Data;

@Data
public class WishlistDTO {

    private Long userId;

    private Long productId;

    private Long id;

    private String productName;
    private String productDescription;
    private byte[]returnedImg;
    private Long price;
}
