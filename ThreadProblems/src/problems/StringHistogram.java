package problems;

import java.util.HashMap;

public class StringHistogram extends ThreadProblem {
	private final String myString = "# ThreadProblems\r\n"
			+ "<br><br>\r\n"
			+ "For using jar->Download-><br>\r\n"
			+ "```java -cp ABSOLUTE_PATH/ThreadProblems.jar main.Main```\r\n"
			+ "<br><br>\r\n"
			+ "//TODO:Make this a proper README. This project was done for educational purposes. The goal was to understand the pattern/framework of using threads into a given problem.\r\n"
			+ "<br>\r\n"
			+ "So in the end the only thing that a programmer needs to write is the actual code for the problem, and hide the complexity of threading.\r\n"
			+ "<br>\r\n"
			+ "This framework is suitable for specifying A Single Type of a problem per run.(Thrading into a single group of responsibilities)\r\n"
			+ "<br><br>\r\n"
			+ "How to create a problem\r\n"
			+ "<br><br>\r\n"
			+ "Create a class into problems package and extend it to ThreadProblems. \r\n"
			+ "<br>Now depending on your problem you shall define the implemented methods.\r\n"
			+ "<br>Take care of fields at your class, they are beeing considered as all threads accesible.\r\n"
			+ "<br>Use the already problems created as your guide. \r\n"
			+ "<br>sharedLocationMethod is used for safe usage of ThreadProblem fields. \r\n"
			+ "<br>Use map function to create your data and use them in the reduce function as needed.\r\n"
			+ "<br><br>\r\n"
			+ "How to create a blocking method\r\n"
			+ "<br><br>\r\n"
			+ "Create a class at blockMethods package and extend it to SharedLocationMethod. \r\n"
			+ "<br>Then create a method that calls threadProblem.sharedLocationMethod(). \r\n"
			+ "<br>Then call this method from the unimplemented method of the class.\r\n"
			+ "<br><br>\r\n"
			+ "Attention\r\n"
			+ "<br><br>\r\n"
			+ "In both cases -> Update the superclass with a private static String with the name of your created class. \r\n"
			+ "<br>Then add that string to the respective Array. \r\n"
			+ "<br>Then create a case at the selection method. \r\n"
			+ "<br>Use the already created custom classes as your guide.";
	private final int myString_length;
	private int dividable;
	private final int alphabetSize = 256;
	{	
		this.SHARED_DATA.put("histogram", this.histogramCreation());
		myString_length = myString.length();
		
		dividable = myString_length;
		while(dividable%this.numThreads!=0) {
			dividable--;
		}
	}

	@Override
	protected HashMap<String, Object> map(int threadId) {
		HashMap<String, Object> data = new HashMap<String, Object>();

		int block = dividable/ this.numThreads;
		int start = threadId*block;
		int finish = start + block;
		
		data.put("start",start);
		data.put("finish",finish);
		data.put("block",block);
		
		return data;
	}

	@Override
	public HashMap<String, Object> reduce(int threadId, HashMap<String, Object> data) {
		int start = (int) data.get("start");
		int finish = (int) data.get("finish");
			
		int[] histogram = histogramCreation();
	    
	    for (int i = start; i < finish; i++) {
			histogram[((int)myString.charAt(i))] ++;
	    }
	    data.put("histogram", histogram);
		return data;
	}

	@Override
	public void sharedLocationMethod(int threadId, HashMap<String, Object> data) {
		int[] histogramTotal = (int[])this.SHARED_DATA.get("histogram");
		int[]histogram = (int[])data.get("histogram");
		int[] temp = this.histogramCreation();
	    for (int i = 0; i < alphabetSize; i++) {
	    	temp[i] = histogram[i] + histogramTotal[i]; 
	    }
	    this.SHARED_DATA.put("histogram", temp);
	}

	@Override
	public void printResults() {
		int[] histogramTotal = (int[])this.SHARED_DATA.get("histogram");
		
	    for (int i = 0; i < alphabetSize; i++) {
	    	int temp = histogramTotal[i];
	    	if(temp!=0) {
	    		System.out.println((char)i+": "+ temp);
	    	}
	    }
	}
	
	private int[] histogramCreation() {
		int[] histogram = new int[alphabetSize];
	    for (int i = 0; i < alphabetSize; i++) { 
	        histogram[i] = 0; 
	    }
	    return histogram;
	}
}
