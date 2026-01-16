import java.util.Scanner;

public class Heap {

	private Entry [] heap = new Entry[1]; // set value of heap is 3
	private int lastInsertionPos = 0;// to track the last insertion position

	public void insert(int k, String v) {//insert() --> (k = entry, v = value)
		Entry entry = new Entry(k, v);
		int secondPos = 2; 
		if(this.size() >= this.heap.length) {//array (that holds heap) is full
			final int adjuster = 2;
			Entry [] newHeap = new Entry[this.size() + adjuster];
			for(int i = 0; i < this.size(); i++) {
				newHeap[i] = this.heap[i]; 
			}
			this.heap = newHeap;
		}
		int insertPos = this.size(); 
		this.heap[insertPos] = entry; 
		if(this.state().equalsIgnoreCase("Min")) {
			this.percolateUpMinHeap(insertPos);
		}
		if(this.state().equalsIgnoreCase("Max")) {
			this.percolateUpMaxHeap(insertPos);
		}
		this.lastInsertionPos = insertPos;
	}
	
	public Entry top() {//return top node of the heap
		final int top_pos = 0; 
		return this.heap[top_pos]; 
	}
	
	public void toggle() { // turn min-Heap into max-Heap or vice versa
		String state = this.state();
		int minSize = 2;
		if(this.isEmpty()) {
			System.out.println("Heap is empty...");
			return;
			}
		if(this.size() < minSize) {
			System.out.println("Heap is doesn't have enough elements...");
			return;
		}
		if(state.equalsIgnoreCase("Min")) {
			this.toggle_Min_to_Max();
		}
		if(state.equalsIgnoreCase("Max")) {
			this.toggle_Max_to_Min();
		}
	}
	
	public void toggle_Min_to_Max() { // turn min-Heap into max-Heap
	    String state = this.state();
	    boolean isMax = false, isMin = false;
	    boolean swappedOccured = false;
	    if(state.equalsIgnoreCase("Min")) { isMin = true; }
	    if(state.equalsIgnoreCase("Max")) { isMax = true; }
	    
	    final int root = 0;
	    int lastInternalNode = (this.size() / 2) - 1;
	    int parentPos = lastInternalNode;
	    int lastPos = this.lastPosition(); // last position of heap
	    int leftChildPos = 0;
	    int rightChildPos = 0;
	    int biggestChildPos = 0;

	    while(parentPos >= root) { // turn it to maxHeap
	        leftChildPos = (2 * parentPos) + 1;
	        rightChildPos = (2 * parentPos) + 2;

	        if(rightChildPos <= lastPos) { // we have a rightChild
	            if(this.heap[leftChildPos].getKey() > this.heap[rightChildPos].getKey()) {
	                biggestChildPos = leftChildPos;
	            } else {
	                biggestChildPos = rightChildPos;
	            }

	            if(this.heap[biggestChildPos].getKey() > this.heap[parentPos].getKey()) {
	                Entry temp = this.heap[parentPos];
	                this.heap[parentPos] = this.heap[biggestChildPos];
	                this.heap[biggestChildPos] = temp;
	                swappedOccured = true;
	                //this.heapifyDown_MaxHeap(this.heap, biggestChildPos);
	            }

	        } else {
	            biggestChildPos = leftChildPos;
	            if(this.heap[biggestChildPos].getKey() > this.heap[parentPos].getKey()) {
	                Entry temp = this.heap[parentPos];
	                this.heap[parentPos] = this.heap[biggestChildPos];
	                this.heap[biggestChildPos] = temp;
	                swappedOccured = true; 
	                //this.heapifyDown_MaxHeap(this.heap, biggestChildPos);
	            }
	        }
	        if(swappedOccured) {
	        	parentPos = lastInternalNode; 
	        	swappedOccured = false;
	        }
	        else {
	        	parentPos = parentPos - 1;
	        }
	        
	    }
	}
	
