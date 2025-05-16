package oop;

import java.util.*;
/**
 * 4. Find Median from Data Stream 
 * The median is the middle value in an ordered integer list. If the size of the 
 * list is even, there is no middle value, and the median is the mean of the two 
 * middle values. 
 *  
 * For example, for arr = [2,3,4], the median is 3. 
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5. 
 * Implement the MedianFinder class: 
 *  
 * MedianFinder() initializes the MedianFinder object. 
 * void addNum(int num) adds the integer num from the data stream to the data 
 * structure. 
 * double findMedian() returns the median of all elements so far. Answers within 
 * 10-5 of the actual answer will be accepted. 
 * -----------------------------
 * 
 * Understand The Problem
 * Data keeps coming from a data stream and the data is not sorted.
 * 
 * Approach 1:
 * Maintain a sorted list of elements so far. 
 * To maintain a sorted list with dynamic updates effeciently,
 * we need a data structure like balanced binary search (red-black tree) and
 * adding a new data takes O(lgN) times. But to find the median in the tree is not
 * straight-forward and need to travel first half of the tree (time O(n));
 * 
 * Approach 2:
 * We don't need the entire list to be sorted to find the median. We only need to
 * split to half-half, so using a two heaps is a more efficient approach.
 * Maintain two balanced heaps, always having almost the same capacity. First heap is
 * a max-heap and the second one is a min-heap.
 * - To insert a new elment to a heap takes O(lgN).
 * - The median can be calculated with the top elements of the two heaps (time O(1)).
 * maxHeap, minHeap
 * 
 * mapHeap.size() >= minHeap.size()
 * if num >= minHeap.peek(): minHeap.add(num)
 * else maxHeap.add(num)
 * 
 * balance(maxHeap, minHeap): maxHeap.size() >= minHeap.size() and diff <= 1
 * 
 * PriorityQueue: add(), peek(), remove()
 */

public class MedianFinder {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    void addNum(int num) {
        if (!minHeap.isEmpty() && num > minHeap.peek()) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }
        // balance the two heap
        // after balancing maxHeap.size() >= minHeap.size() and diff <= 1
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.remove());
        } else if (maxHeap.size() - minHeap.size() == 2) {
            minHeap.add(maxHeap.remove());
        }
    }
    double findMedian() {
        if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
            if(maxHeap.size() == minHeap.size()) {
                return ((double) (maxHeap.peek() + minHeap.peek())) / 2;
            } else {
                return maxHeap.peek();
            }
        } else if (!maxHeap.isEmpty()) {
            return maxHeap.peek();
        } else {
            return 0;
        }
    }
    public static void main(String... args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9,10};
        MedianFinder finder = new MedianFinder();
        List<Double> medianList = new ArrayList<>();
        for (int num : nums) {
            finder.addNum(num);
            medianList.add(finder.findMedian());
        }
        System.out.println(medianList);
        nums = new int[]{10,9, 8, 7, 6,5, 4, 3, 2, 1};
        medianList = new ArrayList<>();
        finder = new MedianFinder();
        for(int num : nums) {
            finder.addNum(num);
            medianList.add(finder.findMedian());
        }
        System.out.println(medianList);
    }
}
