package memberservice.core.scan.filter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {
	@Test
	void filterScan() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(
				ComponentFilterAppConfig.class
		);

		// BeanA: @MyIncludeComponent 선언 => 컴포넌트 스캔 대상에 포함
		BeanA beanA = ac.getBean("beanA", BeanA.class);
		assertThat(beanA).isNotNull();

		// BeanB: @MyExcludeComponent 선언 => 컴포넌트 스캔 대상에서 제외
		// => BeanB 는 스프링 빈으로 등록되지 않음
		assertThrows(NoSuchBeanDefinitionException.class, () -> {
			ac.getBean("beanB", BeanB.class);
		});
	}


	@Configuration
	@ComponentScan(
			includeFilters = @ComponentScan.Filter(
					type = FilterType.ANNOTATION,
					classes = MyIncludeComponent.class
			),
			excludeFilters = @ComponentScan.Filter(
					type = FilterType.ANNOTATION,
					classes = MyExcludeComponent.class
			)
			// type = FilterType.ANNOTATION 은 디폴트로, 생략 가능
	)
	static class ComponentFilterAppConfig {
	}
}
