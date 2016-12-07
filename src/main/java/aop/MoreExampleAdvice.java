package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/** Example shows multiple advice methods within a single class */
@Aspect
public class MoreExampleAdvice {

	/** Any of the various types MAY have a JoinPoint as its first argument */
	@Before("execution(* service.*.*(..))")
	public void before(JoinPoint jp) {
		System.out.print("Before method " + jp.getSignature().getName() + "; ");
		System.out.println("Input arguments: " +jp.getArgs());
		System.out.println("Target: " + jp.getTarget());
	}
	
	/** Around advice MUST have a ProceedingJoinPoint (JoinPoint subclass);]
	 * Here we show how an Around Advice can change the input arguments.
	 */
	@Around("execution(* service.*.*(..))")
	public Object monitor(ProceedingJoinPoint pjp) throws Throwable {
		System.out.print("Around method " + pjp.getSignature().getName() + "; ");
		System.out.printf("Input arguments: ");
		Object[] args = pjp.getArgs();
		int n = args.length;
		Object[] newArgs = new Object[n];
		for (int i = 0; i < args.length; i++) {
			Object o = args[i];
			System.out.printf("'%s' ", o);
			if (o instanceof String) {
				String s = (String) o;
				if (s.startsWith("Q")) {
					newArgs[i] = "I didn't like your argument!";
				} else {
					newArgs[i] = args[i];
				}
			}
		}
		System.out.println();
		
		Object ret = pjp.proceed(newArgs);
		
		return ret;
	}
}
