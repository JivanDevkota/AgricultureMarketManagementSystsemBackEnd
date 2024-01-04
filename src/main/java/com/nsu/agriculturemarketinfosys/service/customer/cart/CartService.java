package com.nsu.agriculturemarketinfosys.service.customer.cart;

import com.nsu.agriculturemarketinfosys.dto.AddProductInCartDTO;
import com.nsu.agriculturemarketinfosys.dto.OrderDTO;
import com.nsu.agriculturemarketinfosys.dto.PlaceOrderDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDTO addProductInCartDTO);

    OrderDTO getCartByUserId(Long userId);

    OrderDTO applyCoupon(Long userId,String code);

    OrderDTO increaseProductQuantity(AddProductInCartDTO addProductInCartDTO);

    OrderDTO decreaseProductQuantity(AddProductInCartDTO addProductInCartDTO);

    OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO);

    List<OrderDTO> getMyPlacedOrders(Long userId);

    OrderDTO searchOrderByTrackingId(UUID trackingId);
}
