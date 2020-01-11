import java.util.ArrayList;
import java.util.Collections;

/*
 * Elements stored in sorted arrays at each node position
 * Add new arrays for larger elements 
 */

public class ArrayTreeSmallAtTop extends DataStructure {
	private ArrayList<Node>[] heap;
	private int size=0;
	private int maxsize;
	private float removeMinTime, insertTime;
	
	//constructor
	@SuppressWarnings("unchecked")
	public ArrayTreeSmallAtTop(int n){
		maxsize = n+1;
		heap = (ArrayList<Node>[])new ArrayList[maxsize];
		for (int i=0;i<maxsize;i++) {
			heap[i] = new ArrayList<Node>();
		}
	}
	
	//insert method
	public Node[] insert(Node k){
		float start = System.currentTimeMillis();
		//if the tree is empty, insert node as root
		if (heap[0].size()==0) {
			heap[0] = new ArrayList<Node>();
			heap[0].add(k);
		}
		//tree is not empty
		else {
			//k is smaller than root
			if (k.f < heap[0].get(0).f) {
				//move root node to 'smallest' array, k is new root
				heap[1].add(heap[0].get(0));
				heap[0].remove(0);
				heap[0].add(k);
				//sort smallest array
				Collections.sort(heap[1], new SortNodes());
			}
			else {
				int i=1;
				for (i=1;i<maxsize;i++) {
					if (heap[i].size()==0) {
						heap[i].add(k);
						break;
					}
					else if (k.f <= heap[i].get(heap[i].size()-1).f) {
						heap[i].add(k);
						Collections.sort(heap[i], new SortNodes());
						break;
					}
				}
				if (i == maxsize) {
					heap[i].add(k);
					Collections.sort(heap[i], new SortNodes());
				}
			}
		}
		float end = System.currentTimeMillis();
		insertTime = end - start;
		
		return new Node[0];
	}
	
	//removeMin method
	public Node removeMin(){
		float start = System.currentTimeMillis();
		Node element = heap[0].remove(0);
		if (heap[1].size()!=0) {
			heap[0].add(heap[1].get(0));
			heap[1].remove(0);
		}
		for (int i=1;i<maxsize-1;i++) {
			if (heap[i].size()==0 && heap[i+1].size() !=0) {
				heap[i].add(heap[i+1].remove(0));
			}
		}
		
		float end = System.currentTimeMillis();
		removeMinTime = end-start;
		return element;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}
