package memberservice.core.member;

public class MemberServiceImpl implements MemberService {
	/* 변경 전) DIP, OCP 위배 */
//	private final MemberRepository memberRepository = new MemoryMemberRepository();
	// 1. DIP (Dependency Inversion Principle: 인터페이스에만 의존할 것) 위배
	// 인터페이스(MemberRepository)와 구현 클래스(MemoryMemberRepository)에 동시 의존

	// 2. OCP (Open-Closed Principle) 위배
	// 향후 저장소 구현체를 변경하려고 하는 경우

	/* 변경 후 - 생성자를 통한 DI) DIP, OCP 만족 - interface 에만 의존 */
	private final MemberRepository memberRepository;

	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
	
	/* 테스드 용도로 추가한 메소드 - @Configuration 의 Singleton 테스트 */
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
