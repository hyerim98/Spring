package com.example.springintro.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // JPA가 관리하는 엔티티라는 어노테이션
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // @Id : PK, @GeneratedValue : 시퀀스, GenerationType.IDENTITY : DB가 알아서 생성해주는 시퀀스
    private Long id;

    private String name;
}
