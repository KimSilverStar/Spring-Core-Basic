package memberservice.core.singleton;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
	@Test
	void statefulServiceSingleton() {
		// Singleton 컨테이너인 스프링 컨테이너로 조회한 StatefulService 객체
		// => Singleton 객체로 1개만 생성되어 공유됨 (같은 StatefulService 객체)
		ApplicationContext ac =
				new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);

		/* 문제 상황: 사용자 A가 주문 후, 주문 금액 조회하려는데,
		   주문 ~ 조회 사이에 사용자 B가 주문 */
		// ThreadA: 사용자 A 10000원 주문
		statefulService1.order("userA", 10000);
		// ThreadB: 사용자 B 20000원 주문
		statefulService2.order("userB", 20000);

		// ThreadA: 사용자 A 주문 금액 조회
		int price = statefulService1.getPrice();
		System.out.println("price = " + price);

		assertThat(statefulService1.getPrice()).isEqualTo(20000);
		// 기대 값: 10000
		// 실제 값: 20000 (Singleton 객체, 마지막에 사용자 B가 20000원 주문 했으므로)
	}

	static class TestConfig {
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}