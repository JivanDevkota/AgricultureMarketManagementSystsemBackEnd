package com.nsu.agriculturemarketinfosys.dto;

import lombok.Data;

@Data
public class PlaceOrderDTO {

    private Long userId;
    private String address;
    private String orderDescription;
}
