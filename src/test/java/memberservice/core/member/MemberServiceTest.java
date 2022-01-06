package memberservice.core.member;

import memberservice.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
	// 변경 전)
//	MemberService memberService = new MemberServiceImpl();

	// 변경 후) AppConfig 를 통해 구현체 객체 생성 후 의존성 주입 (DI)
	MemberService memberService;

	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
	}

	@Test
	void join() {
		// Given
		Member member = new Member(1L, "memberA", Grade.VIP);

		// When
		memberService.join(member);
		Member findMember = memberService.findMember(member.getId());

		// Then
		Assertions.assertThat(member).isEqualTo(findMember);
	}
}
