package pl.edu.agh.kis.java2015.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class HeapTest {
	
	@Test
	public void insert0intoNewHeap_topIs0() {
		
		Heap<Double> heap = new Heap(true);
		heap.insert((double)0);
		
		assertEquals("size should be 1",1,heap.size());
		assertEquals(0,heap.top(),0.001);
		assertEquals(1,heap.size());
	}
	
	@Test
	public void insert0AndThen2intoNewHeap_topIs2() {
		
		Heap<Double> heap = new Heap(true);
		heap.insert((double)0);
		heap.insert((double)2);
		
		assertEquals("size should be 2",2,heap.size());
		assertEquals(2,heap.top(),0.001);
	}
	
	@Test
	public void insert0And2And3And5And6intoNewHeap_topIs6() {

		Heap<Double> heap = new Heap(true);
		heap.insert((double)0);
		heap.insert((double)2);
		heap.insert((double)3);
		heap.insert((double)5);
		heap.insert((double)6);

		assertEquals(6,heap.top(),0.001);
	}

	@Test
	public void insert0And1And3And5And6intoNewHeap_maxIs6() {

		Heap<Integer> heap = new Heap(true);
		heap.insert(0);
		heap.insert(1);
		heap.insert(3);
		heap.insert(5);
		heap.insert(6);

		assertEquals(6,heap.extractMax(),0.001);
	}

	@Test
	public void insert0And1And3And5And6intoNewHeap_isGoodSize() {

		Heap<Integer> heap = new Heap(true);
		heap.insert(0);
		heap.insert(1);
		heap.insert(3);
		heap.insert(5);
		heap.insert(6);
		heap.extractMax();

		assertEquals(4,heap.size(),0.001);
	}

	@Test
	public void insert0And1And2And5And6intoNewHeap_maxIsExtracted() {

		Heap<Integer> heap = new Heap(true);
		heap.insert(0);
		heap.insert(1);
		heap.insert(2);
		heap.insert(5);
		heap.insert(6);
		heap.extractMax();

		assertEquals(5,heap.top(),0.001);
	}

	@Test
	public void insert0And1And2And3And6intoNewHeap_maxIsDeleted() {

		Heap<Integer> heap = new Heap(true);
		heap.insert(0);
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		heap.insert(6);
		assertEquals(true,heap.deleteMax());
		assertEquals(3,heap.top(),0.001);
	}

	@Test
	public void insert0_1_2_3_8_replaceTo6() {

		Heap<Double> heap = new Heap(true);
		heap.insert((double)0);
		heap.insert((double)1);
		heap.insert((double)2);
		heap.insert((double)3);
		heap.insert((double)8);
		assertEquals(true,heap.replace((double)6));
		assertEquals(6,heap.top(),0.001);
	}

	@Test
	public void insert0_1_2_3_8_replaceToMinus1() {

		Heap<Double> heap = new Heap(true);
		heap.insert((double)0);
		heap.insert((double)1);
		heap.insert((double)2);
		heap.insert((double)3);
		heap.insert((double)8);
		assertEquals(true,heap.replace((double)-1));
		assertEquals(3,heap.top(),0.001);
	}

	@Test
	public void heapifyFromArrayList_0_1_2_3_4_5() {
		ArrayList<Double> tab = new ArrayList<>();
		tab.add((double)0);
		tab.add((double)1);
		tab.add((double)2);
		tab.add((double)3);
		tab.add((double)4);
		tab.add((double)5);
		Heap<Double> heap = new Heap(true);
		heap.heapify(tab);

		assertEquals(6,heap.size());
		assertEquals(5,heap.top(),0.001);
	}

	@Test
	public void meld0_1_2With1_2_3() {
		Heap<Integer> heap1 = new Heap(true);
		heap1.insert(0);
		heap1.insert(1);
		heap1.insert(2);
		Heap<Integer> heap2 = new Heap(true);
		heap2.insert(1);
		heap2.insert(2);
		heap2.insert(3);
		heap1.meld(heap2);

		assertEquals(6,heap1.size());
		assertEquals(3,heap1.top(),0.001);
	}

	@Test
	public void isWorkingForString() {
		Heap<String> heap = new Heap(true);
		heap.insert("a");
		heap.insert("ab");
		heap.insert("ad");


		assertEquals(3,heap.size());
		heap.top();
		assertEquals("ad",heap.top());
	}

	@Test
	public void isMinimumFirst() {
		Heap<Integer> heap = new Heap(false);
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);

		assertEquals(3,heap.size());
		assertEquals(1,heap.extractMax(),0.001);
		assertEquals(2,heap.top(),0.001);
	}
}
