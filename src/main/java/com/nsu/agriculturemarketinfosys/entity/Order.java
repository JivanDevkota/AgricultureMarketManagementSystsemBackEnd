package com.nsu.agriculturemarketinfosys.entity;


import com.nsu.agriculturemarketinfosys.dto.OrderDTO;
import com.nsu.agriculturemarketinfosys.enums.OrderStatus;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderDescription;

    private Date date;

    private Long amount;

    private String address;

    private String payment;

    private OrderStatus orderStatus;

    private long totalAmount;

    private Long discount;

    @Column
    private UUID trackingId;

    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;
    //one order can have many cart item on it
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<CartItem>cartItems;

    public OrderDTO getOrderDto(){
        OrderDTO orderDTO=new OrderDTO();

        orderDTO.setId(id);
        orderDTO.setOrderDescription(orderDescription);
        orderDTO.setAddress(address);
        orderDTO.setTrackingId(trackingId);
        orderDTO.setAmount(amount);
        orderDTO.setDate(date);
        orderDTO.setOrderStatus(orderStatus);
        orderDTO.setUserName(user.getName());

        if (coupon!=null){
            orderDTO.setCouponName(coupon.getName());
        }
        return orderDTO;
    }

}
