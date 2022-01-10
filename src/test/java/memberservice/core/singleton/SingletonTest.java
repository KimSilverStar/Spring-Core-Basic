package memberservice.core.singleton;

import memberservice.core.AppConfig;
import memberservice.core.member.MemberService;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {
	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();

		// 1. 조회: 호출할 때 마다 객체 생성
		MemberService memberService1 = appConfig.memberService();

		// 2. 조회: 호출할 때 마다 객체 생성
		MemberService memberService2 = appConfig.memberService();

		// 참조값이 다른 것을 확인
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		// memberService1 != memberService2
		assertThat(memberService1).isNotSameAs(memberService2);
	}

	@Test
	@DisplayName("Singleton Pattern을 적용한 객체 사용")
	void singletonServiceTest() {
		// private 생성자 선언 => 외부에서 직접 인스턴스 생성 방지
		// SingletonService singletonService = new SingletonService();

		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();

		System.out.println("SingletonService1 = " + singletonService1);
		System.out.println("SingletonService2 = " + singletonService2);

		// singletonService1 == singletonService2
		assertThat(singletonService1).isSameAs(singletonService2);
	}
}
