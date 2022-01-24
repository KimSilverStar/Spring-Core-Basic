package memberservice.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycle {
	@Test
	public void lifeCycleTest() {
		// ac.close() 를 해야하므로, ApplicationContext 가 아닌
		// ConfigurableApplicationContext 사용
		ConfigurableApplicationContext ac =
				new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient client = ac.getBean(NetworkClient.class);
		ac.close();			// 스프링 컨테이너 종료
	}

	/* 스프링 빈 수동 등록 - Config 클래스 */
	@Configuration
	static class LifeCycleConfig {
		@Bean
		public NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient();
			networkClient.setUrl("http://spring-core-basic.dev");
			return networkClient;
		}
	}
}
