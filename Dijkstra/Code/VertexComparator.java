import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex arg0, Vertex arg1) {
		return arg0.getVertexName().compareToIgnoreCase(arg1.getVertexName());
	}
}
