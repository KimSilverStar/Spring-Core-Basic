package memberservice.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatelessServiceTest {
	@Test
	void statelessServiceTest() {
		ApplicationContext ac =
				new AnnotationConfigApplicationContext(TestConfig.class);
		StatelessService statelessService1 = ac.getBean(StatelessService.class);
		StatelessService statelessService2 = ac.getBean(StatelessService.class);

		// 공유되지 않는 지역변수, 파라미터 등 이용하여 해결
		// ThreadA: 사용자 A 10000원 주문
		int priceA = statelessService1.order("userA", 10000);
		// ThreadB: 사용자 B 20000원 주문
		int priceB = statelessService2.order("userB", 20000);

		// ThreadA: 사용자 A 주문 금액 조회
		System.out.println("price = " + priceA);
	}

	static class TestConfig {
		@Bean
		public StatelessService statelessService() {
			return new StatelessService();
		}
	}
}