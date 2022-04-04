package blockMethods;

import java.util.HashMap;

import problems.ThreadProblem;

public class SynchronizeMethod extends SharedLocationMethod {

	public SynchronizeMethod(ThreadProblem threadProblem) {
		super(threadProblem);
	}

	@Override
	public void sharedLocationMethod(int threadId, HashMap<String, Object> data) {
		synchronizeMethod(threadId, data);
	}
	
	private synchronized void synchronizeMethod(int threadId, HashMap<String, Object> data) {
		threadProblem.sharedLocationMethod(threadId, data);
	}

}
