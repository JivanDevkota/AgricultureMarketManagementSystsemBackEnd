package com.nsu.agriculturemarketinfosys.entity;

import com.nsu.agriculturemarketinfosys.dto.WishlistDTO;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public WishlistDTO getWishlistDto(){
        WishlistDTO wishlistDTO=new WishlistDTO();
        wishlistDTO.setId(id);
        wishlistDTO.setProductId(product.getId());
        wishlistDTO.setReturnedImg(product.getImg());
        wishlistDTO.setProductName(product.getName());
        wishlistDTO.setProductDescription(product.getDescription());
        wishlistDTO.setPrice(product.getPrice());
        wishlistDTO.setUserId(user.getId());

        return wishlistDTO;
    }
}
