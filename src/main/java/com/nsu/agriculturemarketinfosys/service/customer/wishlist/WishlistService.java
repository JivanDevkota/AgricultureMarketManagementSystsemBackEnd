package com.nsu.agriculturemarketinfosys.service.customer.wishlist;

import com.nsu.agriculturemarketinfosys.dto.WishlistDTO;

import java.util.List;

public interface WishlistService {

    WishlistDTO addProductToWishlist(WishlistDTO wishlistDTO);

    List<WishlistDTO> getWishlistByUserId(Long userId);
}
