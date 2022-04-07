package blockMethods;

import java.util.HashMap;

import problems.ThreadProblem;

public abstract class SharedLocationMethod {
	private static final String LockingMethod = "LockingMethod";
	private static final String SynchronizeMethod = "SynchronizeMethod";
	private static final String None = "None";
	private static final String SemaphoreMethod = "SemaphoreMethod";
	private static final String[] BlockingMethods = {
			LockingMethod, SynchronizeMethod, None, SemaphoreMethod
	};
	public static final String DEFAULT = LockingMethod;
	
	protected ThreadProblem threadProblem;
	
	public abstract void reduce(int threadId, HashMap<String, Object> data);
	
	public SharedLocationMethod(ThreadProblem threadProblem) {
		this.threadProblem = threadProblem;
	}
	
	public static SharedLocationMethod selectBlockMethod(String blockmethod, ThreadProblem threadProblem) {
		boolean correct = true;
		SharedLocationMethod method;
		switch(blockmethod) {
			case LockingMethod:
				method =  new LockingMethod(threadProblem);
				break;
			case SynchronizeMethod:
				method =  new SynchronizeMethod(threadProblem);
				break;
			case None:
				method =  new None(threadProblem);
				break;
			case SemaphoreMethod:
				method =  new SemaphoreMethod(threadProblem);
				break;
			default:
				correct = false;
				method =  new LockingMethod(threadProblem);
				break;
		}
		if(correct) {
			System.out.println("Method Selected: " + blockmethod);
		}else {
			System.out.println("Deafault Method Selected: " + DEFAULT);
		}
		return method;
	}
	
	public static void printAvailableBlockingMethods() {
		for(String method : BlockingMethods) {
			System.out.println("Method name: " + method);
		}
	}
	
	
	
	
}
