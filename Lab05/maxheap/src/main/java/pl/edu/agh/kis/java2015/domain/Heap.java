package pl.edu.agh.kis.java2015.domain;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T extends Comparable> implements Comparator<T>{

	private int heapSize = 0;
	private ArrayList<T> tab = new ArrayList<>();
	private boolean isMaxFirst;

	public Heap(boolean isMaxFirst_) {
		isMaxFirst = isMaxFirst_;
	}

	public void insert(T value) {
		int currentIndex = heapSize;
		int parentIndex = parentIndex(currentIndex);
		tab.add(value);
		while( isChildGreaterThanParent(currentIndex, parentIndex) ) {
			swapElements(currentIndex, parentIndex);
			currentIndex = parentIndex;
			parentIndex = parentIndex(currentIndex);
		}
		heapSize++;
	}

	public boolean isInHeap(int index) {
		if(index < tab.size()) {
			return true;
		}
		return false;
	}

	public boolean isChildGreaterThanParent(int currentIndex, int parentIndex) {
		if(isInHeap(currentIndex) && isInHeap(parentIndex)) {
			return compare(tab.get(currentIndex), tab.get(parentIndex)) > 0;
		}
		return false;
	}

	public void swapElements(int currentIndex, int parentIndex) {
		T parentValue = parentValue(currentIndex);
		T currentValue = tab.get(currentIndex);
		tab.set(parentIndex, currentValue);
		tab.set(currentIndex, parentValue);
	}

	public T parentValue(int currentIndex) {
		T parentValue = tab.get(parentIndex(currentIndex));
		return parentValue;
	}

	public int leftChildIndex(int currentIndex) {
		return currentIndex * 2 + 1;
	}

	public T leftChildValue(int currentIndex) {
		return tab.get(leftChildIndex(currentIndex));
	}

	public int rightChildIndex(int currentIndex) {
		return currentIndex * 2 + 2;
	}

	public T rightChildValue(int currentIndex) {
		return tab.get(rightChildIndex(currentIndex));
	}

	public int parentIndex(int currentIndex) {
		return (currentIndex - 1)/2;
	}

	public int size() {
		return heapSize ;
	}

	public T top() {
		return tab.get(0);
	}

	public T extractMax() {
		T result = top();
		if(deleteMax()) {
			return result;
		}
		return null;
	}

	public void restoreHeap() {
		if (heapSize > 0) {
			T changedValue = tab.get(0);
			int currentIndex = 0;
			int leftChildIndex = leftChildIndex(currentIndex);
			int rightChildIndex = rightChildIndex(currentIndex);
			if (isInHeap(leftChildIndex))
				while (isInHeap(leftChildIndex)) {
					if (isChildGreaterThanParent(leftChildIndex, currentIndex)) {
						swapElements(leftChildIndex, currentIndex);
						currentIndex = leftChildIndex;
					} else if (isInHeap(rightChildIndex) && isChildGreaterThanParent(rightChildIndex,
							currentIndex)) {
						swapElements(rightChildIndex, currentIndex);
						currentIndex = rightChildIndex;
					} else {
						break;
					}
					leftChildIndex = leftChildIndex(currentIndex);
					rightChildIndex = rightChildIndex(currentIndex);
				}
		}
	}

	public boolean deleteMax() {
		boolean result = false;
		if (heapSize > 0) {
			tab.set(0, tab.get(tab.size() - 1));
			tab.remove(tab.size() - 1);
			heapSize--;
			restoreHeap();
			result = true;
		}
		return result;
	}

	public boolean replace(T newValue) {
		boolean result = false;
		if(heapSize > 0) {
			tab.set(0, newValue);
			restoreHeap();
			result = true;
		}
		return result;
	}

	public void heapify(ArrayList<T> array) {
		int size = array.size();
		for (int i = 0; i < size; i++) {
			insert(array.get(i));
		}
	}

	public void meld(Heap newHeap) {
		T current;
		while (newHeap.size() > 0) {
			current = (T) newHeap.extractMax();
			insert(current);
		}
	}

	@Override
	public int compare(T o1, T o2) {
		if (isMaxFirst) {
			return o1.compareTo(o2);
		}
		return -o1.compareTo(o2);
	}
}
