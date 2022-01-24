package memberservice.core.lifecycle;

import org.junit.jupiter.api.Test;
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
		NetworkClient2 client = ac.getBean(NetworkClient2.class);
		ac.close();			// 스프링 컨테이너 종료
	}

	/* 스프링 빈 수동 등록 - Config 클래스 */
	@Configuration
	static class LifeCycleConfig {
		// 설정 정보 Config 클래스의 @Bean 에 초기화 메소드, 소멸 메소드 지정
//		@Bean(initMethod = "init", destroyMethod = "close")
		@Bean
		public NetworkClient2 networkClient() {
			NetworkClient2 networkClient = new NetworkClient2();
			networkClient.setUrl("http://spring-core-basic.dev");
			return networkClient;
		}
	}
}
