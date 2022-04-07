package main;

import java.util.HashMap;

import blockMethods.SharedLocationMethod;
import problems.ThreadProblem;

public class MyThread extends Thread{
	private ThreadProblem threadProblem;
	private SharedLocationMethod sharedLocationMethod;
	private HashMap<String, Object> data;
	
	private int threadId;
	
	public MyThread(int threadId, ThreadProblem threadProblem, SharedLocationMethod sharedLocationMethod) {
		this.data = new HashMap<String, Object>();
		this.threadProblem = threadProblem;
		this.sharedLocationMethod = sharedLocationMethod;
		this.threadId = threadId;
	}

	@Override
	public void run() {
		data = threadProblem.map(threadId,data);
		data = threadProblem.reduce(threadId,data);
		sharedLocationMethod.sharedLocationMethod(threadId,data);
	}

}
