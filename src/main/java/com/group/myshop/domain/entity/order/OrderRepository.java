package com.group.myshop.domain.entity.order;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    Order findByNumber(OrderNumber number);
    void save(Order order);
    void delete(Order order);

    Order findById(OrderId id);
}
