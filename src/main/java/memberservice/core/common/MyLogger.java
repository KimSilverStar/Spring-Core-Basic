package memberservice.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/* 로그 출력을 위한 클래스 */

@Component
//@Scope(value = "request")
// 웹 request 스코프 => HTTP Request 들어올 때마다 각각 생성되고 나갈 때 소멸됨
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// MyLogger 의 가짜 프록시를 만들어 MyLogger 를 주입받으려는 빈들에게 프록시를 주입
// => HTTP Request 가 아직 들어오지 않은 상황이더라도 프록시를 주입하여 오류 발생 X
// => MyLogger 의 기능이 필요한 시점에 가짜 프록시가 진짜 MyLogger 객체에게 위임
public class MyLogger {
	private String uuid;
	private String requestURL;

	// MyLogger 빈이 생성되는 시점에는 requestURL 을 알 수 없으므로,
	// setter 로 외부에서 requestURL 주입 받음
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public void log(String message) {
		System.out.println(
				"[" + uuid + "] [" + requestURL+ "] " + message
		);
	}

	/* HTTP Request 가 들어올 때, 초기화됨 */
	@PostConstruct
	public void init() {
		uuid = UUID.randomUUID().toString();
		System.out.println(
				"[" + uuid  + "] request scope bean create: " + this
		);
	}

	/* HTTP Request 가 나갈 때, 소멸됨 */
	@PreDestroy
	public void close() {
		System.out.println(
				"[" + uuid  + "] request scope bean close: " + this
		);
	}
}
