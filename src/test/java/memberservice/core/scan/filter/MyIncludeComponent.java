package memberservice.core.scan.filter;

import java.lang.annotation.*;

/*
@MyIncludeComponent 어노테이션이 선언된 클래스는
컴포넌트 스캔 대상에 포함
*/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
