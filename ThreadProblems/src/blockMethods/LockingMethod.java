package blockMethods;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import problems.ThreadProblem;

public class LockingMethod extends SharedLocationMethod {
	private Lock lock = new ReentrantLock();
	
	public LockingMethod(ThreadProblem threadProblem) {
		super(threadProblem);
	}

	@Override
	public void reduce(int threadId, HashMap<String, Object> data) {
    	try {
    		lock.lock();
    		threadProblem.reduce(threadId,data);
    	}finally {
    		lock.unlock();
    	}
	}
}
