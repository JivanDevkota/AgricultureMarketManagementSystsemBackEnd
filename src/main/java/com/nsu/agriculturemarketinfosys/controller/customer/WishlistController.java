package com.nsu.agriculturemarketinfosys.controller.customer;

import com.nsu.agriculturemarketinfosys.dto.WishlistDTO;
import com.nsu.agriculturemarketinfosys.service.customer.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/wishlist")
    public ResponseEntity<?>addProductToWishlist(@RequestBody WishlistDTO wishlistDTO){
        WishlistDTO postedWishlistDto=wishlistService.addProductToWishlist(wishlistDTO);

        if (postedWishlistDto ==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);

    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDTO>>getWishlistByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(wishlistService.getWishlistByUserId(userId));
    }
}
