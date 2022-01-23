package memberservice.core.scan;

import memberservice.core.AutoAppConfig;
import memberservice.core.member.MemberRepository;
import memberservice.core.member.MemberService;
import static org.assertj.core.api.Assertions.*;

import memberservice.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
컴포넌트 스캔 방식(@ComponentScan)으로 스프링 빈 등록 후,
자동 의존관계 주입(@Autowired) 한 AutoAppConfig
*/

public class AutoAppConfigTest {
	@Test
	void basicScan() {
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext(AutoAppConfig.class);

		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);

		OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
		MemberRepository memberRepository = bean.getMemberRepository();
		System.out.println("memberRepository = " + memberRepository);
	}
}
