package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.Service;

/**
 * Spring demos.
 */
public class SpringDemo {

	public static void main(String[] args) {
		ApplicationContext factory = 
			new ClassPathXmlApplicationContext("beans.xml");
		
		Service s = factory.getBean("service", Service.class);
		s.doSomething();
	}
}