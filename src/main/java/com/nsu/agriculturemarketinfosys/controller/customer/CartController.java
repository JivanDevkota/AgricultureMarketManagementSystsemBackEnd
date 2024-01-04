package com.nsu.agriculturemarketinfosys.controller.customer;

import com.nsu.agriculturemarketinfosys.dto.AddProductInCartDTO;
import com.nsu.agriculturemarketinfosys.dto.OrderDTO;
import com.nsu.agriculturemarketinfosys.dto.PlaceOrderDTO;
import com.nsu.agriculturemarketinfosys.exception.ValidationException;
import com.nsu.agriculturemarketinfosys.service.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?>addProductToCart(@RequestBody AddProductInCartDTO addProductInCartDTO){
        return cartService.addProductToCart(addProductInCartDTO);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?>getCartByUserId(@PathVariable Long userId){
        OrderDTO orderDTO=cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    @GetMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?>applyCoupon(@PathVariable Long userId, @PathVariable String code){
        try {
            OrderDTO orderDTO=cartService.applyCoupon(userId,code);
            return ResponseEntity.ok(orderDTO);
        }catch (ValidationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/addition")
    public ResponseEntity<OrderDTO>increaseProductQuantity(@RequestBody AddProductInCartDTO addProductInCartDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductInCartDTO));
    }

    @PostMapping("/deduction")
    public ResponseEntity<OrderDTO>decreaseProductQuantity(@RequestBody AddProductInCartDTO addProductInCartDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductInCartDTO));
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderDTO>placeOrder(@RequestBody PlaceOrderDTO  placeOrderDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDTO));
    }

    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<OrderDTO>>getPlacedOrders(@PathVariable Long userId){
        return ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
    }
}
