package sample.cafekiosk.spring.api.controller.order.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.api.service.order.request.OrderCreateServiceRequest;

import java.util.List;

/**
 * <<Request 객체 분리 이유>>
 * Service Layer가 Controller Layer 의존성을 가지지 않도록 하기 위함
 *
 * 하위레이어가 상위레이어에 의존성을 가지면 안된다.
 * Controller Layer는 Service Layer에 의존성을 가질 수 있지만(상위 ->하위), Service Layer는 Controller Layer에 의존성을 가지면 안됨
 * 그래서 Service Layer의 Request 객체를 Controller Layer의 Request 객체로 변환하는 작업이 필요함
 * 이를 위해 Request 객체를 분리함.
 *
 * ex) 주문 생성시 지금은 키오스크에서 주문을 받지만
 * 나중에는 포스, 모바일 앱, 웹 등 다양한 채널에서 주문을 받을 수 있음
 * 그때마다 같은 Service를 사용하고 싶은데 지금 키오스크의 Request DTO를 사용하고 있다보니
 * 이 DTO를 다른 api들도 다 알아야 하는 문제가 발생함
 *
 * 단점: 귀찮음
 */
@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    @NotEmpty(message = "상품 번호 리스트는 필수입니다.")
    private List<String> productNumbers;

    @Builder
    public OrderCreateRequest(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }

    public OrderCreateServiceRequest toServiceRequest() {
        return OrderCreateServiceRequest.builder()
            .productNumbers(productNumbers)
            .build();
    }
}
