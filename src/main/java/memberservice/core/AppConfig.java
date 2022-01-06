package memberservice.core;

import memberservice.core.discount.FixDiscountPolicy;
import memberservice.core.member.MemberService;
import memberservice.core.member.MemberServiceImpl;
import memberservice.core.member.MemoryMemberRepository;
import memberservice.core.order.OrderService;
import memberservice.core.order.OrderServiceImpl;

public class AppConfig {
	public MemberService memberService() {
		return new MemberServiceImpl(new MemoryMemberRepository());
	}

	public OrderService orderService() {
		return new OrderServiceImpl(
				new MemoryMemberRepository(),
				new FixDiscountPolicy()
		);
	}
}
