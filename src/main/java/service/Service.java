package service;

public interface Service {

	void doSomething();
	
	/** A method that should be rejected by the AroundAdvice */
	void getQ();	// Q is a secret, as in 007.

}
