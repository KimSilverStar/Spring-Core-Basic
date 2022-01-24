package memberservice.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {
	// 스프링 컨테이너에 Prototype 빈 직접 요청 => 매번 다른 객체 반환받음
	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext(PrototypeBean.class);

		// prototypeBean1, prototypeBean2 는 서로 다른 객체
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	// Singleton 빈과 Prototype 빈 함께 사용 - 문제 발생
	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext(
						ClientBean.class, PrototypeBean.class
				);

		/* ClientBean 자체는 Singleton 빈
		  - ClientBean 이 Singleton 빈으로, 컨테이너 생성 시점에 1개만 생성됨
		    => ClientBean 생성 => PrototypeBean 주입
		    => PrototypeBean 주입 시점에 요청
		  - clientBean1, clientBean2 에 같은 PrototypeBean 이 주입됨
		    => clientBean1, clientBean2 의 필드 PrototypeBean 은 서로 같은 빈
		 <=> 원래 의도: clientBean1, clientBean2 각각 서로 다른 PrototypeBean
		     (Prototype 빈의 정의에 맞게)
		*/
		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(2);

		assertThat(clientBean1.getPrototypeBean())
				.isSameAs(clientBean2.getPrototypeBean());
	}

	@Scope("singleton")			// 생략 가능 (Singleton 스코프가 기본)
	static class ClientBean {
		// Singleton 빈인 ClientBean 이 PrototypeBean 을 주입받음
		// => 주입 시점에 PrototypeBean 요청
		private final PrototypeBean prototypeBean;

		@Autowired				// 생략 가능 (생성자 1개)
		public ClientBean(PrototypeBean prototypeBean) {
			this.prototypeBean = prototypeBean;
			// ClientBean 생성 시점에 PrototypeBean 주입됨
		}

		public int logic() {
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}

		public PrototypeBean getPrototypeBean() {
			return prototypeBean;
		}
	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() { return count; }

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init " + this);
		}

		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
