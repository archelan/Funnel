import java.util.Arrays;


public class Funnel {
	
	int []array; 
	
	int minSize = 10;
	
	public void sort(int[] inArray){
		array = inArray;
		recursionSort(0, array.length-1);	
	}
	
	void recursionSort(int first, int last){
		int n = last - first + 1;
		if (n < minSize) {
			simpleSort(first, last);			
		} else {		
			int segmentSize = (int)Math.pow(n, 2./3);
			int segmentsQuantity = (n + segmentSize - 1) / segmentSize;
			int i;
			for (i = 0; i < segmentsQuantity - 1; i++) { 
				recursionSort(first + i*segmentSize, first + (i+1)*segmentSize - 1);
			}
			recursionSort(first + i*segmentSize, last);
			
			Buffer[] inBuffers = new Buffer[segmentsQuantity];
			for (i = 0; i < segmentsQuantity - 1; i++) {
				inBuffers[i] = new OnArrayBuffer(array, first + i*segmentSize, first + (i+1)*segmentSize - 1);
			}
			inBuffers[i] = new OnArrayBuffer(array, first + i*segmentSize, last);
			
			int [] outArray = new int [n];
			OnArrayBuffer outBuffer = new OnArrayBuffer(outArray, 0, n-1);			
			new LazyKMerger(segmentsQuantity, inBuffers, outBuffer);
			System.arraycopy(outBuffer.array, 0, array, first, n);
		}
	}
	
	void simpleSort(int first, int last){
		Arrays.sort(array, first, last+1);
	}
}
