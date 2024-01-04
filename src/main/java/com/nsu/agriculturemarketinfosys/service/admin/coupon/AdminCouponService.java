package com.nsu.agriculturemarketinfosys.service.admin.coupon;

import com.nsu.agriculturemarketinfosys.entity.Coupon;

import java.util.List;

public interface AdminCouponService {

    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupons();
}
