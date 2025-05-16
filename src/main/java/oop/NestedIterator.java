package oop;

import java.util.*;
/**
 * 3. Flatten Nested List Iterator 
 * You are given a nested list of integers nestedList. Each element is either an 
 * integer or a list whose elements may also be integers or other lists. 
 * Implement an iterator to flatten it. 
 *  
 * Implement the NestedIterator class: 
 *  
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with 
 * the nested list nestedList. 
 * int next() Returns the next integer in the nested list. 
 * boolean hasNext() Returns true if there are still some integers in the nested 
 * list and false otherwise. 
 * Your code will be tested with the following pseudocode: 
 *  
 * initialize iterator with nestedList 
 * res = [] 
 * while iterator.hasNext() 
 *     append iterator.next() to the end of res 
 * return res 
 * If res matches the expected flattened list, then your code will be judged as 
 * correct. 
 * -----------------------------
 *  
 * Understand the problem.
 * The input nestedList is basically a tree data structure. 
 * If the NestedInteger is an integer, it's the leaf node of the tree.
 * Otherwise it's a subtree.
 * 
 * We need to traverse the tree with depth-first approach, when next() is called we
 *  move a step forward, until all elements in the tree are visited. 
 * 
 * approach 1:
 * we can do the full traversal first and store all the integers to a list,
 * then it's straight forward to use an index to track the current element and go to next.
 * but with this approach we need to create a new data structure to store all element first.
 * No space efficiency.
 * space O(n), n is the total number of integers in the given list.
 * 
 * approach 2: 
 * Can we do the tree traversal on-the-fly and move a step forward when next() is called.
 * How to reserve the traversal status? So we know how to continue when next() is called.
 * 
 * We can use a stack to store all the parent NestedInteger along the way.
 * The max size of the queue is the max depth of the tree.
 * space O(d), d is the max depth of the tree.
 * 
 * Stack<Iterator<NestedInteger>> stack;
 * Integer nextValue;
 * initally only one element in the stack, the given input nested list.
 * For each next() call, find the next integer in the nested list.
 * 1. Move up until a non-empty iterator.
 * 2. move down until a leaf node or move up if encounter an empty iterator. 
 *    Store and return the int value.    
 *     
 */

public class NestedIterator implements Iterator<Integer> {
    private Deque<Iterator<NestedInteger>> stack = new ArrayDeque<>();
    private Integer nextInteger;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack.push(nestedList.iterator());
        getNext();
    }
    // find the next element and store to the nextInteger
    private void getNext() {
        // go up
        Iterator<NestedInteger> iterator = null;
        while (!stack.isEmpty()) {
            iterator = stack.pop();
            if(iterator.hasNext()) break;
        }
        if(iterator == null || !iterator.hasNext()) {// no more element to visit
            nextInteger = null;
            return;
        }
        // go down until a leaf node or can not go down anymore, go up
        NestedInteger nestedList = iterator.next();
        while (!nestedList.isInteger()) {
            stack.push(iterator);
            List<NestedInteger> list = nestedList.getList();
            iterator = list.iterator();
            if (!iterator.hasNext()) {
                // empty iterator, needs to go up
                getNext();
                return;// !! return directly
            }
            nestedList = iterator.next();   
        }
        nextInteger = nestedList.getInteger();
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        return nextInteger != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        Integer rtn = nextInteger;
        getNext();
        return rtn;
    }

    public static void main(String... args) {
        //runTestTwo();
    }
}

interface NestedInteger {
    public boolean isInteger();
    public Integer getInteger();
    public List<NestedInteger> getList();
 }
class NestedIntegerImpl implements NestedInteger {
    private boolean isInteger;
    private List<NestedInteger> list;
    private Integer integer;

    public NestedIntegerImpl(int val) {
        this.isInteger = true;
        this.integer = Integer.valueOf(val);
    }
    public NestedIntegerImpl(List<NestedInteger> list) {
        this.isInteger = false;
        this.list = list;
    }
    public boolean isInteger() {
        return isInteger;
    }
    public Integer getInteger(){
        return this.integer;
    }
    public List<NestedInteger> getList(){
        return this.list;
    }
 }

