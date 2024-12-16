package hello.itemservicedb.repository;

import lombok.Data;

// 상품을 수정할 때 사용하는 객체(단순히 데이터를 전달하는 용도)
@Data
public class ItemUpdateDto {
    private String itemName;
    private Integer price;
    private Integer quantity;

    public ItemUpdateDto() {
    }

    public ItemUpdateDto(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
