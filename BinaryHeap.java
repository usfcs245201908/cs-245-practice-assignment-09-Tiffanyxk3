public class BinaryHeap
{
	int[] heap, growHeap;
	int size, position;
	public BinaryHeap() {
		heap = new int[10];
		size = 0;
	}

	public void add (int elem) {
		if (size == heap.length) {
			grow();
		}
		heap[size] = elem;
		// add elem to the end of heap
		reorderUpwards(size);
		size++;
	}

	public int remove() throws Exception {
		if (empty()) {

			throw new Exception();
		}
		int temp = heap[0];
		heap[0] = heap[size-1];
		// move the last element to the first
		size--;
		reorderDownwards(0);
		return temp;
}

	public int parent(int index) {
		return (index-1)/2;
	}

	public int leftChild(int index) {
		return 2*index+1;
	}

	public int rightChild(int index) {
		return (2*index)+2;
	}

	public boolean empty() {
		return size==0;
	}

	public boolean leaf(int index) {
		if ((2*index)+1 >= size) {
			return true;
		}
		return false;
	}

	public void reorderUpwards(int i) {
		if (heap[i]>heap[parent(i)] || i==0) {
		// if it's greater than its parent so no need to shift, or its the root of heap
			return;
		}
		swap(i, parent(i));
		reorderUpwards((i-1)/2);
	}

	public void reorderDownwards(int rt) {
		if (leaf(rt)) {
			return;
		}
		// if it's is not a leaf
		int smallest;
		// smallest points to the smallest children
		if (rightChild(rt) >= size) {
			// if there's only left child
			smallest = leftChild(rt);
		}
		else {
			if (heap[leftChild(rt)] <= heap[rightChild(rt)]) {
			// if left child is smaller than right child
				smallest = leftChild(rt);
			}
			else {
			// if right child is smaller than left child
				smallest = rightChild(rt);
			}
		}

		if (heap[smallest] >= heap[rt]) {
			return;
		}
		// if the heap[rt] is greater than the smallest child
		swap(rt, smallest);
		reorderDownwards(smallest);
	}

	public void swap(int i, int j) {
		int t = heap[i];
		heap[i] = heap[j];
		heap[j] = t;
	}

	public void grow() {
		growHeap = new int[heap.length*2];
		for (int i=0; i<heap.length; i++) {
			growHeap[i] = heap[i];
		}
		heap = growHeap;
	}

	public void print() {
		for (int i=0; i<heap.length; i++) {
			System.out.print(heap[i] + " ");
		}
		System.out.print("\n\n");
	}
}