package memberservice.core.order;

import memberservice.core.AppConfig;
import memberservice.core.discount.FixDiscountPolicy;
import memberservice.core.member.*;
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

	@Test
	void setterInjectionTest() {
		OrderServiceImpl orderService = new OrderServiceImpl();
		// setter 를 통한 DI
		orderService.setMemberRepository(new MemoryMemberRepository());
		orderService.setDiscountPolicy(new FixDiscountPolicy());

		orderService.createOrder(1L, "itemA", 10000);
	}
}
