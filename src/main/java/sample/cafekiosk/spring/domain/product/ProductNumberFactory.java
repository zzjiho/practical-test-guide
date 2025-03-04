package sample.cafekiosk.spring.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductNumberFactory {

    private final ProductRepository productRepository;

    /**
     * private 메소드는 테스트를 어떻게 할까 ?
     * 외부에서 봤을때 (클라이언트 입장)
     * public method만 알면됨, 외부로 노출되지 않은 내부 기능까지 알아야 될 필요가 아예 없다.
     * public method를 테스트하다보면 자연스럽게 검증이 되기때문이다.
     *
     * 근데 private 메소드를 테스트하고 싶은 욕망이 너무 강하게 든다면
     * 객체 분리의 신호로 봐야한다.
     * 내가 지금 하나의 public method 안에서 하는 일이 너무 많나? 라는 의문을 가질 수 있다.
     *
     */

    // 이렇게 분리해서 public으로 만들고 테스트
    public String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        int latestProductNNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }

}
