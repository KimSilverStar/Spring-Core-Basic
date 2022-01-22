package memberservice.core.order;

import memberservice.core.discount.DiscountPolicy;
import memberservice.core.discount.FixDiscountPolicy;
import memberservice.core.discount.RateDiscountPolicy;
import memberservice.core.member.Member;
import memberservice.core.member.MemberRepository;
import memberservice.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
	/* 변경 전) DIP, OCP 위배 */
//	private final MemberRepository memberRepository = new MemoryMemberRepository();
//	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

	// 1. DIP (Dependency Inversion Principle: 인터페이스에만 의존할 것) 위배
	// 인터페이스와 구현 클래스에 동시 의존

	// 2. OCP (Open-Closed Principle) 위배
	// 향후 저장소 구현체 or 할인 정책 구현체를 변경하려고 하는 경우

	/* 변경 후 - 생성자를 통한 DI) DIP, OCP 만족 - interface 에만 의존 */
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	@Autowired
	public OrderServiceImpl(
			MemberRepository memberRepository,
			DiscountPolicy discountPolicy) {
		System.out.println("1. OrderServiceImpl.OrderServiceImpl");
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	/* setter (수정자)를 통한 DI - set~(주입할 Bean)
	(기존 final 선언된 필드를 final 선언 제거함)
	 - 생성자 호출하여 객체 생성 -> setter 호출하여 DI (생성자를 통한 DI 없는 코드 가정)
	*/
//	private MemberRepository memberRepository;
//	private DiscountPolicy discountPolicy;
//
//	@Autowired
//	public void setMemberRepository(MemberRepository memberRepository) {
//		System.out.println("memberRepository = " + memberRepository);
//		this.memberRepository = memberRepository;
//	}
//	@Autowired
//	public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//		System.out.println("discountPolicy = " + discountPolicy);
//		this.discountPolicy = discountPolicy;
//	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	/* 테스드 용도로 추가한 메소드 - @Configuration 의 Singleton 테스트 */
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
