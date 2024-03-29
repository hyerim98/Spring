package com.example.springbasic.singleton;

// stateful(상태유지) 설계
public class StatefulService {
    private int price; // 상태를 유지하는 필드 10000 -> 20000

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // 여기가 문제
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
