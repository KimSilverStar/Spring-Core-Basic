package memberservice.core.order;

import memberservice.core.discount.FixDiscountPolicy;
import memberservice.core.member.Grade;
import memberservice.core.member.Member;
import memberservice.core.member.MemberRepository;
import memberservice.core.member.MemoryMemberRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {
	@Test
	void createOrder() {
		MemberRepository memberRepository = new MemoryMemberRepository();
		memberRepository.save(new Member(1L, "itemA", Grade.VIP));

		OrderServiceImpl orderService = new OrderServiceImpl(
				memberRepository, new FixDiscountPolicy()
		);
		Order order = orderService.createOrder(
				1L, "itemA", 10000
		);
		assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}
