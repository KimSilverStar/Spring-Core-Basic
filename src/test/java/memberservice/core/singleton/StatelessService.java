package memberservice.core.singleton;

public class StatelessService {
	private int price;		// 상태를 유지하는 필드
	// => Singleton 객체가 되면 공유 필드가 됨
	// => 공유 필드는 특정 클라이언트가 값을 변경하면, 문제 발생 가능
	// => 해결 방법) 스프링 빈은 무상태(stateless)로 설계할 것

	public int order(String name, int price) {
		System.out.println("name = " + name + ", price = " + price);
//		this.price = price;		// 문제 발생 !! => 공유 필드 값을 변경하지 말것
		return price;
	}

//	public int getPrice() {
//		return price;
//	}
}