	public void toggle_Max_to_Min() { // turn max-Heap into min-Heap
	    String state = this.state();
	    boolean isMax = false, isMin = false;
	    boolean swappedOccured = false;
	    if(state.equalsIgnoreCase("Min")) { isMin = true; }
	    if(state.equalsIgnoreCase("Max")) { isMax = true; }
	    
	    final int root = 0;
	    int lastInternalNode = (this.size() / 2) - 1;
	    int parentPos = lastInternalNode;
	    int lastPos = this.lastPosition(); // last position of heap
	    int leftChildPos = 0;
	    int rightChildPos = 0;
	    int smallestChildPos = 0;

	    while(parentPos >= root) { // turn it to maxHeap
	        leftChildPos = (2 * parentPos) + 1;
	        rightChildPos = (2 * parentPos) + 2;

	        if(rightChildPos <= lastPos) { // we have a rightChild
	            if(this.heap[leftChildPos].getKey() < this.heap[rightChildPos].getKey()) {
	                smallestChildPos = leftChildPos;
	            } else {
	                smallestChildPos = rightChildPos;
	            }

	            if(this.heap[smallestChildPos].getKey() < this.heap[parentPos].getKey()) {
	                Entry temp = this.heap[parentPos];
	                this.heap[parentPos] = this.heap[smallestChildPos];
	                this.heap[smallestChildPos] = temp;
	                swappedOccured = true;
	                //this.heapifyDown_MaxHeap(this.heap, biggestChildPos);
	            }

	        } else {
	            smallestChildPos = leftChildPos;
	            if(this.heap[smallestChildPos].getKey() < this.heap[parentPos].getKey()) {
	                Entry temp = this.heap[parentPos];
	                this.heap[parentPos] = this.heap[smallestChildPos];
	                this.heap[smallestChildPos] = temp;
	                swappedOccured = true; 
	                //this.heapifyDown_MaxHeap(this.heap, biggestChildPos);
	            }
	        }
	        if(swappedOccured) {
	        	parentPos = lastInternalNode; 
	        	swappedOccured = false;
	        }
	        else {
	        	parentPos = parentPos - 1;
	        }
	        
	    }
	}


	public Entry remove(Entry e) { // remove entry "e" (object) from the heap. 
		boolean isMax = false, isMin = false;
		boolean entryFound = false; 
		final int root = 0; 
		if(this.state().equalsIgnoreCase("Min")) {
			isMin = true;
		}
		if(this.state().equalsIgnoreCase("Max")) {
			isMax = true;
		}
		Entry removedEntry = null; 
		for(int i = 0; i <= this.heap.length - 1; i++) {
			if((this.heap[i]!= null )&&(this.heap[i].getKey() == e.getKey())
					&&(this.heap[i].getValue().equalsIgnoreCase(e.getValue()))) {
				if(i == root) {
					this.removeTop();
					entryFound = true;
					removedEntry = this.heap[i];
					break;
				}
				entryFound = true;
				removedEntry = this.heap[i];
				int removedPosition = i; 
				this.heap[removedPosition]= this.heap[this.lastInsertionPos];  
				this.heap[this.lastInsertionPos] = null; 
				break; 
			}
		}
		if(isMin) {
			this.toggle_Max_to_Min();
		}
		if(isMax) {
			this.toggle_Min_to_Max();
		}
		if(!entryFound) {
			System.out.println("\n\nThe entry entered is not in the heap...");
			removedEntry = null;
		}
		return removedEntry; 
	}
	
	public void removeTop() {//remove top node of heap and fix heap afterwards. 
		if(this.isEmpty()) {return;}//if heap is empty exit
		boolean isMax = false, isMin = false; 
		if(this.state().equalsIgnoreCase("Min")) {
			isMin = true;
		}
		if(this.state().equalsIgnoreCase("Max")) {
			isMax = true;
		}
		final int root = 0; 
		this.heap[root] = this.heap[this.lastInsertionPos]; 
		this.heap[this.lastInsertionPos] = null; 
		this.lastInsertionPos--;
		if(this.isEmpty()) {return;}//if heap is empty exit

		if(isMin) {
			this.toggle_Max_to_Min();
		}
		if(isMax) {
			this.toggle_Min_to_Max();
		}
	}
	
