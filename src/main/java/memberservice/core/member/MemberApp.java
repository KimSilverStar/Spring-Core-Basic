package memberservice.core.member;

/*
* main 함수로 직접 테스트 케이스 작성
*/

import memberservice.core.AppConfig;

public class MemberApp {
	public static void main(String[] args) {
		// 변경 전)
//		MemberService memberService = new MemberServiceImpl();

		// 변경 후) AppConfig 를 통해 구현체 객체 생성 후 의존성 주입 (DI)
		AppConfig appConfig = new AppConfig();
		MemberService memberService = appConfig.memberService();

		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);

		Member findMember = memberService.findMember(1L);
		System.out.println("new member = " + member.getName());
		System.out.println("find member = " + findMember.getName());
	}
}
