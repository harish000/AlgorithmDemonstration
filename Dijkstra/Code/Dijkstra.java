import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Dijkstra {

	static void findShortestDistance(String v1, String v2, HashSet<Vertex> vSet,
			HashMap<String, LinkedList<Vertex>> adjList) {

		Vertex source = null;
		Vertex destination = null;

		
		Iterator<Vertex> iterator = vSet.iterator();
		while (iterator.hasNext()) {
			Vertex n = iterator.next();
			n.setParent(null);
			n.setMinDistFromSource(Double.POSITIVE_INFINITY);

			
			if (n.getVertexName().equalsIgnoreCase(v1)) {
				source = n;
			} else if (n.getVertexName().equalsIgnoreCase(v2)) {
				destination = n;
			}
		}

		
		source.setMinDistFromSource(0);

		
		int size = vSet.size();
		MyQueue<Vertex> pq = new MyQueue<Vertex>(size);
		pq.addAll(vSet);

		while (!pq.isEmpty()) {
			Vertex u = pq.removePriorityQueueHead();
			LinkedList<Vertex> list = adjList.get(u.getVertexName());
			Vertex v = null;
			for (int i = 0; i < list.size(); i++) {
				v = getNodeFromSet(list.get(i), vSet);
				if (v.getMinDistFromSource() > u.getMinDistFromSource()
						+ list.get(i).getWeight()) {
					v.setMinDistFromSource(u.getMinDistFromSource()
							+ list.get(i).getWeight());
					v.setParent(u);
					pq.decreaseKey(v);
				}
			}
		}

		printShortestPath(source, destination);
		printShortestDistance(source, destination);

	}
	private static void printShortestDistance(Vertex source, Vertex destination) {
		System.out.print(destination.getMinDistFromSource() + " ");
	}

	private static void printShortestPath(Vertex source, Vertex destination) {
		if (source == null || destination == null)
			return;
		printShortestPath(source, destination.getParent());
		System.out.print(destination.getVertexName() + " ");
	}


	private static Vertex getNodeFromSet(Vertex node, HashSet<Vertex> vSet) {
		Iterator<Vertex> iterator = vSet.iterator();
		while (iterator.hasNext()) {
			Vertex n = iterator.next();
			if (node.getVertexName().equals(n.getVertexName())) {
				return n;
			}
		}
		return null;
	}

	
}
