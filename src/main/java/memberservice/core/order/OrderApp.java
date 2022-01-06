package memberservice.core.order;

import memberservice.core.AppConfig;
import memberservice.core.member.Grade;
import memberservice.core.member.Member;
import memberservice.core.member.MemberService;
import memberservice.core.member.MemberServiceImpl;

public class OrderApp {
	public static void main(String[] args) {
		// 변경 전)
//		MemberService memberService = new MemberServiceImpl();
//		OrderService orderService = new OrderServiceImpl();

		// 변경 후) AppConfig 를 통해 구현체 객체 생성 후 의존성 주입 (DI)
		AppConfig appConfig = new AppConfig();
		MemberService memberService = appConfig.memberService();
		OrderService orderService = appConfig.orderService();

		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(
				memberId, "itemA", 10000
		);

		System.out.println("order = " + order);
	}
}
