
/*
 * Standard MinHeap
 */

public class GridMinHeap extends DataStructure {
	private Node[] heap;
	private int size=0;
	private int maxsize;
	private float removeMinTime, insertTime;
	
	//constructor
	public GridMinHeap(int n){
		maxsize = n+1;
		heap = new Node[maxsize];
	}
	
	//insert method
	public Node[] insert(Node k){
		float start = System.currentTimeMillis();
		if (size<maxsize){
			heap[size+1] = k;
			size++;
			int parent = (int)Math.floor((size)/2);
			int child = size;
			while (parent != 0) {
				//System.out.println(parent + " "+child);
				if (heap[parent].f > heap[child].f){
					Node temp = heap[parent];
					heap[parent] = heap[(int)child];
					heap[child] = temp;
					child = parent;
					parent = (int)Math.floor((parent/2));
				}
				else if (heap[parent].f == heap[child].f) {
					if (heap[parent].h > heap[child].h) {
						Node temp = heap[parent];
						heap[parent] = heap[(int)child];
						heap[child] = temp;
					}
					child = parent;
					parent = (int)Math.floor((parent/2));
				}
				else break;
			}
		}
		else{
			System.out.println("Heap is full!");
		}
		float end = System.currentTimeMillis();
		insertTime = end - start;
		return heap;
	}
	
	//removeMin method
	public Node removeMin(){
		float start = System.currentTimeMillis();
		Node element = min();
		heap[1] = heap[size];
		size--;
		int parent = 1;
		int child1 = parent*2;
		int child2 = parent*2+1;
		for (int i=parent;i < maxsize;){
			if (heap[child1] != null && heap[child2] != null) {
				if ((heap[parent].f > heap[child1].f || heap[parent].f >heap[child2].f ) && heap[child1].f < heap[child2].f){	
					Node temp = heap[parent];
					heap[parent] = heap[child1];
					heap[child1] = temp;
					parent = child1;
					child2 = child1*2+1;
					child1 = child1*2;
				}
				else if((heap[parent].f>heap[child1].f || heap[parent].f>heap[child2].f) && heap[child2].f<=heap[child1].f){
					Node temp = heap[parent];
					heap[parent] = heap[child2];
					heap[child2] = temp;
					parent = child2;
					child1 = child2*2;
					child2 = child2*2+1;
				}
				else break;
			}
			else if (heap[child1] != null && heap[child2] != null) {
				if (heap[parent].f > heap[child1].f){	
					Node temp = heap[parent];
					heap[parent] = heap[child1];
					heap[child1] = temp;
					parent = child1;
					child1 = child1*2;
				}
			}
			else {
				break;
			}
		}
		float end = System.currentTimeMillis();
		removeMinTime = end-start;
		return element;
	}
	
	//min method
	public Node min(){
		return heap[1];
	}
	
	//size method
	public int size(){
		return size;
	}
	
	//isEmpty method
	public boolean isEmpty(){
		if (size==0){
			return true;
		}
		else return false;
	}
	
	//displayHeap method
	public void displayHeap(){
		for (int i=1;i<size+1;i++){
			System.out.printf("%s ",heap[i].getName());
		}
		System.out.println("");
	}
}
