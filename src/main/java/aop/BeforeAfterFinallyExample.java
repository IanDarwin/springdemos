package aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BeforeAfterFinallyExample {
	final static String POINTCUT_EXPR =
		"execution(* service.*.*(..))";
	
	@Before(POINTCUT_EXPR)
	public void getLock() {
		System.out.println("GOT LOCK");
	}
	
	@After(POINTCUT_EXPR)
	public void releaseLock() {
		System.out.println("RELEASING LOCK");
	}
}
