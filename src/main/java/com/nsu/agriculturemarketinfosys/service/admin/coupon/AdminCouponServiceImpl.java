package com.nsu.agriculturemarketinfosys.service.admin.coupon;

import com.nsu.agriculturemarketinfosys.entity.Coupon;
import com.nsu.agriculturemarketinfosys.exception.ValidationException;
import com.nsu.agriculturemarketinfosys.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService {

    private final CouponRepository couponRepository;

    public Coupon createCoupon(Coupon coupon){
        if (couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon code already exists.");
        }
        return couponRepository.save(coupon);
    }

    public List<Coupon>getAllCoupons(){
        return couponRepository.findAll();
    }
}
