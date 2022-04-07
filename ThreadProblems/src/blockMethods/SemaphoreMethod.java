package blockMethods;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

import problems.ThreadProblem;

public class SemaphoreMethod extends SharedLocationMethod {

	private Semaphore sem = new Semaphore(1);

	public SemaphoreMethod(ThreadProblem threadProblem) {
		super(threadProblem);
	}

	@Override
	public void reduce(int threadId, HashMap<String, Object> data) {
		try {
		    sem.acquire();
		    this.threadProblem.reduce(threadId, data);
		} catch (InterruptedException e) {
	    }
		sem.release();
	}

}
