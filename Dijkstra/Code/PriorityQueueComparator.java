import java.util.Comparator;

public class PriorityQueueComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex arg0, Vertex arg1) {
		if (arg0.getMinDistFromSource() <= arg1.getMinDistFromSource())
			return -1;
		else
			return 1;
	}

}
