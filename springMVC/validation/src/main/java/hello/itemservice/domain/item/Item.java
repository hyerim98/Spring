package hello.itemservice.domain.item;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

@Data
// 특정 필드가 아닌 해당 오브젝트 관련 오류 처리하는 어노테이션 -> 사용 거의 안함
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총합이 10,000원 넘게 입력해주세요.")
public class Item {

    @NotNull(groups = UpdateCheck.class) // 수정 요구사항으로 추가됨
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class}, message = "공백X") // 빈값 + 공백이 있는 경우를 허용하지 않는다(message 지정 가능)
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class}) // null을 허용하지 않는다
    @Range(min = 1000, max = 10000000, groups = {SaveCheck.class, UpdateCheck.class}) // 범위 안의 값이어야 한다
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = {SaveCheck.class}) // 최대 9999까지만 허용한다 -> 수정 요구사항 추가(최대 수량 적용X)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
