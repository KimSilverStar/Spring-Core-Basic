package memberservice.core.singleton;

public class SingletonService {
	// static 멤버 변수 선언 => 클래스(공유) 변수
	// 미리 static 영역에 인스턴스 1개 생성해놓음
	private static final SingletonService instance = new SingletonService();
	// Singleton 공유 변수 Naming 관례: instance

	// 외부에서는 static 메소드(클래스 메소드)만으로 인스턴스 조회 가능
	public static SingletonService getInstance() {
		return instance;
	}

	// private 생성자를 선언하여, 외부에서 직접 인스턴스 생성 방지
	private SingletonService() {}

	public void logic() {
		System.out.println("Singleton 객체 로직 호출");
	}
}
