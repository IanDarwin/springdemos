package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Simple demonstration of "Around" advice.
 * Remember to add aop config to Spring Bean Factory config.
 */
@Aspect
public class AroundAdviceExample {
	
	@Around("execution(* main.SpringIntl.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("AroundAdviceExample.around()");
		// do before work here
		String methodName = pjp.getSignature().getName();
		if (methodName.contains("Q")) {	// slightly contrived example
			throw new SecurityException("Invalid method " + methodName);
		}
		Object[] args = pjp.getArgs();
		for (Object o : args) {
			if (((String)o).contains("/etc")) {
				throw new SecurityException("Invalid arg: " + o);
			}
		}
		
		try {
			Object ret = pjp.proceed();
			return ret;
		} finally {
			// do after work here, even if Exception thrown
			System.out.println("Returning from " + methodName);
		}
	}
}
