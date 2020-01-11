import java.io.*;
import java.util.ArrayList;
import java.util.List;

//this class finds the fastest route through a provided map

public class Astar{
	
	private class Pair{
		Node node;
		int cost;
		
		Pair(Node node, int cost){
			this.node = node;
			this.cost = cost;
		}
	}

	private Map map;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private int numNodes,removeMinNum=0,insertNum=0,datastructure;
	private Node startNode=null,endNode=null;
	
	public Astar(Map map,int datastructure) {
		this.map = map;
		this.datastructure = datastructure;
	}
	
	public void setup() {
		nodes.addAll(map.getNodeList());
		edges.addAll(map.getEdgeList());
		numNodes = nodes.size();
		for (int i=0;i<numNodes;i++) {
			if (nodes.get(i).isFirst()) {
				startNode = nodes.get(i);
			}
			if (nodes.get(i).isGoal()) {
				endNode = nodes.get(i);
			}
		}
		
	}
	
	public List<Node> findPath() {
		DataStructure openList = null;
		switch(datastructure) {
			case 1: openList = new MinHeap(map.getSize());
				break;
			case 2: openList = new LimitedMinHeapWithSortedList();
				break;
			case 3: openList = new ArrayTreeSmallAtTop(map.getSize());
				break;
			case 4: openList = new LimitedMinHeapWithBuckets();
				break;
			case 5: openList = new LimitedMinHeapWithBucket();
				break;
			case 6: openList = new ArrayTreeSmallAtTop_Discard(map.getSize());
				break;
			case 7: openList = new GridMinHeap(map.getSize());
				break;
		}
		//DataStructure openList = new MinHeap(map.getSize());
		//DataStructure openList = new LimitedMinHeapWithSortedList();
		//List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		
		startNode.g = 0;
		startNode.h = startNode.getEstToFinish();
		startNode.f = startNode.h;
		
		openList.insert(startNode);
		insertNum++;
		
		while (true) {
			Node current = null;
			
			if (openList.isEmpty()) {
				throw new RuntimeException("no route");
			}
			
			current = openList.removeMin();
			//System.out.println(current.getName());
			removeMinNum++;
			
			if (current.equals(endNode)) {
				break;
			}
			
			closedList.add(current);
			
			List<Pair> neighbours = findNeighbours(current.getName());
			
			for (Pair neighbour : neighbours) {
				if (neighbour == null) {
					continue;
				}
				
				int nextG = current.g + neighbour.cost;
				
				/*
				if (nextG < neighbour.node.g) {
					openList.remove(neighbour);
					closedList.remove(neighbour);
				}
				*/
				
				if (!closedList.contains(neighbour.node)) {
					neighbour.node.g = nextG;
					neighbour.node.h = neighbour.node.getEstToFinish();
					neighbour.node.f = neighbour.node.g + neighbour.node.h;
					neighbour.node.parent = current;
					openList.insert(neighbour.node);
					insertNum++;
				}
			}
		}
		
		List<Node> nodes = new ArrayList<Node>();
		Node current = endNode;
		while (current.parent != null) {
			nodes.add(current);
			current = current.parent;
		}
		nodes.add(startNode);

		return nodes;
	}
	
	public List<Pair> findNeighbours(String name){
		List<Pair> neighbours = new ArrayList<Pair>();
		for (int i=0;i<edges.size();i++) {
			if (edges.get(i).getEnd1().getName().equals(name)) {
				neighbours.add(new Pair(edges.get(i).getEnd2(),edges.get(i).getCost()));
			}
			else if (edges.get(i).getEnd2().getName().equals(name)) {
				neighbours.add(new Pair(edges.get(i).getEnd1(),edges.get(i).getCost()));
			}
		}
		return neighbours;
	}
	
	public List<Node> run() {
		long start = System.nanoTime();
		setup();
		List<Node> path = findPath();
		long end = System.nanoTime();
		
		String fileName = map.getFileName();
		FileWriter fileWriter=null;
		try {
			switch(datastructure) {
				case 1:fileWriter = new FileWriter(fileName.replace(".txt","") + "_MinHeap.txt"); break;
				case 2:fileWriter = new FileWriter(fileName.replace(".txt","") + "_LimitedMinHeapWithSortedList.txt"); break;
				case 3:fileWriter = new FileWriter(fileName.replace(".txt","") + "_ArrayTreeSmallAtTop.txt"); break;
				case 4:fileWriter = new FileWriter(fileName.replace(".txt","") + "_LimitedMinHeapWithBuckets.txt"); break;
				case 5:fileWriter = new FileWriter(fileName.replace(".txt","") + "_LimitedMinHeapWithBucket.txt"); break;
				case 6:fileWriter = new FileWriter(fileName.replace(".txt","") + "_ArrayTreeSmallAtTop_Discard.txt"); break;
				case 7:fileWriter = new FileWriter(fileName.replace(".txt","") + "_GridMinHeap.txt");break;
			}
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("Time in milliseconds to find path: " + ((float)(end -start)/1000000));
			bufferedWriter.write("\nNumber of removeMin operations: " + removeMinNum);
			bufferedWriter.write("\nNumber of insert operations: " + insertNum);
			bufferedWriter.close();
		}
		catch(Exception e) {e.printStackTrace();}

		return path;
	}
}