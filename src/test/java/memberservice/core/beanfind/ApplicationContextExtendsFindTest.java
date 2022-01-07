package memberservice.core.beanfind;

import memberservice.core.discount.DiscountPolicy;
import memberservice.core.discount.FixDiscountPolicy;
import memberservice.core.discount.RateDiscountPolicy;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {
	AnnotationConfigApplicationContext ac
			= new AnnotationConfigApplicationContext(TestConfig.class);

	@Test
	@DisplayName("부모 타입으로 빈 조회 시 자식이 둘 이상 있으면, 중복 오류 발생")
	void findBeanByParentTypeDuplicate() {
		assertThrows(NoUniqueBeanDefinitionException.class,
					 () -> ac.getBean(DiscountPolicy.class)
		);
		// 부모 타입으로 빈 조회하면, 해당 타입의 부모 + 상속받는 자식들까지 모두 조회됨
	}

	/* 해결 방법 1) 빈 이름 + 빈 타입으로 조회 */
	@Test
	@DisplayName("부모 타입으로 빈 조회 시 자식이 둘 이상 있으면, 빈 이름을 지정할 것")
	void findBeanByParentTypeBeanName() {
		DiscountPolicy rateDiscountPolicy = ac.getBean(
				"rateDiscountPolicy", DiscountPolicy.class
		);
		assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}

	/* 해결 방법 2) 특정 구체(자식, 하위) 타입으로 조회
	* - 구현체에 의존하게 되어 유연성 떨어짐 (지양할 것)
	*/
	@Test
	@DisplayName("부모 타입으로 빈 조회 시 자식이 둘 이상 있으면, 특정 자식 타입으로 조회")
	void findBeanBySubType() {
		RateDiscountPolicy rateDiscountPolicy = ac.getBean(
				RateDiscountPolicy.class
		);
		assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}

	@Test
	@DisplayName("부모 타입으로 모두 조회하기")
	void findAllBeanByParentType() {
		Map<String, DiscountPolicy> beansOfType =
				ac.getBeansOfType(DiscountPolicy.class);
		for (String key : beansOfType.keySet())
			System.out.println("key = " + key +
								", value = " + beansOfType.get(key));
		assertThat(beansOfType.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("최상위 부모 타입 Object 로 모두 조회하기")
	void findAllBeanByObjectType() {
		Map<String, Object> beansOfType =
				ac.getBeansOfType(Object.class);
		for (String key : beansOfType.keySet())
			System.out.println("key = " + key +
								", value = " + beansOfType.get(key));
	}

	@Configuration
	static class TestConfig {
		@Bean
		public DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}

		@Bean
		public DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();
		}
	}
}
