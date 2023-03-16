package com.group.myshop.domain.entity.order;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "purchase_order")
public class Order {
    private OrderState orderState;
    private ShippingInfo shippingInfo;
    private OrderLine orderLines;
    private Money totalAmounts;
    private String orderNumber;
    private OrderNo number;
    private Orderer orderer;

    protected Order() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this == null) return false;
        if (obj.getClass() != Order.class) return false;
        Order other = (Order) obj;
        if (this.orderNumber == null) return false;
        return this.orderNumber.equals(this.orderNumber);
    }

    public Order(OrderLine orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    private void setShippingInfo(ShippingInfo newShippingInfo) {
        if (shippingInfo == null) throw new IllegalArgumentException("no shippingInfo!!");
        setShippingInfo(newShippingInfo);
    }

    private void setOrderState(OrderState state) {
        if (state == null) {
            throw new IllegalArgumentException("no OrderState!!");
        }
        this.orderState = state;
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
        int sum = orderLines.stream().mapToInt(x -> x.getAmounts().getValue()).sum();
        this.totalAmounts = new Money(sum);

    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
        checkShippingInfoChangeable(); //배송지 변경 가능 여부 확인
        this.shippingInfo = newShippingInfo;
    }

    private void checkShippingInfoChangeable() {

    }

    public void cancle() {
        verifyNotYetShipped();
        this.orderState = OrderState.CANCELED;
    }

    private void verifyNotYetShipped() {
        if (orderState != OrderState.PAYMENT_WAITING && orderState != OrderState.PREPARING) {
            throw new IllegalArgumentException("Already Shipped!");
        }
    }

    private boolean isShippingChangable() {
        return orderState == OrderState.PAYMENT_WAITING || orderState == OrderState.PREPARING;
    }

    public void changeOrderLines(List<OrderLine> newLines) {
        orderLines.changeOrderLines(newLines);
        this.totalAmounts = orderLines.getTotalAmounts();
    }

    public Orderer getOrderer() {
        return orderer;
    }

}