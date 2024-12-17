package hello.itemservicedb.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity // JPA가 사용하는 객체
public class Item {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO-INCREMENT(DB에서 ID 증가)
    private Long id;

    @Column(name = "item_name", length = 10)
    private String itemName;
    private Integer price;
    private Integer quantity;

    // JPA는 기본 생성자가 필수
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
