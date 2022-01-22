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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* AppConfig 리팩토링
 - 역할 interface 반환형 메소드에서 구현체 생성하여 반환
 - 역할과 구현이 명확히 보이도록 정리
*/

@Configuration
public class AppConfig {
	// @Bean memberService -> new MemoryMemberRepository()
	// @Bean orderService -> new MemoryMemberRepository()

	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		// OrderServiceImpl - 생성자를 통한 DI 이용
//		return new OrderServiceImpl(
//				memberRepository(), discountPolicy()
//		);

		// OrderServiceImpl - setter 를 통한 DI 이용
		return null;
	}

	@Bean
	public DiscountPolicy discountPolicy() {
//		return new FixDiscountPolicy();
		return new RateDiscountPolicy();
		// 할인 정책 변경: FixDiscountPolicy -> RateDiscountPolicy
	}
}
