package com.example.springbasic.singleton;

// stateless(무상태) 설계
public class StatelessService {

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
