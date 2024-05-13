package sample.cafekiosk.spring.api.service.product.response;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream() //stream을 사용하여 각각의 요소를 하나씩 처리
                .map(ProductResponse::of) //ProductResponse의 of 메소드를 사용하여 Product를 ProductResponse로 변환
                .collect(Collectors.toList()); //collect를 사용하여 List로 변환


    }



}
