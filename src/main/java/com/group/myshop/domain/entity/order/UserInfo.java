package com.group.myshop.domain.entity.order;


public class UserInfo {
    private String id;
    private String name;
    private Orderer orderer;
    private OrderLine orderLines;

    public UserInfo() {}

    public void Order(Orderer orderer, OrderLine orderLines, ShippingInfo shippingInfo, OrderState orderState) {
        setOrderer(orderer);
        setOrderLines(orderLines);
    }

    private void setOrderer(Orderer orderer) {
        if (orderer == null) throw new IllegalArgumentException("no orderer");
        this.orderer = orderer;
    }

    private void setOrderLines(OrderLine orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void verifyAtLeastOneOrMoreOrderLines(OrderLine orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = orderLines.stream().mapToInt(x -> x.getAmount()).sum();
    }

    public String getName() {
        return name;
    }
}
