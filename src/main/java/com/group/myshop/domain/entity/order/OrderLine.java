package com.group.myshop.domain.entity.order;

import com.group.myshop.domain.entity.product.ProductId;

public class OrderLine {
    private ProductId productId;
    private Money price;
    private int quantity;
    private Money amounts;

    public OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = new Money(price.getValue()); // Money가 불변객체가 아니면, 데이터를 복사한 새로운 객체 생성해야 한다.
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() {
        return price.multiply(quantity);
    }

    public ProductId getProductId() {
        return productId;
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getAmounts() {
        return amounts;
    }
}