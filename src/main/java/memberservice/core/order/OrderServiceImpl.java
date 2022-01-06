package memberservice.core.order;

import memberservice.core.discount.DiscountPolicy;
import memberservice.core.discount.FixDiscountPolicy;
import memberservice.core.discount.RateDiscountPolicy;
import memberservice.core.member.Member;
import memberservice.core.member.MemberRepository;
import memberservice.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
	private final MemberRepository memberRepository = new MemoryMemberRepository();
//	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

	// 1. DIP (Dependency Inversion Principle: 인터페이스에만 의존할 것) 위배
	// 인터페이스와 구현 클래스에 동시 의존

	// 2. OCP (Open-Closed Principle) 위배
	// 향후 저장소 구현체 or 할인 정책 구현체를 변경하려고 하는 경우

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