	public int lastNonEmptyNodePosition() {//checking which one is the last Non-Empty Node of the Heap
		int lastNonEmptyNodePosition = 0;
		for(int i = 0; i <= lastNonEmptyNodePosition; i++) {
			if(this.heap[i] == null) {
				lastNonEmptyNodePosition = i; 
				break;
			}
		}
		return lastNonEmptyNodePosition;
	}

	public int size() {//checking the size of the heap
		int size = 0; 
		for(int i = 0; i <= this.heap.length - 1; i++) {
			if(this.heap[i] != null) {
				size++;
			}
		}
		return size;
	}

	public boolean isEmpty() {//checking if heap is Empty
		boolean isEmpty = false; 
		final int emptyNumber = 0; 
		int size = size();
		if(size == emptyNumber) {
			isEmpty = true;
		}
		return isEmpty;
	}

	public String state() {//checking state of heap (Min or Max)
		final int minSize = 2; 
		String state = "";
		final int pos1 = 0, pos2 = 1; 
		if(this.size() < minSize) {return "None";}
		else {
			if(this.heap[pos1].getKey() > this.heap[pos2].getKey()) {
				state = "Max";
			}
			else if(this.heap[pos1].getKey() < this.heap[pos2].getKey()) {
				state = "Min";
			}
			if(this.heap[pos1].getKey() == this.heap[pos2].getKey()) {
				for(int i = pos2 + 1; i <= this.lastPosition(); i++) {
					if(this.heap[pos2].getKey() > this.heap[i].getKey()) {
						state = "Max"; 
						break;
					}
					if(this.heap[pos2].getKey() < this.heap[i].getKey()) {
						state = "Min"; 
						break;
					}
				}
			}
		}
		return state;
		
	}

	public int numbEmptyNodes() {//checking the number of empty slots in the heap (array)
		int numbEmptyNodes = 0; 
		int size = size() - 1; 
		for(int i = 0; i <= size; i++) {
			if(this.heap[i] == null) {
				numbEmptyNodes++;
			}
		}
		return numbEmptyNodes;
	}

	public void percolateUpMinHeap(int insertedPos) {//insertion of key at last position fix up on min-heap
		int start = 0;
		int i = insertedPos;
		while(i > 0) {
			int parent = (i-1)/2;
			System.out.println(i);
			if(this.heap[parent].getKey() > this.heap[i].getKey()) {
				Entry temp = this.heap[parent]; 
				this.heap[parent] = this.heap[i];
				this.heap[i] = temp;
				i = parent;
			}
			else {break;} // "i" is greater than its parent so we can break the loop
		};
	}

	public void percolateUpMaxHeap(int insertedPos) {//insertion of key at last position fix up on max-heap
		int start = 0;
		int i = insertedPos;
		while(i > 0) {
			int parent = (i-1)/2;
			if(this.heap[parent].getKey() < this.heap[i].getKey()) {
				Entry temp = this.heap[parent]; 
				this.heap[parent] = this.heap[i];
				this.heap[i] = temp;
				i = parent;
			}
			else {break;} // "i" is greater than its parent so we can break the loop
		};
	}

