package memberservice.core.singleton;

import memberservice.core.AppConfig;
import memberservice.core.member.MemberRepository;
import memberservice.core.member.MemberServiceImpl;
import memberservice.core.order.OrderServiceImpl;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
	@Test
	void configurationTest() {
		ApplicationContext ac
				= new AnnotationConfigApplicationContext(AppConfig.class);

		// 스프링 컨테이너가 Singleton 으로 관리하는 스프링 빈 (Singleton 객체) 조회
		MemberServiceImpl memberService = ac.getBean(
				"memberService", MemberServiceImpl.class
		);
		OrderServiceImpl orderService = ac.getBean(
				"orderService", OrderServiceImpl.class
		);
		MemberRepository memberRepository = ac.getBean(
				"memberRepository", MemberRepository.class
		);

		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();

		// 모두 다 같은 객체 => 스프링 빈 Singleton 객체
		System.out.println("memberService -> memberRepository = " + memberRepository1);
		System.out.println("orderService -> memberRepository = " + memberRepository2);
		System.out.println("memberRepository = " + memberRepository2);

		// 모두 다 같은 객체 => 1개만 생성되어 공유
		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
	}

	@Test
	void configurationDeep() {
		ApplicationContext ac
				= new AnnotationConfigApplicationContext(AppConfig.class);

		AppConfig bean = ac.getBean(AppConfig.class);
		System.out.println("bean = " + bean.getClass());
	}
}
