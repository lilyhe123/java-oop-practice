package oop;

import java.util.Iterator;
/**
 * Design an iterator that supports the peek operation on an existing iterator 
 * in addition to the hasNext and the next operations. 
 *  
 * Implement the PeekingIterator class: 
 *  
 * PeekingIterator(Iterator<int> nums) Initializes the object with the given 
 * integer iterator iterator. 
 * int next() Returns the next element in the array and moves the pointer to the 
 * next element. 
 * boolean hasNext() Returns true if there are still elements in the array. 
 * int peek() Returns the next element in the array without moving the pointer. 
 * Note: Each language may have a different implementation of the constructor 
 * and Iterator, but they all support the int next() and boolean hasNext() 
 * functions. 
 * -----------------------------
 * 
 */


class PeekingIterator implements Iterator<Integer> {
  private boolean hasNext;
  private int top;// Holds the next element unless hasNext is false
  private Iterator<Integer> iterator;
  public PeekingIterator(Iterator<Integer> iterator) {
    if (iterator.hasNext()) {
      hasNext = true;
      top = iterator.next();
    }
    this.iterator = iterator;
  }
  public int peek() {
    if (hasNext) {
      return top;
    } else {
      throw new IllegalStateException("The iterator is empty.");
    }
  }
  @Override
  public boolean hasNext() {
    return hasNext;
  }

  @Override
  public Integer next() {
    if (hasNext) {
      int nextVal = top;
      if (iterator.hasNext()) {
        top = iterator.next();
      } else {
        hasNext = false;
      }
      return nextVal;
    } else {
      throw new IllegalStateException("The iterator is empty.");
    }
  }
}
