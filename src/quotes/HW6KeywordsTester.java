
package quotes;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;

public class HW6KeywordsTester{

  private KeyWordsTester keyWordsTester;

  @Before public void setUp(){

    ArrayList<String> keywords = new ArrayList<String>(Arrays.asList("one", "two"));
    Quote q = new Quote("Quote1", "", keywords);
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////
  // REFACTORED BY REMOVING AFTER TEST 3:
  // TEST1 - Trivial test to check that a quote can now retain keywords:
  @Test public void addKeywords(){

    assertTrue("Quotes size not equal to 2", q.getKeyWords().size() == 2);
  }

  // TEST2 - Test to add keywords to quotes within 1 - 5 keyword bounds:
  @Test public void addKeywordsInBounds(){

    q.setKeyWords(keywords.append("three"));
    assertTrue("Keywords list size not equal to 3", q.getKeyWords().size() == 3);
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////
  // REFACTORED TO INCLUDE EXCEPTION AFTER TEST 5
  // TEST3 - Test to check whether 1 - 5 keyword bounds enforced:
  @Test (expected = IllegalArgumentException.class)
  public void addKeywordsOutBounds(){

    q.addKeyWord("three");
    q.addKeyWord("four");
    q.addKeyWord("five");
    q.addKeyWord("six");
  }

  /*//////////////////////////////////////////////////////////////////////////////////////////////
  // REFACTORED BY REMOVING; COMBINATION OF TEST 4 AND TEST 2 ALREADY TEST FUNCTIONALITY FOR THIS
  // TEST 4 - Test to check that a keyword string is within 44 characters or less:
  @Test public void addKeywordInLength(){

    String keyword = "This-string-is-within-44-char-limits.";
    q.setKeyWords(new ArrayList<String>(Arrays.asList(keyword)));
    assertTrue("Keyword invalid size", q.getKeyWords().get(0).length() == keyword.length())
  }*/

  // TEST 5 - Test to check that a keyword greater than 44 characters results in error:
  @Test (expected = IllegalArgumentException.class)
  public void addKeywordOutLength(){

    String keyword = "ThisStringIsJustNOTWithinThe44CharacterLimits.";
    q.addKeyWord(keyword);
  }

  // TEST 6 - Test to check that a keyword with invalid characters is invalid:
  @Test (expected = IllegalArgumentException.class)
  public void noInvalidChars(){

    String keyword = "ThisStringHas W H I T E S P A C E";
    q.addKeyWord(keyword);
  }

  // TEST 7 - Test to check that quotes containing queried keywords are returned:
  @Test public void searchQuotesKeyword(){

    ArrayList<String> new_keywords = new ArrayList<String>(Arrays.asList("1", "two", "three"));
    Quote new_q = new Quote("Quote2", "", new_keywords);
    
  }
}
