package service;

public class ServiceImpl implements Service {

	public void doSomething() {
		System.out.println("ServiceImpl.doSomething()");
	}

	public void getQ() {
		System.out.println("ERROR: got into ServiceImpl.getQ()");
	}

}
