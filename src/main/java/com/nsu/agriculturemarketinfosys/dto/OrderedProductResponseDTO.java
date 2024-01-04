package com.nsu.agriculturemarketinfosys.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderedProductResponseDTO {
    private List<ProductDTO>productDTOList;

    private Long orderAmount;
}
