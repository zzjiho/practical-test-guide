package sample.cafekiosk.spring.api.controller.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@Getter
@NoArgsConstructor // Post요청이 올때 ObjectMapper가 역직렬화를 도와주는데 (JSON -> String) 이때 ObjectMapper가 기본생성자를 쓴다.
public class ProductCreateRequest {

    /**
     * @NotBlank 만 해서 클라이언트에게, 너 공백이야 이정도만 알려주고
     * 20자 검증이라던지 이런것들은 service 레이어에서 검증을 하던지,
     * product code에서 생성시점에서 검증을 하던지 안쪽 레이어에서 검증을 하는게 더 맞다 라고 하시네요
     */
    @NotNull(message = "상품 타입은 필수입니다.")
    private ProductType type;

    @NotNull(message = "상품 판매상태는 필수입니다.")
    private ProductSellingStatus sellingStatus;

    @NotBlank(message = "상품 이름은 필수입니다.")
    private String name;

    @Positive(message = "상품 가격은 양수여야 합니다.")
    private int price;

    /**
     * 테스트에서만 필요한 메소드가 생겼는데 프로덕션 코드에서는 필요 없다면 ?
     *
     * 아래 builder 패턴은 테스트 코드에서만 사용하기 위해서 만들어졌는데 어떻게 해야할까 ?
     * -> 만들어도 된다. (getter, 기본생성자 등등..)
     * 미래에도 충분히 사용될 수 있는 성격의 메소드라면 괜찮다.
     * 하지만 무분별한 생성은 지양하는것이 좋다..
     */
    @Builder
    public ProductCreateRequest(ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public Product toEntity(String nextProductNumber) {
        return Product.builder()
                .productNumber(nextProductNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();



    }
}
