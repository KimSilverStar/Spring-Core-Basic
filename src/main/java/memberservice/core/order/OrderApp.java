package memberservice.core.order;

import memberservice.core.member.Grade;
import memberservice.core.member.Member;
import memberservice.core.member.MemberService;
import memberservice.core.member.MemberServiceImpl;

public class OrderApp {
	public static void main(String[] args) {
		MemberService memberService = new MemberServiceImpl();
		OrderService orderService = new OrderServiceImpl();

		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(
				memberId, "itemA", 10000
		);

		System.out.println("order = " + order);
	}
}
