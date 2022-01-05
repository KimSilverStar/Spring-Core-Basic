package memberservice.core.discount;

import memberservice.core.member.Grade;
import memberservice.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {
	RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

	@Test
	@DisplayName("VIP는 10% 할인이 적용되어야 함")
	void vip_o() {
		// Given
		Member member = new Member(1L, "memberVIP", Grade.VIP);

		// When
		int discount = discountPolicy.discount(member, 10000);

		// Then: 10000원인 상품을 10% 할인하면, 1000원이 할인되어야 함
		assertThat(discount).isEqualTo(1000);
	}

	@Test
	@DisplayName("VIP가 아니면 할인이 적용되지 않아야 함")
	void vip_x() {
		// Given
		Member member = new Member(2L, "memberBASIC", Grade.BASIC);

		// When
		int discount = discountPolicy.discount(member, 10000);

		// Then
		assertThat(discount).isEqualTo(0);
	}
}