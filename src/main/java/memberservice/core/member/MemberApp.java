package memberservice.core.member;

/*
* main 함수로 직접 테스트 케이스 작성
*/

public class MemberApp {
	public static void main(String[] args) {
		MemberService memberService = new MemberServiceImpl();
		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);

		Member findMember = memberService.findMember(1L);
		System.out.println("new member = " + member.getName());
		System.out.println("find member = " + findMember.getName());
	}
}
