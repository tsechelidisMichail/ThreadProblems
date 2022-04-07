package blockMethods;

import java.util.HashMap;

import problems.ThreadProblem;

public class SynchronizeMethod extends SharedLocationMethod {

	public SynchronizeMethod(ThreadProblem threadProblem) {
		super(threadProblem);
	}

	@Override
	public void reduce(int threadId, HashMap<String, Object> data) {
		synchronizeMethod(threadId, data);
	}
	
	private synchronized void synchronizeMethod(int threadId, HashMap<String, Object> data) {
		threadProblem.reduce(threadId, data);
	}

}
