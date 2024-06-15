package sample.cafekiosk.spring.api.service.product.response;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * readOnly = true : 데이터베이스에서 데이터를 읽기만 할 때 사용
 * CRUD 에서 R에 해당하는 메소드에 사용 , CUD 동작 X
 * JPA : CUD 스냅 샷 저장, 변경 감지 X -> 성능 향상
 *
 * CQRS - Command Query Responsibility Segregation : 명령과 조회를 분리 (서로 연관이 없게 분리)
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        int latestProductNNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }



    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream() //stream을 사용하여 각각의 요소를 하나씩 처리
                .map(ProductResponse::of) //ProductResponse의 of 메소드를 사용하여 Product를 ProductResponse로 변환
                .collect(Collectors.toList()); //collect를 사용하여 List로 변환


    }


}
