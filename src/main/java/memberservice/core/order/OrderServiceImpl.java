package memberservice.core.order;

import memberservice.core.discount.DiscountPolicy;
import memberservice.core.discount.FixDiscountPolicy;
import memberservice.core.member.Member;
import memberservice.core.member.MemberRepository;
import memberservice.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
	private final MemberRepository memberRepository = new MemoryMemberRepository();
	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