	public void heapifyDown_MinHeap(Entry [] heap, int parentPos) {// Re-adjusts heap after removal of root-key --> for minHeap
		int heapSize = -1;
		for(int i = 0; i<= heap.length - 1; i++) {
			if(heap[i] != null) {heapSize++;}
		}
		int leftChildPos = 2*parentPos + 1, rightChildPos = 2*parentPos + 2; 
		int smallestChildPos = 0; 
		boolean inbounds = true, rightOutbound = false;

		while(parentPos <= heapSize) {
			if(rightChildPos <= heapSize) {
				inbounds = true; 
				rightOutbound = false;
			}
			else {
				rightOutbound = true;
				if(leftChildPos <= heapSize) {inbounds = true;}
				else {inbounds = false;}
			}
			if(inbounds == false) {return;}

			if(rightOutbound == false) {
				if((heap[leftChildPos] != null)&&(heap[rightChildPos] != null)) {
					if(heap[leftChildPos].getKey() > heap[rightChildPos].getKey()) {//finding smallest child position
						smallestChildPos = rightChildPos;
					}
					else {
						smallestChildPos = leftChildPos;
					}
				}
			}
			else {
				smallestChildPos = leftChildPos;
			}

			if(heap[smallestChildPos].getKey() < heap[parentPos].getKey()) {
				Entry temp = heap[parentPos]; 
				heap[parentPos] = heap[smallestChildPos];
				heap[smallestChildPos] = temp;
				// re-adjusting the positions
				parentPos = smallestChildPos;
				leftChildPos = 2*parentPos + 1; 
				rightChildPos = 2*parentPos + 2; 
			}
			else {return;}
		}
	}
	
	public void heapifyDown_MaxHeap(Entry [] heap, int parentPos) {// Re-adjusts heap after removal of root-key --> for maxHeap
		int parentKey = 0, leftChildKey = 0, rightChildKey = 0; 
		int leftChildPos = 0, rightChildPos = 0;
		int lastPos = this.lastNonEmptyNodePosition();
		int biggestPos = 0, biggestKey = 0; 
		leftChildPos = (2*parentPos) + 1; 
		rightChildPos = (2*parentPos) + 2;
		while(leftChildPos <= lastPos) {
			if(rightChildPos <= lastPos) {//we have a right-child
				parentKey = this.heap[parentPos].getKey();
				leftChildKey = this.heap[leftChildPos].getKey();
				rightChildKey = this.heap[rightChildPos].getKey();
				if(leftChildKey > rightChildKey) {
					biggestPos = leftChildPos; 
					biggestKey = leftChildKey;
				}
				else {
					biggestPos = rightChildPos; 
					biggestKey = rightChildKey;
				}
				if(parentKey < biggestKey) {
					Entry temp = this.heap[parentPos]; 
					this.heap[parentPos] = this.heap[biggestPos]; 
					this.heap[biggestPos] = temp;
					parentPos = biggestPos;
				}
					
			}
			else {// we don't have a right-child
				parentKey = this.heap[parentPos].getKey();
				leftChildKey = this.heap[leftChildPos].getKey();
				if(leftChildKey > parentKey) {
					Entry temp = this.heap[parentPos]; 
					this.heap[parentPos] = this.heap[leftChildPos]; 
					this.heap[leftChildPos] = temp; 
					parentPos = leftChildPos;
				}
			}
			leftChildPos = (2*parentPos) + 1; 
			rightChildPos = (2*parentPos) + 2;
		}
		
	}
	
	public int replace(int e, int k) {//find key/position e and replace it with key/position k
		boolean isMax = false, isMin = false;
		boolean keyFound = false; 
		int removedKey = -1; 
		if(this.state().equalsIgnoreCase("Min")) {
			isMin = true;
		}
		if(this.state().equalsIgnoreCase("Max")) {
			isMax = true;
		}
		for(int i = 0; i <= this.heap.length - 1; i++) {
			if((this.heap[i]!= null )&&(this.heap[i].getKey() == e)) {
				keyFound = true;
				removedKey = this.heap[i].getKey();
				int keyPosition = i; 
				this.heap[keyPosition].setKey(k);
				break; 
			}
		}
		if(isMin) {
			this.toggle_Max_to_Min();
		}
		if(isMax) {
			this.toggle_Min_to_Max();
		}
		if(!keyFound) {
			System.out.println("\n\nThe entry entered is not in the heap...");
			removedKey = -1;
		}
		return removedKey; 
	}
	
