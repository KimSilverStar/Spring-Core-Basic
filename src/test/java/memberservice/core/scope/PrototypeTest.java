package memberservice.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
	@Test
	void prototypeBeanFind() {
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext(PrototypeBean.class);

		// Prototype 스코프: 빈 조회 요청이 들어올 때마다,
		// Prototype 빈을 생성 + 의존관계 주입 + 초기화한 후, 반환
		System.out.println("find prototypeBean1");
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		System.out.println("find prototypeBean2");
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

		System.out.println("prototypeBean1 = " + prototypeBean1);
		System.out.println("prototypeBean2 = " + prototypeBean2);
		// Prototype 스코프이므로, 두 객체는 서로 다른 빈
		assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

		// 2. 컨테이너가 소멸 메소드를 호출하지 않으므로, 클라이언트에서 직접 호출하여 소멸
		// => Prototype 빈 반환 이후, 관리는 클라이언트가 직접
		prototypeBean1.destroy();
		prototypeBean2.destroy();

		// 1. 클라이언트에게 Prototype 빈 반환 후, 컨테이너가 더 이상 관리 X
		// => 컨테이너가 소멸 메소드(@PreDestroy 선언된 메소드) 호출 X
		ac.close();
	}

	@Scope("prototype")
	static class PrototypeBean {
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
