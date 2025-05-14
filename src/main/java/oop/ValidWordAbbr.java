package oop;

import java.util.*;
/**
 * Unique Word Abbreviation 
 * The abbreviation of a word is a concatenation of its first letter, the number 
 * of characters between the first and last letter, and its last letter. If a 
 * word has only two characters, then it is an abbreviation of itself. 
 *  
 * For example: 
 *  
 * dog --> d1g because there is one letter between the first letter 'd' and the 
 * last letter 'g'. 
 * internationalization --> i18n because there are 18 letters between the first 
 * letter 'i' and the last letter 'n'. 
 * it --> it because any word with only two characters is an abbreviation of 
 * itself. 
 * Implement the ValidWordAbbr class: 
 *  
 * ValidWordAbbr(String[] dictionary) Initializes the object with a dictionary 
 * of words. 
 * boolean isUnique(string word) Returns true if either of the following 
 * conditions are met (otherwise returns false): 
 * There is no word in dictionary whose abbreviation is equal to word's 
 * abbreviation. 
 * For any word in dictionary whose abbreviation is equal to word's 
 * abbreviation, that word and word are the same. 
 * -----------------------------
 * 
 */

 class ValidWordAbbr {
  private Map<String, String> map = new HashMap<>();
  public ValidWordAbbr(String[] dictionary) {
    for (String word : dictionary) {
      String abbr = getAbbr(word);
      if(!map.containsKey(abbr)) {
        map.put(abbr, word);
      } else if (!map.get(abbr).isEmpty() && !map.get(abbr).equals(word)) {
        // Value as empty string indicates there are mulitple different words 
        // with the same abbr.
        map.put(abbr, "");
      }
    }
  }
  private String getAbbr(String word) {
    int len = word.length();
    if(len<=2) return word;
    String abbr = word.charAt(0) + String.valueOf(len-2) + word.charAt(len-1);
    return abbr;
  }
  
  public boolean isUnique(String word) {
    String abbr = getAbbr(word);
    return !map.containsKey(abbr) || map.get(abbr).equals(word);
  }
}
