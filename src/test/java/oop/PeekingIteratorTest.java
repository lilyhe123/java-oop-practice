package oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PeekingIteratorTest {

  @Test
  public void testPeekingIteratorFunctionality() {
    int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
    List<Integer> peekList = new ArrayList<>();
    List<Integer> nextList = new ArrayList<>();
    PeekingIterator peekingIterator = new PeekingIterator(list.iterator());
    
    while (peekingIterator.hasNext()) {
      peekList.add(peekingIterator.peek());
      nextList.add(peekingIterator.next());
    }
    
    assertEquals(list, peekList, "Original list should be equal to the peekList.");
    assertEquals(list, nextList, "Original list should be equal to the nextList.");
  }

  @Test
  public void testPeekWithoutNext() {
    List<Integer> list = Arrays.asList(1);
    PeekingIterator peekingIterator = new PeekingIterator(list.iterator());
    
    assertEquals(1, peekingIterator.peek(), "Peek should return the first element.");
    assertEquals(1, peekingIterator.next(), "Next should return the first element.");
    assertFalse(peekingIterator.hasNext(), "Iterator should not have more elements.");
  }

  @Test
  public void testEmptyIterator() {
    List<Integer> list = new ArrayList<>();
    PeekingIterator peekingIterator = new PeekingIterator(list.iterator());
    
    assertFalse(peekingIterator.hasNext(), "Iterator should be empty.");
    assertThrows(IllegalStateException.class, peekingIterator::peek, "Peek should throw exception for empty iterator.");
    assertThrows(IllegalStateException.class, peekingIterator::next, "Next should throw exception for empty iterator.");
  }

  @Test
  public void testSingleElementIterator() {
    List<Integer> list = Arrays.asList(42);
    PeekingIterator peekingIterator = new PeekingIterator(list.iterator());
    
    assertTrue(peekingIterator.hasNext(), "Iterator should have one element.");
    assertEquals(42, peekingIterator.peek(), "Peek should return the single element.");
    assertEquals(42, peekingIterator.next(), "Next should return the single element.");
    assertFalse(peekingIterator.hasNext(), "Iterator should not have more elements.");
    assertThrows(IllegalStateException.class, peekingIterator::peek, "Peek should throw exception after last element.");
    assertThrows(IllegalStateException.class, peekingIterator::next, "Next should throw exception after last element.");
  }
}
