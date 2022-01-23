package memberservice.core.discount;

import memberservice.core.annotation.MainDiscountPolicy;
import memberservice.core.member.Grade;
import memberservice.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
//@Primary
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {
	private int discountPercent = 10;			// 10% 할인

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP)
			return price * discountPercent / 100;
		else
			return 0;
	}
}
