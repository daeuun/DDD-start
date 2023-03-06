package com.group.myshop.domain.entity.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderLine {
    private Product product;
    private Money price;
    private int quantity;
    private int amounts;

}