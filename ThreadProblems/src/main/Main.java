package main;


import java.util.Scanner;

import blockMethods.SharedLocationMethod;
import problems.SharedCounter;
import problems.ThreadProblem;

public class Main { 
	private static ThreadProblem threadProblem;
	private static SharedLocationMethod sharedLocationMethod;

	public static void main(String[] args) {
		//Uncomment for easy testing
		//test();
		startFromCommandLine(args);
		long startTime = System.currentTimeMillis();
		threadProblem.start(sharedLocationMethod);
		threadProblem.stop();
		long endTime = System.currentTimeMillis();
		System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
		threadProblem.printResults();
		System.out.printf("Exiting");
		System.exit(0);
	}

	private static void startFromCommandLine(String[] args) {
		if(args.length == 1 && args[0].equals("-h")) {
			printHelp();
			
		}else if(args.length == 1 && args[0].equals("-s")) {
			printAvailableProblems();
			
		}else if(args.length == 1 && args[0].equals("-S")) {
			printAvailableBlockingMethods();
			
		}else if(args.length == 1 && args[0].equals("-t")) {
			test();
			
		}else if(args.length >= 1 && args[0].equals("-r")){
			startProgram(args);
			
		}else {
			noArgsRun();
		}
	}
	
	private static void startProgram(String[] args) {
		String problem = "";
		String blockMethod = "";
		
		if(args.length != 1) {
			for(int i=0; i<args.length;i++) {
				if(args[i].equals("-p"))
					problem = safeArgsAssignment(args,i);
				if(args[i].equals("-b"))
					blockMethod = safeArgsAssignment(args,i);
				}
		}
		//ATTENTION: Problem and method have to be created together and then linked together
		//Each problem Object knows its own method Object + Each method Object knows its own problem Object
		//It's a 2 way relationship
		threadProblem = ThreadProblem.selectProblem(problem);
		sharedLocationMethod = SharedLocationMethod.selectBlockMethod(blockMethod,threadProblem);
		askConfirmation();
	}

	private static String safeArgsAssignment(String[] args, int i) throws NullPointerException{
		String temp = "";
		try{
			temp = args[i+1];
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Use of " + args[i] +" expects a value");
		}
		return temp;
	}

	private static void askConfirmation() {
		while(true) {
			System.out.println("Do you want to continue? [Y/n]");
			Scanner keyboard = new Scanner(System.in);
			String confirm = keyboard.next();
			if(confirm.equals("Y")) {
				keyboard.close();
				System.out.println("Continuing...");
				break;
			}else if(confirm.equals("n")) {
				keyboard.close();
				System.out.println("Exiting...");
				System.exit(0);
			}else {
				System.out.println("Please select: [Y/n]");
			}		
		}
	}
	
	private static void printHelp() {
		System.out.println("Use -h for help");
		System.out.println("Use -r to run the program");
		System.out.println("Use -t to run the test(runs SharedCounter in a loop and compares)");
		System.out.println("Use -p to select problem");
		System.out.println("Use -b to select block method");
		System.out.println("Use -s to show available problems");
		System.out.println("Use -S to show available blocking methods");
		System.exit(0);
	}
	
	private static void noArgsRun() {
		System.out.println("To run the program use -r : The default problem is " + ThreadProblem.DEFAULT + "with blocking method " + SharedLocationMethod.DEFAULT + ".");
		System.out.println("Use -h to view options (!!! -h argument expected alone !!!)");
		System.out.println("First argument should be -r/h/s/S\nOnly -r can have selectors after it");
		System.out.println("\n\nExamples of usage:\nTPC -r\nTPC -r -p IntegralPi");
		System.exit(0);
	}

	private static void printAvailableProblems() {
		ThreadProblem.printAvailableProblems();
		System.exit(0);
	}
	
	private static void printAvailableBlockingMethods() {
		SharedLocationMethod.printAvailableBlockingMethods();
		System.exit(0);
	}
	/*THIS IS A TEST FOR TESTING BLOCKING METHODS
	 *IT IS HARD CODED WITH SharedCounter PROBLEM INTENDED
	 *FOR EASY TESTING JUST CALL FIRST TEST FUNCTION FROM main() -- UNCOMMENT IT.
	 */
	private static void test() {
		SharedCounter threadProblem = new SharedCounter();
		
		String blockMethod = "LockingMethod";
		System.out.println("Test will run with a Block Method.\nDo you want None???? [Y/n]");
		Scanner keyboard = new Scanner(System.in);
		String confirm = keyboard.next();
		if(confirm.equals("Y")) {
			blockMethod = "None";
		}
		keyboard.close();
		SharedLocationMethod sharedLocationMethod = SharedLocationMethod.selectBlockMethod(blockMethod,threadProblem);
		
		
		threadProblem.start(sharedLocationMethod);
		threadProblem.stop();	
		int k = threadProblem.getCounter();
		
		for(int i = 0; i<3000;i++) {
			threadProblem = new SharedCounter();
			sharedLocationMethod = SharedLocationMethod.selectBlockMethod(blockMethod,threadProblem);
			threadProblem.start(sharedLocationMethod);
			threadProblem.stop();
			System.out.println(k + "--<---initial----OK---at rerun--->--" + threadProblem.getCounter() + "---(time re-run)--" + i);
			if(k!=threadProblem.getCounter()) {
				threadProblem.getCounter();
				System.out.println(k + "--<---initial----fail---at rerun--->--" + threadProblem.getCounter()+ "--(time re-run)---" + i);
				break;
			}
			threadProblem.printResults();
		 }
		 System.out.println("Test is hardcoded configured with: (SharedCounter) problem.");
		 System.exit(0);
	}

}
