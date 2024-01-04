package com.nsu.agriculturemarketinfosys.service.customer.wishlist;

import com.nsu.agriculturemarketinfosys.dto.WishlistDTO;
import com.nsu.agriculturemarketinfosys.entity.Product;
import com.nsu.agriculturemarketinfosys.entity.User;
import com.nsu.agriculturemarketinfosys.entity.Wishlist;
import com.nsu.agriculturemarketinfosys.repository.ProductRepository;
import com.nsu.agriculturemarketinfosys.repository.UserRepository;
import com.nsu.agriculturemarketinfosys.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WishlistRepository wishlistRepository;

    public WishlistDTO addProductToWishlist(WishlistDTO wishlistDTO){
        Optional<Product>optionalProduct=productRepository.findById(wishlistDTO.getProductId());
        Optional<User>optionalUser=userRepository.findById(wishlistDTO.getUserId());
        if (optionalProduct.isPresent() && optionalUser.isPresent()){
            Wishlist wishlist=new Wishlist();
            wishlist.setProduct(optionalProduct.get());
            wishlist.setUser(optionalUser.get());

            return wishlistRepository.save(wishlist).getWishlistDto();
        }
        return null;
    }

    public List<WishlistDTO>getWishlistByUserId(Long userId){
        return wishlistRepository.findAllByUserId(userId).stream().map(Wishlist::getWishlistDto).collect(Collectors.toList());

    }
}
