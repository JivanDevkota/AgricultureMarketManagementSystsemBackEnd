package com.nsu.agriculturemarketinfosys.service.customer.review;

import com.nsu.agriculturemarketinfosys.dto.OrderedProductResponseDTO;
import com.nsu.agriculturemarketinfosys.dto.ProductDTO;
import com.nsu.agriculturemarketinfosys.dto.ReviewDTO;
import com.nsu.agriculturemarketinfosys.entity.*;
import com.nsu.agriculturemarketinfosys.repository.OrderRepository;
import com.nsu.agriculturemarketinfosys.repository.ProductRepository;
import com.nsu.agriculturemarketinfosys.repository.ReviewRepository;
import com.nsu.agriculturemarketinfosys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    public OrderedProductResponseDTO getOrderedProductsDetailsByOrderId(Long orderId){
        Optional<Order>optionalOrder=orderRepository.findById(orderId);
        OrderedProductResponseDTO orderedProductResponseDTO=new OrderedProductResponseDTO();
        if (optionalOrder.isPresent()){
            orderedProductResponseDTO.setOrderAmount(optionalOrder.get().getAmount());
            List<ProductDTO>productDTOList=new ArrayList<>();
            for (CartItem cartItems: optionalOrder.get().getCartItems()){
                ProductDTO productDTO=new ProductDTO();

                productDTO.setId(cartItems.getProduct().getId());
                productDTO.setName(cartItems.getProduct().getName());
                productDTO.setPrice(cartItems.getPrice());
                productDTO.setQuantity(cartItems.getQuantity());

                productDTO.setByteImg(cartItems.getProduct().getImg());

                productDTOList.add(productDTO);
            }
            orderedProductResponseDTO.setProductDTOList(productDTOList);
        }
        return orderedProductResponseDTO;
    }

    public ReviewDTO giveReview(ReviewDTO reviewDTO)throws IOException {
        Optional<Product>optionalProduct=productRepository.findById(reviewDTO.getProductId());
        Optional<User>optionalUser=userRepository.findById(reviewDTO.getUserId());

        if (optionalProduct.isPresent() && optionalUser.isPresent()){
            Review review=new Review();

            review.setRating(reviewDTO.getRating());
            review.setDescription(reviewDTO.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDTO.getImg().getBytes());

            return reviewRepository.save(review).getDto();
        }
        return null;
    }
}
