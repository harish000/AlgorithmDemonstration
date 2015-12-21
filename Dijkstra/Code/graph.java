import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class graph {

	public static void main(String[] args) throws IOException {
		HashMap<String, LinkedList<Vertex>> graphListRep;

		graph g = new graph();

		
		graphListRep = g.buildGraph(args[0]);
		System.out.println("\n\n\nGraph built using the input file provided..\n\n ");
	
		
		String str = null;
		
		do {
			System.out.println("\n\nPlease select..");
			System.out.println("1. for finding shortest path.. ");
			System.out.println("2. for printing graph.. ");
			System.out.println("Enter exit to quit the program.. ");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			str = br.readLine();
			switch (str) {
			case "1":

System.out.println("\nPlease enter vertices name in same case as in the input file. Space between source and destination ");
				String[] path = br.readLine().split(" ");

				Dijkstra.findShortestDistance(path[0], path[1],
						g.getVertexSet(), graphListRep);
				break;

			case "2":
				
				System.out.println("\n\nPrinted graph in output.txt...\n\n\n");
				g.printGraph(g.getVertexSet(), graphListRep);
				break;
			}
		} while (!str.equalsIgnoreCase("exit"));

	}

	public graph() {
		vertexSet = new HashSet<Vertex>();
		adjList = new HashMap<String, LinkedList<Vertex>>();
	}

	private void printGraph(HashSet<Vertex> v,
			HashMap<String, LinkedList<Vertex>> conVertices)
			throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter pw = new PrintWriter("output.txt", "UTF-8");

		VertexComparator vc = new VertexComparator();

		ArrayList<Vertex> sortedVetrexList = new ArrayList<Vertex>(v);
		Collections.sort(sortedVetrexList, vc);


		for (int i = 0; i < sortedVetrexList.size(); i++) {
			String vertex = sortedVetrexList.get(i).getVertexName();
			pw.println(vertex);

			LinkedList<Vertex> connectedVertices = conVertices.get(vertex);
			Collections.sort(connectedVertices, vc);
			for (int j = 0; j < connectedVertices.size(); j++) {
				pw.println("   " + connectedVertices.get(j).getVertexName()
						+ "  " + connectedVertices.get(j).getWeight());
			}
		}
		pw.close();
	}

	private HashSet<Vertex> getVertexSet() {
		return vertexSet;
	}


	private HashMap<String, LinkedList<Vertex>> buildGraph(String fileName)
			throws IOException {
		File inputFile = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		String str = br.readLine();
		while (str != null) {
			
			String[] vertexData = str.split(" ");
			String v1 = vertexData[0];
			String v2 = vertexData[1];
			double weight = Double.parseDouble(vertexData[2]);
			addToGraph(v1, v2, weight);
			str = br.readLine();
		}
		br.close();
		return adjList;
	}

	private void addToGraph(String v1, String v2, double weight) {
		vertexSet.add(new Vertex(v1));
		vertexSet.add(new Vertex(v2));

		LinkedList<Vertex> vertexList;
		vertexList = getAdjListForVertex(v1, v2, weight, adjList.get(v1));
		adjList.put(v1, vertexList);

		vertexList = getAdjListForVertex(v2, v1, weight, adjList.get(v2));
		adjList.put(v2, vertexList);
	}


	private LinkedList<Vertex> getAdjListForVertex(String v1, String v2,
			double weight, LinkedList<Vertex> list) {

		if (list == null) {
			list = new LinkedList<Vertex>();
		}
		Vertex n = new Vertex(weight, v2);
		list.addLast(n);
		return list;
	}

	private HashMap<String, LinkedList<Vertex>> adjList;
	private HashSet<Vertex> vertexSet;
}
