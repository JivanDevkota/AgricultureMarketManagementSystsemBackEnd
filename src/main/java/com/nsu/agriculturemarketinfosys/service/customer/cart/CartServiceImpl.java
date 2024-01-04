package com.nsu.agriculturemarketinfosys.service.customer.cart;

import com.nsu.agriculturemarketinfosys.dto.AddProductInCartDTO;
import com.nsu.agriculturemarketinfosys.dto.CartItemsDTO;
import com.nsu.agriculturemarketinfosys.dto.OrderDTO;
import com.nsu.agriculturemarketinfosys.dto.PlaceOrderDTO;
import com.nsu.agriculturemarketinfosys.entity.*;
import com.nsu.agriculturemarketinfosys.enums.OrderStatus;
import com.nsu.agriculturemarketinfosys.exception.ValidationException;
import com.nsu.agriculturemarketinfosys.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponRepository couponRepository;

    public ResponseEntity<?>addProductToCart(AddProductInCartDTO addProductInCartDTO){
        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(addProductInCartDTO.getUserId(), OrderStatus.Pending);
        Optional<CartItem>optionalCartItem=cartItemRepository.findByProductIdAndOrderIdAndUserId
                (addProductInCartDTO.getProductId(),activeOrder.getId(),addProductInCartDTO.getUserId());

        if (optionalCartItem.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        else {
            Optional<Product>optionalProduct=productRepository.findById(addProductInCartDTO.getProductId());
            Optional<User>optionalUser=userRepository.findById(addProductInCartDTO.getUserId());

            if (optionalProduct.isPresent() &&optionalUser.isPresent()){
                CartItem cart=new CartItem();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItem updateCart=cartItemRepository.save(cart);


                activeOrder.setTotalAmount(activeOrder.getTotalAmount()+cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount()+cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found");
            }
        }
    }

    public OrderDTO getCartByUserId(Long userId){
        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        List<CartItemsDTO>cartItemsDTOList=activeOrder.getCartItems().stream().map(CartItem ::getCartDto).collect(Collectors.toList());
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setAmount(activeOrder.getAmount());
        orderDTO.setId(activeOrder.getId());
        orderDTO.setOrderStatus(activeOrder.getOrderStatus());
        orderDTO.setDiscout(activeOrder.getDiscount());
        orderDTO.setTotalAmount(activeOrder.getTotalAmount());
        orderDTO.setCartItems(cartItemsDTOList);

        if (activeOrder.getCoupon()!=null){
            orderDTO.setCouponName(activeOrder.getCoupon().getName());
        }

        return orderDTO;
    }


    public OrderDTO applyCoupon(Long userId,String code){
        //get current active order of user
        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        Coupon coupon=couponRepository.findByCode(code).orElseThrow(()->new  ValidationException("Coupon not found!"));

        if (couponIsExpired(coupon)){
            throw new ValidationException("Coupon is expired.");
        }

        double discountAmount=((coupon.getDiscount()/100.0)*activeOrder.getTotalAmount());
        double netAmount=activeOrder.getTotalAmount()-discountAmount;

        activeOrder.setAmount((long) netAmount);
        activeOrder.setDiscount((long) discountAmount);
        activeOrder.setCoupon(coupon);

        orderRepository.save(activeOrder);
        //to convert the entity to dto
        return activeOrder.getOrderDto();
    }

        private boolean couponIsExpired(Coupon coupon){
            Date currentDate=new Date();
            Date expiredDate=coupon.getExpirationDate();

            return expiredDate!=null && currentDate.after(expiredDate);
        }

        public OrderDTO increaseProductQuantity(AddProductInCartDTO addProductInCartDTO){
            Order activeOrder=orderRepository.findByUserIdAndOrderStatus(addProductInCartDTO.getUserId(), OrderStatus.Pending);
            Optional<Product>optionalProduct=productRepository.findById(addProductInCartDTO.getProductId());

            Optional<CartItem>optionalCartItem=cartItemRepository.findByProductIdAndOrderIdAndUserId(
                    addProductInCartDTO.getProductId(),activeOrder.getId(),addProductInCartDTO.getUserId());

            if (optionalProduct.isPresent() && optionalCartItem.isPresent()){
                CartItem cartItem=optionalCartItem.get();
                Product product=optionalProduct.get();

                activeOrder.setAmount(activeOrder.getAmount()+product.getPrice());
                activeOrder.setTotalAmount(activeOrder.getTotalAmount()+product.getPrice());

                cartItem.setQuantity(cartItem.getQuantity()+1);

                if (activeOrder.getCoupon()!=null){
                    double discountAmount=((activeOrder.getCoupon().getDiscount()/100.0)*activeOrder.getTotalAmount());
                    double netAmount=activeOrder.getTotalAmount()-discountAmount;

                    activeOrder.setAmount((long) netAmount);
                    activeOrder.setDiscount((long) discountAmount);
                }
                cartItemRepository.save(cartItem);
                orderRepository.save(activeOrder);
                return activeOrder.getOrderDto();
            }
            return null;
        }


    public OrderDTO decreaseProductQuantity(AddProductInCartDTO addProductInCartDTO){
        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(addProductInCartDTO.getUserId(), OrderStatus.Pending);
        Optional<Product>optionalProduct=productRepository.findById(addProductInCartDTO.getProductId());

        Optional<CartItem>optionalCartItem=cartItemRepository.findByProductIdAndOrderIdAndUserId(
                addProductInCartDTO.getProductId(),activeOrder.getId(),addProductInCartDTO.getUserId());

        if (optionalProduct.isPresent() && optionalCartItem.isPresent()){
            CartItem cartItem=optionalCartItem.get();
            Product product=optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount()-product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount()-product.getPrice());

            cartItem.setQuantity(cartItem.getQuantity()-1);

            if (activeOrder.getCoupon()!=null){
                double discountAmount=((activeOrder.getCoupon().getDiscount()/100.0)*activeOrder.getTotalAmount());
                double netAmount=activeOrder.getTotalAmount()-discountAmount;

                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);
            }
            cartItemRepository.save(cartItem);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return null;
    }


    public OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO){
        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(placeOrderDTO.getUserId(), OrderStatus.Pending);
        Optional<User>optionalUser=userRepository.findById(placeOrderDTO.getUserId());
        if (optionalUser.isPresent()){
            activeOrder.setOrderDescription(placeOrderDTO.getOrderDescription());
            activeOrder.setAddress(placeOrderDTO.getAddress());
            activeOrder.setDate(new Date());
            activeOrder.setOrderStatus(OrderStatus.Placed);
            activeOrder.setTrackingId(UUID.randomUUID());

            orderRepository.save(activeOrder);

            Order order=new Order();
            order.setAmount(0L);
            order.setTotalAmount(0L);
            order.setDiscount(0L);
            order.setUser(optionalUser.get());
            order.setOrderStatus(OrderStatus.Pending);
            orderRepository.save(order);

            return activeOrder.getOrderDto();
        }
        return null;
    }

    public List<OrderDTO>getMyPlacedOrders(Long userId){
        return orderRepository.findByUserIdAndOrderStatusIn(userId, Arrays.asList(OrderStatus.Placed,
                OrderStatus.Shipped,OrderStatus.Delivered)).stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

    public OrderDTO searchOrderByTrackingId(UUID trackingId){
        Optional<Order>optionalOrder=orderRepository.findByTrackingId(trackingId);
        if (optionalOrder.isPresent()){
            return optionalOrder.get().getOrderDto();
        }
        return null;
    }
}
