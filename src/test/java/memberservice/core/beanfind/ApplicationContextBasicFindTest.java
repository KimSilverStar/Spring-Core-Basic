package memberservice.core.beanfind;

import memberservice.core.AppConfig;
import memberservice.core.member.MemberService;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import memberservice.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {
	AnnotationConfigApplicationContext ac =
			new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 이름 + 타입으로 조회")
	void findBeanByNameAndType() {
		MemberService memberService = ac.getBean(
				"memberService", MemberService.class
		);
		System.out.println("memberService = " + memberService);
		System.out.println("memberService.getClass() = " + memberService.getClass());

		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
		// 빈에 등록된 memberService 객체가 MemberServiceImpl 객체인지 검증
	}

	@Test
	@DisplayName("빈 이름없이 타입으로만 조회")
	void findBeanByType() {
		MemberService memberService = ac.getBean(MemberService.class);
		System.out.println("memberService = " + memberService);
		System.out.println("memberService.getClass() = " + memberService.getClass());

		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
		// 빈에 등록된 memberService 객체가 MemberServiceImpl 객체인지 검증
	}

	/* interface 타입 대신 구현체 타입으로도 빈 조회 가능
	* - But 구현체에 의존하는 것은 바람직하지 않음 !! (변경 시 유연성 떨어짐)
	*/
	@Test
	@DisplayName("빈 이름 + 구체 타입으로 조회")
	void findBeanByNameAndConcreteType() {
		MemberServiceImpl memberService = ac.getBean(
				"memberService", MemberServiceImpl.class
		);
		System.out.println("memberService = " + memberService);
		System.out.println("memberService.getClass() = " + memberService.getClass());

		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
		// 빈에 등록된 memberService 객체가 MemberServiceImpl 객체인지 검증
	}

	@Test
	@DisplayName("해당 빈 존재 X (예외 발생)")
	void findBeanByNameX() {
		assertThrows(NoSuchBeanDefinitionException.class,
					 () -> ac.getBean("xxx", MemberService.class)
		);
	}
}
