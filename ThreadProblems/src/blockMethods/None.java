package blockMethods;

import java.util.HashMap;

import problems.ThreadProblem;

public class None extends SharedLocationMethod{

	public None(ThreadProblem threadProblem) {
		super(threadProblem);
	}

	@Override
	public void reduce(int threadId, HashMap<String, Object> data) {
		this.threadProblem.reduce(threadId, data);
	}
}
