package com.nsu.agriculturemarketinfosys.service.customer.review;

import com.nsu.agriculturemarketinfosys.dto.OrderedProductResponseDTO;
import com.nsu.agriculturemarketinfosys.dto.ReviewDTO;

import java.io.IOException;

public interface ReviewService {

    OrderedProductResponseDTO getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDTO giveReview(ReviewDTO reviewDTO)throws IOException;
}
