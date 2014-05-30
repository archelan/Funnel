import java.util.LinkedList;

public class InMemoryBuffer implements Buffer{
	static int maxSize = 1024;
	LinkedList<Integer> content = new LinkedList<Integer>(); 
	boolean isNotFull = true;
	boolean isEmpty = true;	
		
	public int pop() {		
		int t = content.poll();
		if (content.isEmpty()){
			isEmpty = true;
		}
		if (content.size() < maxSize) {
			isNotFull = true;
		}
		return t;
	}
	
	public int head() {
		return content.getFirst();
	}

	public void put(int t) {
		content.add(t);
		if (content.size() >= maxSize) {
			isNotFull = false;
		}
		isEmpty = false;
	}

	public boolean isNotFull() {		
		return isNotFull;
	}

	public boolean isEmpty() {
		return isEmpty;
	}
	
}
