package memberservice.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
* 빈 생명주기 콜백 - InitializingBean, DisposableBean 인터페이스 이용
*/

public class NetworkClient1 implements InitializingBean, DisposableBean {
	private String url;

	public NetworkClient1() {
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

	/* 초기화: 빈에 의존관계 주입이 끝난 후 (객체 Property 셋팅 끝난 후), 호출 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// 네트워크와 연결
		System.out.println("NetworkClient.afterPropertiesSet");
		connect();
		call("초기화 연결 메시지");
	}

	/* 소멸: 스프링 컨테이너 / 빈이 종료될 때 호출 */
	@Override
	public void destroy() throws Exception {
		// 네트워크와 연결 종료
		System.out.println("NetworkClient.destroy");
		disconnect();
	}
}
