package main;

import java.util.HashMap;

import blockMethods.SharedLocationMethod;
import problems.ThreadProblem;

public class MyThread extends Thread{
	private ThreadProblem threadProblem;
	private SharedLocationMethod sharedLocationMethod;
	private HashMap<String, Object> data;
	
	private int threadId;
	
	public MyThread(int threadId, HashMap<String, Object> data, ThreadProblem threadProblem, SharedLocationMethod sharedLocationMethod) {
		this.data = data;
		this.threadProblem = threadProblem;
		this.sharedLocationMethod = sharedLocationMethod;
		this.threadId = threadId;
	}

	@Override
	public void run() {
		data = threadProblem.reduce(threadId,data);
		sharedLocationMethod.sharedLocationMethod(threadId,data);
	}

}