	public String replace(String e, String v) {//find position/entry of String "e", replace it with String "v", and return "e"
		String replacedValue = null;
		for(int i = 0; i <= this.lastInsertionPos; i++) {
			if((this.heap[i] != null)&&(this.heap[i].getValue().equalsIgnoreCase(e))) {
				this.heap[i].setValue(v);
				replacedValue = e;
				break;
			}
		}
		return replacedValue;
	}
	
	// in the case that heap property is violated going upwards Ex: after replace() watch below 
	// **** check out heapifyUp_MinHeap() and heapifyUp_MaxHeap() 
	public void heapifyUp_MinHeap(int childPos) {// Re-adjusts heap upwards after child-Modification  --> minHeap
		final int rootPos = 0; 
		int parentPos = 0; 
		while (childPos > rootPos) {
			parentPos = (childPos - 1)/2;
			if(this.heap[childPos].getKey() < this.heap[parentPos].getKey()) {
				Entry temp = this.heap[childPos]; 
				this.heap[childPos] = this.heap[parentPos]; 
				this.heap[parentPos] = temp; 
				childPos = parentPos; 
			}
			else {break;}
		};	
	}
	public void heapifyUp_MaxHeap(int childPos) {// Re-adjusts heap upwards after child-Modification --> maxHeap
		final int rootPos = 0; 
		int parentPos = 0; 
		while (childPos > rootPos) {
			parentPos = (childPos - 1)/2;
			if(this.heap[childPos].getKey() > this.heap[parentPos].getKey()) {
				Entry temp = this.heap[childPos]; 
				this.heap[childPos] = this.heap[parentPos]; 
				this.heap[parentPos] = temp; 
				childPos = parentPos; 
			}
			else {break;}
		};	
	}
	
	public void merge(Heap heap1) {//heap1 is being merged to the main heap. 
		int oldHeapSize = this.size();  
		int heap1Size = heap1.size();
		int newHeapSize = oldHeapSize + heap1Size;
		String heapState = this.state();
		Entry [] newHeapArray = new Entry [newHeapSize];
		
		for(int i = 0; i < oldHeapSize; i++) {
			newHeapArray[i] = this.heap[i]; 
		}
		this.heap = newHeapArray;
		
		for(int v1 = this.size(), v2 = 0; v2 < heap1Size; v1++, v2++) {
			this.heap[v1] = heap1.heap[v2];
			if(heapState.equalsIgnoreCase("Min")) {
				this.heapifyUp_MinHeap(v1);
			}
			if(heapState.equalsIgnoreCase("Max")) {
				this.heapifyUp_MaxHeap(v1);
			}
		}
	}
	
	public void showHeap() {
		int lastPos = this.size() - 1;
		System.out.println("\nHere is the content of your heap (below): \n");
		System.out.print("[ ");
		for(int i = 0; i < this.size(); i++) {
			System.out.print("( Key: " + this.heap[i].getKey() + ", Value: " + this.heap[i].getValue() + " )");
			if(i < this.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.print(" ]\n\n");
	}
	
	public boolean entryKeyFound(int entryKey) {//find key of an Entry within Heap
		int n = this.lastNonEmptyNodePosition();
		boolean entryFound = false; 
		for(int i = 0; i <= n; i++) {
			if(this.heap[i].getKey() == entryKey) {
				entryFound = true;
			}
		}
		return entryFound; 
	}
	
	public boolean entryValueFound(String entryValue) {//find value of an Entry within Heap
		int n = this.lastInsertionPos;
		boolean entryFound = false; 
		for(int i = 0; i <= n; i++) {
			if(this.heap[i].getValue().equalsIgnoreCase(entryValue)) {
				entryFound = true;
			}
		}
		return entryFound; 
	}
	
	public int nextNullPosition() {//finding out the next-null position for fill up. 
		int nextNullPos = 0; 
		for(int i = 0; i < this.size(); i++) {
			if(this.heap[i] == null) {
				nextNullPos = i; 
				break;
			}
		}
		return nextNullPos;
	}
	
	public int lastPosition() {//getting last position of heap. 
		int lastPos = this.size() - 1; 
		return lastPos;
	}


}
