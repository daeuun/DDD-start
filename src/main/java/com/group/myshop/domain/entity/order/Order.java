package com.group.myshop.domain.entity.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Order {
    private OrderState orderState;
    private ShippingInfo shippingInfo;
    private List<OrderLine> orderLines;
    private Money totalAmounts;

    public void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null) {
            throw new IllegalArgumentException("no shippingInfo!!");
        }
        this.shippingInfo = shippingInfo;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLines!");
        }
    }

    private void calculateTotalAmounts() {
        int sum = orderLines.stream().mapToInt(x -> x.getAmounts()).sum();
        this.totalAmounts = new Money(sum);
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
        // if (!isShippingChangable()) {
        //     throw new IllegalArgumentException("can't change shipping in " + orderState);
        // }
        // this.shippingInfo = newShippingInfo;
    }

    public void cancle() {
        verifyNotYetShipped();
        this.orderState = OrderState.CANCLED;
    }

    private void verifyNotYetShipped() {
        if (orderState != OrderState.PAYMENT_WAITING && orderState != OrderState.PREPARING) {
            throw new IllegalArgumentException("Already Shipped!");
        }
    }

    private boolean isShippingChangable() {
        return orderState == OrderState.PAYMENT_WAITING || orderState == OrderState.PREPARING;
    }
}