package oop;

import java.util.Iterator;

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
