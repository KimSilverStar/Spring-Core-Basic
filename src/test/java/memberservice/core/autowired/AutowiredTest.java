package memberservice.core.autowired;

import memberservice.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
	@Test
	void autowiredOption() {
		ApplicationContext ac =
				new AnnotationConfigApplicationContext(TestBean.class);
	}

	static class TestBean {
		/* Member: 스프링 컨테이너에 등록된 스프링 빈이 아님 */

		// @Autowired(required = false) 명시하면, 자동 주입할 대상이 없는 경우
		// setter 메소드 자체가 호출 안됨
		@Autowired(required = false)
		public void setNoBean1(Member noBean1) {
			System.out.println("noBean1 = " + noBean1);
			// setNoBean1() 메소드 호출 안됨
		}

		// 파라미터에 @Nullable 명시하면, 자동 주입할 대상이 없는 경우 Null 값으로 채워짐
		@Autowired
		public void setNoBean2(@Nullable Member noBean2) {
			System.out.println("noBean2 = " + noBean2);
		}

		// 파라미터에 Optional 명시하면, Optional 로 wrapping 됨
		@Autowired
		public void setNoBean3(Optional<Member> noBean3) {
			System.out.println("noBean3 = " + noBean3);
		}
	}
}
