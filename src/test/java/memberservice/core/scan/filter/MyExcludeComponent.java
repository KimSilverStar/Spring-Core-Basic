package memberservice.core.scan.filter;

import java.lang.annotation.*;

/*
@MyExcludeComponent 어노테이션이 선언된 클래스는
컴포넌트 스캔 대상에서 제외
*/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
