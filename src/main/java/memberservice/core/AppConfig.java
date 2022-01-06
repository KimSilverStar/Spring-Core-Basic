package memberservice.core;

import memberservice.core.discount.DiscountPolicy;
import memberservice.core.discount.FixDiscountPolicy;
import memberservice.core.discount.RateDiscountPolicy;
import memberservice.core.member.MemberRepository;
import memberservice.core.member.MemberService;
import memberservice.core.member.MemberServiceImpl;
import memberservice.core.member.MemoryMemberRepository;
import memberservice.core.order.OrderService;
import memberservice.core.order.OrderServiceImpl;

public class AppConfig {
	// AppConfig 리팩토링 - 역할 interface 반환형 메소드에서 구현체 생성하여 반환
	// => 역할과 구현이 명확히 보이도록 정리

	// MemberService 역할
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());	// 구현체
	}

	// MemberRepository 역할
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();				// 구현체
	}

	// OrderService 역할
	public OrderService orderService() {
		return new OrderServiceImpl(
				memberRepository(), discountPolicy()		// 구현체
		);
	}

	// DiscountPolicy 역할
	public DiscountPolicy discountPolicy() {
//		return new FixDiscountPolicy();						// 구현체
		return new RateDiscountPolicy();
		// 할인 정책 변경: FixDiscountPolicy -> RateDiscountPolicy
	}
}
