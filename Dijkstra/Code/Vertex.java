public class Vertex implements Comparable<Vertex> {

	private double weight;
	private String vertexName;
	private Vertex parent;
	private double minDistFromSource;

	Vertex(String vertexName) {
		this.vertexName = vertexName;
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	public double getMinDistFromSource() {
		return minDistFromSource;
	}
	
	public void setMinDistFromSource(double minDistFromSource) {
		this.minDistFromSource = minDistFromSource;
	}

	Vertex(double weight, String vertexName) {
		this.weight = weight;
		this.vertexName = vertexName;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getVertexName() {
		return vertexName;
	}

	public void setVertexName(String vertexName) {
		this.vertexName = vertexName;
	}

	
	Vertex(double weight, double minDistanceFromWeight, Vertex parent, String vertexName) {
		this.weight = weight;
		this.vertexName = vertexName;
		this.parent = parent;
		this.minDistFromSource = minDistanceFromWeight;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(minDistFromSource);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result
				+ ((vertexName == null) ? 0 : vertexName.hashCode());
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (Double.doubleToLongBits(minDistFromSource) != Double
				.doubleToLongBits(other.minDistFromSource))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (vertexName == null) {
			if (other.vertexName != null)
				return false;
		} else if (!vertexName.equals(other.vertexName))
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [weight=" + weight + ", vertexName=" + vertexName
				+ ", parent=" + parent + ", minDistFromSource="
				+ minDistFromSource + "]";
	}

	@Override
	public int compareTo(Vertex arg0) {
		if (this.getMinDistFromSource() <= arg0.getMinDistFromSource()) 
			return 1;
		else return -1;
	}
	
	public boolean lessThan(Vertex arg0) {
		if (this.getMinDistFromSource() <= arg0.getMinDistFromSource()) 
			return true;
		else return false;
	}
}
