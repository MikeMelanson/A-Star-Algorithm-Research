import java.util.Comparator;

public class SortNodes implements Comparator<Node> {

	@Override
	public int compare(Node a, Node b) {
		return a.f - b.f;
	}

}
