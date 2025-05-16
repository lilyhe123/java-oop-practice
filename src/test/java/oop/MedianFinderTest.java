package oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;


class MedianFinderTest {

  @Test
  void testAddNumAndFindMedianWithAscendingOrder() {
    MedianFinder finder = new MedianFinder();
    int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    List<Double> expectedMedians = List.of(1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5);
    List<Double> actualMedians = new ArrayList<>();

    for (int num : nums) {
      finder.addNum(num);
      actualMedians.add(finder.findMedian());
    }

    assertEquals(expectedMedians, actualMedians);
  }

  @Test
  void testAddNumAndFindMedianWithDescendingOrder() {
    MedianFinder finder = new MedianFinder();
    int[] nums = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    List<Double> expectedMedians = List.of(10.0, 9.5, 9.0, 8.5, 8.0, 7.5, 7.0, 6.5, 6.0, 5.5);
    List<Double> actualMedians = new ArrayList<>();

    for (int num : nums) {
      finder.addNum(num);
      actualMedians.add(finder.findMedian());
    }

    assertEquals(expectedMedians, actualMedians);
  }

  @Test
  void testAddNumAndFindMedianWithMixedOrder() {
    MedianFinder finder = new MedianFinder();
    int[] nums = {5, 15, 1, 3};
    List<Double> expectedMedians = List.of(5.0, 10.0, 5.0, 4.0);
    List<Double> actualMedians = new ArrayList<>();

    for (int num : nums) {
      finder.addNum(num);
      actualMedians.add(finder.findMedian());
    }

    assertEquals(expectedMedians, actualMedians);
  }

  @Test
  void testFindMedianWithNoElements() {
    MedianFinder finder = new MedianFinder();
    assertEquals(0.0, finder.findMedian());
  }

  @Test
  void testFindMedianWithSingleElement() {
    MedianFinder finder = new MedianFinder();
    finder.addNum(42);
    assertEquals(42.0, finder.findMedian());
  }
}