
public class Edge {
	private Node end1;	//one of the end nodes
	private Node end2;	//the other end node
	private int cost;	//the cost to traverse the edge
	
	//constructor
	public Edge(Node end1, Node end2, int cost) {
		this.end1 = end1;
		this.end2 = end2;
		this.cost = cost;
	}
	
	//retrieve end1
	public Node getEnd1() {
		return end1;
	}
	
	//retrieve end2
	public Node getEnd2() {
		return end2;
	}
	
	//retrieve the cost
	public int getCost() {
		return cost;
	}
}
