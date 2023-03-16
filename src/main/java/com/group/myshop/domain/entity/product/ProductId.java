package com.group.myshop.domain.entity.product;

import javax.persistence.Column;
import java.io.Serializable;

public class ProductId implements Serializable {
    @Column(name = "product_id")
    private String id;

    protected ProductId() {}

    public ProductId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
