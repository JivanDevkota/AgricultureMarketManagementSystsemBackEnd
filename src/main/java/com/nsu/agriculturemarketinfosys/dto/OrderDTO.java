package com.nsu.agriculturemarketinfosys.dto;

import com.nsu.agriculturemarketinfosys.enums.OrderStatus;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private Long id;

    private String orderDescription;

    private Date date;

    private Long amount;

    private String address;

    private String payment;

    private OrderStatus orderStatus;

    private long totalAmount;

    private Long discout;

    private UUID trackingId;

    private String userName;
    private List<CartItemsDTO> cartItems;

    private String couponName;
}
