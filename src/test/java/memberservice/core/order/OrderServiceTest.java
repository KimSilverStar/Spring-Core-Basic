package memberservice.core.order;

import memberservice.core.AppConfig;
import memberservice.core.member.Grade;
import memberservice.core.member.Member;
import memberservice.core.member.MemberService;
import memberservice.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
	// 변경 전)
//	MemberService memberService = new MemberServiceImpl();
//	OrderService orderService = new OrderServiceImpl();

	// 변경 후) AppConfig 를 통해 구현체 객체 생성 후 의존성 주입 (DI)
	MemberService memberService;
	OrderService orderService;

	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}

	@Test
	void createOrder() {
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(
				memberId, "itemA", 10000
		);

		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}
