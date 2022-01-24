package memberservice.core.lifecycle;

/*
 * 빈 생명주기 콜백
  - @PostConstruct, @PreDestroy 어노테이션 사용
    => 자바에서 지원하는 어노테이션
 */

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient3 {
	private String url;

	public NetworkClient3() {
		System.out.println("생성자 호출, url = " + url);
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/* 서비스 시작 시 호출 => 네트워크와 연결 역할 */
	public void connect() {
		System.out.println("connect: " + url);
	}

	public void call(String message) {
		System.out.println("call: " + url + ", message = " + message);
	}

	/* 서비스 종료 시 호출 => 네트워크와 연결 종료 역할 */
	public void disconnect() {
		System.out.println("close: " + url);
	}

	/* 초기화: 빈에 의존관계 주입이 끝난 후 호출 */
	@PostConstruct
	public void init() {
		// 네트워크와 연결
		System.out.println("NetworkClient3.init");
		connect();
		call("초기화 연결 메시지");
	}

	/* 소멸: 스프링 컨테이너 / 빈이 종료될 때 호출 */
	@PreDestroy
	public void close() {
		// 네트워크와 연결 종료
		System.out.println("NetworkClient3.close");
		disconnect();
	}
}
