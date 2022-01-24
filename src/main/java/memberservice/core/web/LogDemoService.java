package memberservice.core.web;

import lombok.RequiredArgsConstructor;
import memberservice.core.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// final 선언된 필드를 파라미터로 갖는 생성자 자동 생성 => 생성자 주입
public class LogDemoService {
	private final ObjectProvider<MyLogger> myLoggerProvider;
	// MyLogger 빈: 웹 request 스코프
	// - HTTP Request 가 들어올 때, 빈 생성됨
	// - HTTP Request 가 들어올 때까지 ObjectProvider 로 빈 생성을 지연시킴

	public void logic(String id) {
		// ObjectProvider.getObject() 호출 시점까지 컨테이너에게
		// MyLogger 빈 요청을 지연시킴
		MyLogger myLogger = myLoggerProvider.getObject();
		myLogger.log("service id = " + id);
	}
}
