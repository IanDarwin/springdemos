package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
/**
 * Simple demonstration of "Around" advice.
 * Remember to add to Spring Bean Factory config too
 */
public class AroundAdviceExample {
	
	@Around("execution(* rain.RainForestService.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("AroundAdviceExample.around()");
		// do before work here
		String methodName = pjp.getSignature().getName();
		if (methodName.contains("Q")) {
			throw new SecurityException("Invalid method " + methodName);
		}
		Object[] args = pjp.getArgs();
		for (Object o : args) {
			if (((String)o).contains("/etc")) {
				throw new SecurityException("Invalid arg: " + o);
			}
		}
		
		Object ret = pjp.proceed();
		
		// do after work here
		System.out.println("Returning from " + methodName);
		
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
