package oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidWordAbbrTest {
  @Test
  public void test() {
    String[] dictionary = {"deer", "door", "cake", "card"};
    ValidWordAbbr validWordAbbr = new ValidWordAbbr(dictionary);
    assertFalse(validWordAbbr.isUnique(("deer")));
    assertTrue(validWordAbbr.isUnique(("cart")));
    assertFalse(validWordAbbr.isUnique(("cane")));
    assertTrue(validWordAbbr.isUnique(("make")));
    assertTrue(validWordAbbr.isUnique(("cake")));
  }

  @Test
  public void testIsUnique_NoConflicts() {
    String[] dictionary = {"dog", "cat", "fish"};
    ValidWordAbbr validWordAbbr = new ValidWordAbbr(dictionary);

    assertTrue(validWordAbbr.isUnique("dog")); // Same word in dictionary
    assertTrue(validWordAbbr.isUnique("bat")); // Unique abbreviation
  }

  @Test
  public void testIsUnique_ConflictingAbbreviations() {
    String[] dictionary = {"dog", "dig"};
    ValidWordAbbr validWordAbbr = new ValidWordAbbr(dictionary);

    assertFalse(validWordAbbr.isUnique("dag")); // Conflicting abbreviation "d1g"
  }

  @Test
  public void testIsUnique_SameAbbreviationSameWord() {
    String[] dictionary = {"dog"};
    ValidWordAbbr validWordAbbr = new ValidWordAbbr(dictionary);

    assertTrue(validWordAbbr.isUnique("dog")); // Same word in dictionary
  }

  @Test
  public void testIsUnique_ShortWords() {
    String[] dictionary = {"it", "is"};
    ValidWordAbbr validWordAbbr = new ValidWordAbbr(dictionary);

    assertTrue(validWordAbbr.isUnique("it")); // Same word in dictionary
    assertTrue(validWordAbbr.isUnique("an")); // Unique short word
  }

  @Test
  public void testIsUnique_EmptyDictionary() {
    String[] dictionary = {};
    ValidWordAbbr validWordAbbr = new ValidWordAbbr(dictionary);

    assertTrue(validWordAbbr.isUnique("dog")); // No conflicts in an empty dictionary
  }

  @Test
  public void testIsUnique_MultipleWordsWithSameAbbreviation() {
    String[] dictionary = {"dog", "dig", "dug"};
    ValidWordAbbr validWordAbbr = new ValidWordAbbr(dictionary);

    assertFalse(validWordAbbr.isUnique("dag")); // Conflicting abbreviation "d1g"
  }
}
