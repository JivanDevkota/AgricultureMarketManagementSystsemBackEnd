package com.nsu.agriculturemarketinfosys.dto;

import com.nsu.agriculturemarketinfosys.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
//    private Set<String> roles;
}
