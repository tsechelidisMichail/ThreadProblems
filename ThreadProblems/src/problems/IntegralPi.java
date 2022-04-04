package problems;

import java.util.HashMap;

public class IntegralPi extends ThreadProblem{
	//THIS IS PUBLIC AREA FOR ALL THREADS
	private final long numSteps = 1000000000;
	private final double step = 1.0 / (double)numSteps;
	{this.SHARED_DATA.put("pi", 0.0);}
	
	//DUE TO THE NATURE OF DOUBLE TYPES, PI MAY DEFER AT A FAR AWAY DECIMAL
	//IT IS NOT GOOD FOR TESTING WITH MAIN TEST FUNCTION, BETTER USE TEST CLASS
	
	@Override
	protected HashMap<String, Object> map(int threadId) {
		//This instantiates ONCE the private data for a thread
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		long stepsPerThread = numSteps / this.numThreads;
		long start = threadId*stepsPerThread;
		long finish = start + stepsPerThread;
		
		data.put("start",start);
		data.put("finish",finish);
		
		return data;
	}
	
	@Override
	public HashMap<String, Object> reduce(int threadId, HashMap<String, Object> data) {
		//This uses data that is shared by all threads - aka the fields of this class
		
		long start = (long) data.get("start");
		long finish = (long) data.get("finish");
		
		double regionalSum = 0.0;
		
        for (long i=start; i < finish; ++i) {
            double x = ((double)i+0.5)*step;
            regionalSum += 4.0/(1.0+x*x);
        }
        data.put("regionalSum", regionalSum);
        return data;
        
	}
	
	@Override
	public void sharedLocationMethod(int threadId, HashMap<String, Object> data) {
		//This uses data that is shared by all threads - aka the fields of this class
		//Here a block mechanism specified by the user. This enables to combine the per_thread data with the public available fields.
		double pi = ((double) data.get("regionalSum"))*step + (double) this.SHARED_DATA.get("pi");
		this.SHARED_DATA.put("pi", pi);
	}

	@Override
	public void printResults() {
		//Threads are done - No worries about accessing SHARED_OBJECT
		double pi = (double) this.SHARED_DATA.get("pi");
		System.out.printf("sequential program results with %d steps\n", numSteps);
		System.out.printf("computed pi = %22.20f\n" , pi);
	    System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
	}
}
