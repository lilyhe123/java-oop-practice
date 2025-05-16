package oop;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NestedIteratorTest {

  @Test
  void testSingleInteger() {
    List<NestedInteger> list = new ArrayList<>();
    list.add(new NestedIntegerImpl(1));

    NestedIterator iterator = new NestedIterator(list);
    assertTrue(iterator.hasNext());
    assertEquals(1, iterator.next());
    assertFalse(iterator.hasNext());
  }
  @Test
  void testNestedEmptyList() {
    //[[]]
    List<NestedInteger> list = new ArrayList<>();
    List<NestedInteger> listOne = new ArrayList<>();
    NestedInteger nested = new NestedIntegerImpl(listOne);
    list.add(nested);
    NestedIterator iterator =  new NestedIterator(list);
    assertFalse(iterator.hasNext());
  }

  @Test
  //[1,[2,3],4]
  void testNestedList() {
    List<NestedInteger> list = new ArrayList<>();
    list.add(new NestedIntegerImpl(1));
    List<NestedInteger> nestedOne = new ArrayList<>();
    nestedOne.add(new NestedIntegerImpl(2));
    nestedOne.add(new NestedIntegerImpl(3));
    list.add(new NestedIntegerImpl(nestedOne));
    list.add(new NestedIntegerImpl(4));

    NestedIterator iterator = new NestedIterator(list);
    assertTrue(iterator.hasNext());
    assertEquals(1, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(2, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(3, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(4, iterator.next());
    assertFalse(iterator.hasNext());
  }

  @Test
  //[1,[2,[3,4]],5]
  void testDeeplyNestedList() {
    List<NestedInteger> list = new ArrayList<>();
    list.add(new NestedIntegerImpl(1));
    List<NestedInteger> nestedOne = new ArrayList<>();
    nestedOne.add(new NestedIntegerImpl(2));
    List<NestedInteger> nestedTwo = new ArrayList<>();
    nestedTwo.add(new NestedIntegerImpl(3));
    nestedTwo.add(new NestedIntegerImpl(4));
    nestedOne.add(new NestedIntegerImpl(nestedTwo));
    list.add(new NestedIntegerImpl(nestedOne));
    list.add(new NestedIntegerImpl(5));

    NestedIterator iterator = new NestedIterator(list);
    assertTrue(iterator.hasNext());
    assertEquals(1, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(2, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(3, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(4, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(5, iterator.next());
    assertFalse(iterator.hasNext());
  }

  @Test
  void testEmptyList() {
    List<NestedInteger> list = new ArrayList<>();

    NestedIterator iterator = new NestedIterator(list);
    assertFalse(iterator.hasNext());
  }

  @Test
  void testComplexNestedList() {
    List<NestedInteger> list = new ArrayList<>();
    List<NestedInteger> nestedOne = new ArrayList<>();
    nestedOne.add(new NestedIntegerImpl(1));
    List<NestedInteger> nestedTwo = new ArrayList<>();
    nestedTwo.add(new NestedIntegerImpl(2));
    List<NestedInteger> nestedThree = new ArrayList<>();
    nestedThree.add(new NestedIntegerImpl(3));
    nestedThree.add(new NestedIntegerImpl(4));
    nestedTwo.add(new NestedIntegerImpl(nestedThree));
    nestedOne.add(new NestedIntegerImpl(nestedTwo));
    list.add(new NestedIntegerImpl(nestedOne));

    NestedIterator iterator = new NestedIterator(list);
    assertTrue(iterator.hasNext());
    assertEquals(1, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(2, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(3, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(4, iterator.next());
    assertFalse(iterator.hasNext());
  }
}