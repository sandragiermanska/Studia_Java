package pl.edu.agh.kis.java2015.domain;

import java.util.ArrayList;

public class Heap {

	private int heapSize = 0;
	private ArrayList<Double> tab = new ArrayList<>();

	public void insert(double value) {
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
			return tab.get(currentIndex) > tab.get(parentIndex);
		}
		return false;
	}

	public void swapElements(int currentIndex, int parentIndex) {
		Double parentValue = parentValue(currentIndex);
		Double currentValue = tab.get(currentIndex);
		tab.set(parentIndex, currentValue);
		tab.set(currentIndex, parentValue);
	}

	public Double parentValue(int currentIndex) {
		Double parentValue = tab.get(parentIndex(currentIndex));
		return parentValue;
	}

	public int leftChildIndex(int currentIndex) {
		return currentIndex * 2 + 1;
	}

	public Double leftChildValue(int currentIndex) {
		return tab.get(leftChildIndex(currentIndex));
	}

	public int rightChildIndex(int currentIndex) {
		return currentIndex * 2 + 2;
	}

	public Double rightChildValue(int currentIndex) {
		return tab.get(rightChildIndex(currentIndex));
	}

	public int parentIndex(int currentIndex) {
		return (currentIndex - 1)/2;
	}

	public int size() {
		return heapSize ;
	}

	public double top() {
		return tab.get(0);
	}

	public double extractMax() {
		double result = top();
		if(deleteMax()) {
			return result;
		}
		return -1;
	}
	public void restoreHeap() {
		double changedValue = tab.get(0);
		int currentIndex = 0;
		int leftChildIndex = leftChildIndex(currentIndex);
		int rightChildIndex = rightChildIndex(currentIndex);
		if (isInHeap(leftChildIndex))
		while( isInHeap(leftChildIndex)) {
			if(isChildGreaterThanParent(leftChildIndex, currentIndex)) {
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

	public boolean replace(double newValue) {
		boolean result = false;
		if(heapSize > 0) {
			tab.set(0, newValue);
			restoreHeap();
			result = true;
		}
		return result;
	}

	public void heapify(ArrayList<Double> array) {
		int size = array.size();
		for (int i = 0; i < size; i++) {
			insert(array.get(i));
		}
	}

	public boolean merge(Heap newHeap) {
		return false;
	}

	public boolean meld(Heap newHeap) {
		return false;
	}
}
