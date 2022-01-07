package memberservice.core.beanfind;

import memberservice.core.member.MemberRepository;
import memberservice.core.member.MemoryMemberRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

class ApplicationContextSameBeanFindTest {
	AnnotationConfigApplicationContext ac =
			new AnnotationConfigApplicationContext(SameBeanConfig.class);

	@Test
	@DisplayName("빈 타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 중복 예외 발생")
	void findBeanByTypeDuplicate() {
		assertThrows(NoUniqueBeanDefinitionException.class,
					 () -> ac.getBean(MemberRepository.class)
		);
	}

	@Test
	@DisplayName("빈 타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 이름을 지정할 것")
	void findBeanByName() {
		MemberRepository memberRepository = ac.getBean(
				"memberRepository1", MemberRepository.class
		);
		assertThat(memberRepository).isInstanceOf(MemberRepository.class);
		// 더 정확하게 검증하는 방법) 메소드 파라미터 값으로 검증
	}

	@Test
	@DisplayName("특정 빈 타입을 모두 조회")
	void findAllBeanType() {
		Map<String, MemberRepository> beansOfType =
				ac.getBeansOfType(MemberRepository.class);
		for (String key : beansOfType.keySet())
			System.out.println("key = " + key + ", value = " + beansOfType.get(key));
		System.out.println("beansOfType = " + beansOfType);

		assertThat(beansOfType.size()).isEqualTo(2);
	}

	/* 테스트 목적으로 중복 빈 생성을 위한 Config 클래스 (static 으로 Inner Class 선언) */
	@Configuration
	static class SameBeanConfig {
		// 같은 MemoryMemberRepository 객체 반환하는 두 메소드
		@Bean
		public MemberRepository memberRepository1() {
			return new MemoryMemberRepository();
		}

		@Bean
		public MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}
	}
}
