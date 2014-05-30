
public class LazyKMerger {
	int size;
	Buffer[] inBuffers;
	Buffer outBuffer;	
	int currentBuffer = 0;	
	int height;
	InMemoryBuffer fakeEmptyBuffer;

	public LazyKMerger(int size, Buffer[] inBuffers, Buffer outBuffer){		
		this.inBuffers = inBuffers;
		this.outBuffer = outBuffer;
		this.size = size;
		this.fakeEmptyBuffer = new InMemoryBuffer();
		this.fakeEmptyBuffer.isEmpty = true;
		height = (int) (Math.log(size)/Math.log(2));			
		Node head = createNode(0);//creates Tree
		head.OutBuffer = outBuffer;
		head.lazy_fill();		
	}
	
	public Node createNode(int depth) {
		Node current = new Node();
		if (depth == height) {
			if (currentBuffer < size) 
				current.leftInBuffer = inBuffers[currentBuffer++];
			else
				current.leftInBuffer = fakeEmptyBuffer;
			
			if (currentBuffer < size)
				current.rightInBuffer = inBuffers[currentBuffer++];
			else
				current.rightInBuffer = fakeEmptyBuffer;
			current.bottomNode = true;
		} else {
			current.leftInBuffer = new InMemoryBuffer();
			current.leftNode = createNode(++depth);
			current.leftNode.OutBuffer = current.leftInBuffer;
			current.rightInBuffer = new InMemoryBuffer();
			current.rightNode = createNode(depth);
			current.rightNode.OutBuffer = current.rightInBuffer;
		}
		return current;
	}
}
