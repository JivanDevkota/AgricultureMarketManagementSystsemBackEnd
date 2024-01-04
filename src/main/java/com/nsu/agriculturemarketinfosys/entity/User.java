package com.nsu.agriculturemarketinfosys.entity;

import com.nsu.agriculturemarketinfosys.enums.UserRole;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String name;

    private String password;

    private UserRole role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[]img;


}
