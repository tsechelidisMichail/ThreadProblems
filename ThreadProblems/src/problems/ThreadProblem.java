package problems;

import java.util.HashMap;

import blockMethods.SharedLocationMethod;
import main.MyThread;

public abstract class ThreadProblem {
	private static final String IntegralPi = "IntegralPi";
	private static final String SharedCounter = "SharedCounter";
	private static final String StringHistogram = "StringHistogram";
	private static final String[] Problems = {
			IntegralPi, SharedCounter, StringHistogram
	};
	public static final String DEFAULT = IntegralPi;
	
	protected final int numThreads = Runtime.getRuntime().availableProcessors();
	private MyThread[] threads = new MyThread[numThreads];
	
	protected HashMap<String, Object> SHARED_DATA = new HashMap<String, Object>();
	
	//TODO:CONSIDER REMOVING MAP METHOD->MAPPING CAN BE DONE INSIDE REDUCE + CHANGE NAMING
	protected abstract HashMap<String, Object> map(int threadId);
	public abstract HashMap<String, Object> reduce(int threadId, HashMap<String, Object> data);
	public abstract void sharedLocationMethod(int threadId, HashMap<String, Object> data);
	public abstract void printResults();
	
	public void start(SharedLocationMethod sharedLocationMethod) {
		System.out.println("Available Threads:" + numThreads + "\nStarting...");
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new MyThread(i, map(i), this, sharedLocationMethod);
			threads[i].start();
		}
	}

	public void stop() {
		System.out.println("Joining threads...");
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		}
	}
	
	public static ThreadProblem selectProblem(String problem) {
		boolean correct = true;
		ThreadProblem temp;
		switch(problem) {
			case IntegralPi:
				temp = new IntegralPi();
				break;
			case SharedCounter:
				temp = new SharedCounter();
				break;
			case StringHistogram:
				temp = new StringHistogram();
				break;
			default:
				correct = false;
				temp =  new IntegralPi();
				break;
		}
		if(correct) {
			System.out.println("Problem Selected: " + problem);
		}else {
			System.out.println("Deafault Problem Selected: " + DEFAULT);
		}
		return temp;
	}
	
	public static void printAvailableProblems() {
		for(String problem : Problems) {
			System.out.println("Problem name: " + problem);
		}
	}
}
