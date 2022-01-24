package memberservice.core.scope;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {
	@Test
	void singletonBeanFind() {
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext(SingletonBean.class);

		SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
		SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
		System.out.println("singletonBean1 = " + singletonBean1);
		System.out.println("singletonBean2 = " + singletonBean2);
		// Singleton 스코프이므로, 두 객체는 같은 빈 (Singleton 빈)
		assertThat(singletonBean1).isSameAs(singletonBean2);

		ac.close();
	}

	// 기본적으로 Singleton 스코프이므로, @Scope("singleton") 생략 가능
	@Scope("singleton")
	static class SingletonBean {
		@PostConstruct
		public void init() {
			System.out.println("SingletonBean.init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("SingletonBean.destroy");
		}
	}
}
