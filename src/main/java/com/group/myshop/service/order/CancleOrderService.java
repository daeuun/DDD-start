package com.group.myshop.service.order;

import com.group.myshop.domain.entity.order.Order;
import com.group.myshop.domain.entity.order.OrderNumber;
import com.group.myshop.domain.entity.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancleOrderService {
    private OrderRepository orderRepository;

    @Transactional
    public void cancel(OrderNumber number) {
        Order order = orderRepository.findByNumber(number);
        if (order == null) throw new NoOrderException(number);
        order.cancle();
    }
}
