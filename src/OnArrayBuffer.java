/**
 * It's important to understand, what OnArrayBuffer is.
 * That buffers always exists on the top(outBuffer) of kMerger, or on the bottom(inBuffer).
 * So, it's only for puts, or only for pops.
 * Therefore, we can just iterate from first(smallest) to last(biggest), incrementing 'current' variable.
 * Also notice, that 'isNotFull()' method is used only with outBuffers, and 'isEmpty' only in with inBuffers
 * In correct realization, that buffer should reside on HDD.
*/
public class OnArrayBuffer implements Buffer{
	int first;
	int last;
	int current;
	int[] array;
	boolean endReached;
		
	public OnArrayBuffer(int[] array, int first, int last){
		this.array = array;
		this.current = first;
		this.first = first;
		this.last = last;
		if (first > last) {
			endReached = true;
		} else {
			endReached = false;
		}
	}

	public int pop() {		
		int t = array[current++];
		if (current > last) {
			endReached = true;
		}
		return t;
	}

	public int head() {
		return array[current];
	}

	public void put(int t) {
		array[current++] = t;
		if (current > last) {
			endReached = true;
		}
	}

	public boolean isNotFull() {
		return !endReached;//read class description
	}

	public boolean isEmpty() {
		return endReached;//read class description
	}
	
}
