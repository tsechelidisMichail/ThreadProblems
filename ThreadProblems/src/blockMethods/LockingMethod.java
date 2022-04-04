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
	public void sharedLocationMethod(int threadId, HashMap<String, Object> data) {
		lockingMethod(threadId,data);
	}
	
	private void lockingMethod(int threadId, HashMap<String, Object> data) {
    	try {
    		lock.lock();
    		threadProblem.sharedLocationMethod(threadId,data);
    	}finally {
    		lock.unlock();
    	}
	}

}
