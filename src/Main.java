import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Main {
	static String filename = "numbers.txt";
	static boolean okStatus = true;
	static int n;

	public static void main(String[] args) {		
		n = ((Integer)((int)(Math.random()*50000000)));
		//n = 25;
		int maxNumber = Integer.MAX_VALUE;
		printNumbers(maxNumber, n);//Generate new numbers and store them in 'numbers.txt'.
		//Comment previous line, if u want to don't want to change numbers on every launch.
		int[] array;
		try {
			array = readNumbers(n);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}				
		int []javaSortResult = Arrays.copyOf(array, n);
		
		long start,end;
		start = System.currentTimeMillis();	
		Arrays.sort(javaSortResult);
		end = System.currentTimeMillis();
		System.out.println("Java sort complited in " + (end - start) + " milliseconds.");
		
		start = System.currentTimeMillis();
		new Funnel().sort(array);
		end = System.currentTimeMillis();
		System.out.println("Funnel sort complited in " + (end - start) + " milliseconds.");
		//javaSortResult[5] = 0;
		int errorcnt = 0;
		
		
		for (int i=0; i < n; i++) {
			if (array[i] != javaSortResult[i]) {
				if (++errorcnt > 10) {
					System.out.println("There are more than 10 errors, stopping.");
					break;
				}
				System.out.println("Error on " + i +" position.");
				System.out.println("	Java sort: " + javaSortResult[i]);
				System.out.println("	Funnel sort: " + array[i]);
				System.out.println();
				okStatus = false;
			}			
		}
		if (errorcnt == 0) {
			okStatus = true;
			System.out.println("Results are the same.");
		}		
	}
	
	static void printNumbers(int max, int n){
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(filename), "utf-8"));
		    for (int i = 0; i < n; i++) {		    	
		    	writer.write(((Integer)((int)((Math.random()-0.5)*max))).toString()+" ");
		    }
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
	
	static int[] readNumbers(int n) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File(filename));
		int [] t = new int [n];
		int i = 0;
		while(scanner.hasNextInt())
		{
		     t[i++] = scanner.nextInt();
		}		
		scanner.close();
		return t;
	}

}
