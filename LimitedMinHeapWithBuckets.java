import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * MinHeap with only 2 levels
 * Everything else sorted into two unsorted bucket
 * Large elements discarded
 */
public class LimitedMinHeapWithBuckets extends DataStructure{
	
	private Node[] heap;
	private int size=0,listsize=0;
	private int maxsize;
	private float removeMinTime, insertTime;
	private ArrayList<Node> smallBucket = new ArrayList<Node>();
	private ArrayList<Node> medBucket = new ArrayList<Node>();
	
	//constructor
	public LimitedMinHeapWithBuckets(){
		maxsize = 3;
		heap = new Node[maxsize+1];
	}

	public Node[] insert(Node k) {
		float start = System.currentTimeMillis();
		if (size<maxsize){
			heap[size+1] = k;
			size++;
			int parent = (int)Math.floor((size)/2);
			int child = size;
			for (int i=parent; i>0;){
				if (heap[i].f > heap[child].f){
					Node temp = heap[i];
					heap[i] = heap[(int)child];
					heap[child] = temp;
					child = parent;
					parent = (int)Math.floor((parent/2));
				}
				else break;
			}
		}
		else{
			if (k.f/heap[size].f <= 1) {
				smallBucket.add(k);
			}
			else if (k.f/heap[size].f < 3) {
				medBucket.add(k);
			}
		}
		float end = System.currentTimeMillis();
		insertTime = end - start;
		return heap;
	}
	
	public Node removeMin() {
		float start = System.currentTimeMillis();
		Node min = heap[1];
		if (size == 1) {
			heap[1] = null;size--;
		}
		if (size == 2) {
			heap[1] = heap[2];
			heap[2] = null;
			size--;
		}
		else if (size == 3) {
			if (heap[2].f < heap[3].f) {
				heap[1] = heap[2];
				heap[2] = heap[3];
				if (smallBucket.size()>0) {
					heap[3] = smallBucket.remove(0);
				}
				else if (medBucket.size()>0) {
					heap[3] = medBucket.remove(0);
				}
			}
			else {
				heap[1] = heap[3];
				if (smallBucket.size()>0) {
					heap[3] = smallBucket.remove(0);
				}
				else if (medBucket.size()>0) {
					heap[3] = medBucket.remove(0);
				}
			}
		}
		
		float end = System.currentTimeMillis();
		removeMinTime = end-start;
		return min;
	}
	
	public boolean isEmpty() {
		if (size==0){
			return true;
		}
		else return false;
	}
}
