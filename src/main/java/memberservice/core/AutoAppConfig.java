package memberservice.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
* 기존 AppConfig: @Bean 으로 직접 스프링 빈으로 등록
* AutoAppConfig: 컴포넌트 스캔으로 스프링 빈 자동 등록, @Autowired 자동 DI

1. @ComponentScan: @Component 선언된 클래스를 찾아서 자동으로 스프링 빈으로 등록
 - @Component 선언: MemoryMemberRepository, RateDiscountPolicy,
 				   MemberServiceImpl, OrderServiceImpl
 - @Autowired 선언: MemberServiceImpl, OrderServiceImpl
2. @Configuration: 내부에 @Component 어노테이션 포함
*/

@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(
		type = FilterType.ANNOTATION,
		classes = Configuration.class
))
// @Configuration 어노테이션이 붙은 클래스는 컴포넌트 스캔 대상에서 제외
// => AppConfig, TestConfig 등 충돌나는 다른 Config 클래스는 제외
public class AutoAppConfig {
}
