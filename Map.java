import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//this class creates a map of nodes and edges

public class Map {
	
	private ArrayList<Node> nodeList = new ArrayList<Node>();	//a list of all the nodes in the map
	private ArrayList<Edge> edgeList = new ArrayList<Edge>();	//a list of all the edges in the map
	private int size = 0;
	private String file;
	
	//Map constructor
	public Map() {
		System.out.println("Enter data file name: ");
		file=null; 
		file = ScannerClass.in.nextLine();
		
		String line = null;
		boolean nodeFlag = true;
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while ((line = bufferedReader.readLine()) != null) {
				if (line.equals("")) {
					nodeFlag = false;
				}
				else {
					StringTokenizer tokenizer = new StringTokenizer(line); //tokenize the input
					if (nodeFlag) {
						Node newNode = new Node(tokenizer.nextToken(),Integer.parseInt(tokenizer.nextToken()),tokenizer.nextToken(),tokenizer.nextToken()); //create a new node from the tokenized info
						nodeList.add(newNode); //add the new node to the list
						size++;
					}
					else {
						String node1Name = tokenizer.nextToken(); //store the name of the first end node
						String node2Name = tokenizer.nextToken(); //store the name of the second end node
						Node node1=null,node2=null; //create two nodes
						
						//find the names of the endnodes in the node list and get the nodes
						for (int i = 0; i< nodeList.size();i++) {
							if (nodeList.get(i).getName().equals(node1Name)) {
								node1 = nodeList.get(i);
							}
							if (nodeList.get(i).getName().equals(node2Name)) {
								node2 = nodeList.get(i);
							}
						}
						Edge newEdge = new Edge(node1,node2,Integer.parseInt(tokenizer.nextToken())); //create a new edge from the information
						edgeList.add(newEdge); //add the new edge to the list
					}
				}
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Cannot find file");
			System.exit(0);
		}
		catch(IOException ex) {
			System.out.println("Error reading file");
			System.exit(0);
		}
	}
	
	//retrieve the node list
	public ArrayList<Node> getNodeList(){
		return nodeList;
	}
	
	//retrieve the edge list
	public ArrayList<Edge> getEdgeList(){
		return edgeList;
	}
	
	//retrieve the number of nodes in the map
	public int getSize() {
		return size;
	}
	
	public String getFileName() {
		return file;
	}
}
