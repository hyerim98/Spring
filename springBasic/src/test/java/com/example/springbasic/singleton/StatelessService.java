package com.example.springbasic.singleton;

// stateless 설계
public class StatelessService {

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
