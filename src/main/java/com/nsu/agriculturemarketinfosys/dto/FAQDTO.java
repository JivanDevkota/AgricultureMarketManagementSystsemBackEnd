package com.nsu.agriculturemarketinfosys.dto;

import lombok.Data;

@Data
public class FAQDTO {
    private Long id;

    private String question;

    private String answer;

    private Long productId;
}
