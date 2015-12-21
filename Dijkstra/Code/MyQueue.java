import java.util.HashSet;
import java.util.Iterator;

public class MyQueue<T extends Vertex> {

	public MyQueue(int size) {
		A = new Vertex[size];
	}

	public void addAll(HashSet<Vertex> vSet) {
		Iterator<Vertex> iterator = vSet.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Vertex n = iterator.next();
			A[i] = n;
			i++;
		}
		buildMinHeap();
	}

	
	private void buildMinHeap() {
		heapsize = A.length;
		for (int i = (A.length-1) / 2 - 1; i >= 0; i--) {
			minHeapify(i);
		}
	}


	private void minHeapify(int i) {
		int l = getLeft(i);
		int r = getRight(i);

		int smallest = -1;
		if (l < heapsize && A[l].lessThan(A[i]))
			smallest = l;
		else
			smallest = i;
		if (r < heapsize && A[r].lessThan(A[smallest]))
			smallest = r;
		if (smallest != i) {
			Vertex temp = A[i];
			A[i] = A[smallest];
			A[smallest] = temp;
			minHeapify(smallest);
		}

	}

	public Vertex getHeapMin() {
		return A[0];
	}

	
	public Vertex getPriorityQueueHead() {
		return getHeapMin();
	}

	
	public Vertex removePriorityQueueHead() {
		
		if (heapsize-1 < 0) {
			System.err.println("Heap Undeflow");
			return null;
		}
		
		Vertex minimum = A[0];
		A[0] = A[heapsize-1];
		heapsize--;
		minHeapify(0);
		return minimum;
	}

	 
    public void decreaseKey(int i, double key) {
        if (key > A[i].getMinDistFromSource()) {
            System.err.println("New key is already larger, no change to make");
            return;
        }
        A[i].setMinDistFromSource(key);
        
        int parent = getParent(i);
        while (i > 0 && !A[parent].lessThan(A[i])) {
            Vertex temp = A[i];
            A[i] = A[parent];
            A[parent] = temp;
            
            i = getParent(i);
            parent = getParent(i);
        }
    }
    
	public void decreaseKey(Vertex v) {
		for (int i = 0; i < heapsize; i++) {
			if (v.equals(A[i])) {
				decreaseKey(i, v.getMinDistFromSource());
				break;
			}
		}
	}

	public boolean isEmpty() {
		return (heapsize == 0);
	}

	
	public int getLeft(int i) {
		return 2 * i + 1;
	}

	
	public int getRight(int i) {
		return 2 * i + 2;
	}


	public int getParent(int i) {
		return i / 2;
	}

	Vertex[] A;
	int heapsize;
}
