package sample.cafekiosk.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * select *
     * from product
     * where selling_type in ('SELLING', 'HOLD');
     */
    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);

    List<Product> findAllByProductNumberIn(List<String> productNumbers);

    // repository 구현 내용에 (natvieQuery, QueryDSL, QueryMethod를 썼던간)
    // 관계없이 테스트를 작성해야한다.
    @Query(value = "select p.product_number from Product p order by id desc limit 1", nativeQuery = true)
    String findLatestProductNumber();
}
