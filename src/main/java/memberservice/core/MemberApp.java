package memberservice.core.member;

/*
* main 함수로 직접 테스트 케이스 작성
*/

import memberservice.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
	public static void main(String[] args) {
		// 변경 전)
//		MemberService memberService = new MemberServiceImpl();

		// 변경 후) AppConfig 를 통해 구현체 객체 생성 후 의존성 주입 (DI)
//		AppConfig appConfig = new AppConfig();
//		MemberService memberService = appConfig.memberService();

		// ApplicationContext: 스프링 컨테이너
		// AppConfig 에 @Bean 선언된 메소드의 객체들을 생성하여 관리
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(AppConfig.class);

		MemberService memberService = applicationContext.getBean(
				"memberService", MemberService.class
		);		// 인자: @Bean 선언된 메소드 이름, 반환형

		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);

		Member findMember = memberService.findMember(1L);
		System.out.println("new member = " + member.getName());
		System.out.println("find member = " + findMember.getName());
	}
}
