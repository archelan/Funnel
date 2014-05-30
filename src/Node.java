
public class Node {
	Buffer leftInBuffer;
	Buffer rightInBuffer;
	Buffer OutBuffer;
	Node leftNode = null;
	Node rightNode = null;
	boolean exhausted = false;
	boolean bottomNode = false;
	
	void lazy_fill(){		
		while (OutBuffer.isNotFull()) {
			if (!bottomNode && leftInBuffer.isEmpty() && leftNode.exhausted == false) { 
				leftNode.lazy_fill();
			}
			if (!bottomNode && rightInBuffer.isEmpty() && rightNode.exhausted == false) { 
				rightNode.lazy_fill();
			}
			if (leftInBuffer.isEmpty() && rightInBuffer.isEmpty()) { 
				break;
			} else if (leftInBuffer.isEmpty()) {
				OutBuffer.put(rightInBuffer.pop());
			} else if (rightInBuffer.isEmpty()) {
				OutBuffer.put(leftInBuffer.pop());
			} else {					
				if (leftInBuffer.head() < rightInBuffer.head()){
					OutBuffer.put(leftInBuffer.pop());
				} else {
					OutBuffer.put(rightInBuffer.pop());
				}
			}
		}
		if (bottomNode) {
			if (leftInBuffer.isEmpty() && rightInBuffer.isEmpty()) exhausted = true;
		} else {
			if (leftInBuffer.isEmpty() && leftNode.exhausted 
				&& rightInBuffer.isEmpty() && rightNode.exhausted) exhausted = true;
		}		
	}
}
