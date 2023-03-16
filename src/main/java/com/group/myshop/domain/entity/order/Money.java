package com.group.myshop.domain.entity.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Money {
    private int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    public Money add(Money money) {
        return new Money(this.value + money.value); // 변경한 데이터를 갖는 새로운 객체 생성
    }
}
