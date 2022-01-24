package memberservice.core.web;

import lombok.RequiredArgsConstructor;
import memberservice.core.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
// final 선언된 필드를 파라미터로 갖는 생성자 자동 생성 => 생성자 주입
public class LogDemoController {
	private final LogDemoService logDemoService;
	private final ObjectProvider<MyLogger> myLoggerProvider;
	// MyLogger 빈: 웹 request 스코프
	// - HTTP Request 가 들어올 때, 빈 생성됨
	// - HTTP Request 가 들어올 때까지 ObjectProvider 로 빈 요청을 지연시킴

	// requestURL: localhost:8080/local-demo
	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request) {	// request 정보 받음
		String requestURL = request.getRequestURL().toString();
		MyLogger myLogger = myLoggerProvider.getObject();
		// ObjectProvider.getObject() 호출 시점까지 컨테이너에게
		// MyLogger 빈 요청을 지연시킴
		myLogger.setRequestURL(requestURL);		// requestURL 로그 남김
		myLogger.log("controller test");		// 메시지 로그 남김

		logDemoService.logic("testId");
		return "OK";
	}
}
