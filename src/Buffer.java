
interface Buffer {
	boolean exhausted = false;
	int pop();
	int head();
	void put(int t);
	boolean isNotFull();
	boolean isEmpty();
}
