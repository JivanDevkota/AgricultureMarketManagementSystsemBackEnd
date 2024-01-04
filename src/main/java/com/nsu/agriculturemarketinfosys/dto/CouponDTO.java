package com.nsu.agriculturemarketinfosys.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CouponDTO {
    private Long id;

    private String name;
    private String code;

    private Long discount;

    private Date expirationDate;
}
