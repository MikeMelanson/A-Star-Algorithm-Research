import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Choose a data structure\n\t1. MinHeap\n\t2. Limited MinHeap with sorted list\n\t3. Array Tree"
				+ "\n\t4. Limited MinHeap with buckets\n\t5. Limited MinHeap with one bucket\n\t6. Array Tree with discard"
				+ "\n\t7. GridMinHeap \nSelection: ");
		int datastructure = Integer.parseInt(ScannerClass.in.nextLine());

		Map newMap = new Map();
		
		Astar astar = new Astar(newMap,datastructure);
        
		List<Node> path = astar.run();
		
		for (int i=0;i<path.size();i++) {
			System.out.println(path.get(i).getName());
		}
		
	}
}
