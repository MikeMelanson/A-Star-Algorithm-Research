
public class Node {
	private int estToFinish;	//estimate cost to the finish
	private String name;		//name of the node
	private boolean startNode;	//indicates if the node is the start node
	private boolean endNode;	//indicates if the node is the end node
	public int f,g,h;
	public Node parent;
	
	//constructor
	public Node(String name, int estToFinish, String s, String e) {
		this.estToFinish = estToFinish;
		h = estToFinish;
		this.name = name;
		if (s.equals("T")) {
			startNode = true;
		} else startNode = false;
		if (e.equals("T")) {
			endNode = true;
		} else endNode = false;
	}
	
	//retrieve the estimate to the finish
	public int getEstToFinish() {
		return estToFinish;
	}
	
	//retrieve the name
	public String getName() {
		return name;
	}
	
	public boolean isFirst() {
		if (startNode) return true;
		else return false;
	}
	
	public boolean isGoal() {
		if (endNode) return true;
		else return false;
	}
}
