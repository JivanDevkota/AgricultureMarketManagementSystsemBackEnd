package com.nsu.agriculturemarketinfosys.service.admin.order;

import com.nsu.agriculturemarketinfosys.dto.AnalyticsResponse;
import com.nsu.agriculturemarketinfosys.dto.OrderDTO;

import java.util.List;

public interface AdminOrderService {

    List<OrderDTO> getAllPlacedOrder();

    OrderDTO changeOrderStatus(Long orderId, String status);

    AnalyticsResponse calculateAnalytics();
}
