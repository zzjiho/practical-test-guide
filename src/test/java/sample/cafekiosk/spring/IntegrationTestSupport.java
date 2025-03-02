package sample.cafekiosk.spring;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.client.mail.MailSendClient;

/**
 * @DataJpaTest 이거보단 SpringBootTest를 사용하는게 좋다.
 * SpringBootTest 보단 가볍다.
 * jpa 관련된 빈만 띄운다.
 *
 * 근데왜 SpringBootTest를 사용하는게 좋을까?
 * @DataJpaTest는 @Transactional이 기본으로 설정되어있고, @SpringBootTest는 기본적으로 @Transactional이 설정되어있지 않다.
 *
 */
@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {

    @MockBean
    protected MailSendClient mailSendClient; // protected로 변경해서 다른 테스트에서 사용

}
