package problems;

import java.util.HashMap;

public class SharedCounter extends ThreadProblem{
	{this.SHARED_DATA.put("sum", 0);}
	
	@Override
	public HashMap<String, Object> map(int threadId, HashMap<String, Object> data) {
		return data;
	}
	
	@Override
	public HashMap<String, Object> reduce(int threadId, HashMap<String, Object> data) {
		//This uses data that is shared by all threads - aka the fields of this class
		
		//count(threadId);
		
		return data;
	}

	@Override
	public void sharedLocationMethod(int threadId, HashMap<String, Object> data) {
		//This uses data that is shared by all threads - aka the fields of this class
		//Here a block mechanism specified by the user. This enables to combine the per_thread data 
		//with the public available fields.
		
		count(threadId);
		
	}



	@Override
	public void printResults() {
		System.out.println("RESULT:  " + (int)SHARED_DATA.get("sum"));
	}
	
	private void count(int threadId) {
		int counter = (int) SHARED_DATA.get("sum");
		for(int i = 0; i<4;i++) {
			counter++;
			SHARED_DATA.put("sum", counter);
			System.out.println("ThreadId ->: " +threadId +   "   |||   Loop Stage(i)->: " + i + "   |||   Shared Sum ->: " + counter);
		}
	}
	
	public int getCounter() {
		return (int)SHARED_DATA.get("sum");
	}

}
