package com.example.springbasic.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return itemPrice - discountPrice;
    }



    // Order class 자체 출력 시 출력 되는 문장
    @Override
    public String toString() {
        return "Order{" +
                "memberId = " + memberId +
                ", itemName = " + itemName + '\'' +
                ", itemPrice = " + itemPrice +
                ", discountPrice = " + discountPrice + '}';
    }
}
