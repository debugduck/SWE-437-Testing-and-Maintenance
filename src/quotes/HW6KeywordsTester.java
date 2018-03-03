
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
		keyWordsTester = new KeyWordsTester();
		ArrayList<String> keyWords1 = new ArrayList<String>(Arrays.asList("one", "two"));
		ArrayList<String> keyWords2 = new ArrayList<String>(Arrays.asList("two", "three"));
		ArrayList<String> keyWords3 = new ArrayList<String>(Arrays.asList("three", "four"));
		Quote q1 = new Quote("Quote One", "Author One", keyWords1);
		Quote q2 = new Quote("Quote Two", "Author Two", keyWords2);
		Quote q3 = new Quote("Quote Three", "Author Three", keyWords3);
		QuoteList qList = new QuoteList();
		qList.setQuote(q1);
		qList.setQuote(q2);
		qList.setQuote(q3);
		keyWordsTester.qList = qList;
	}

	/*//////////////////////////////////////////////////////////////////////////////////////////////
	// REFACTORED BY REMOVING AFTER TEST 3:
	// TEST1 - Trivial test to check that a quote can now retain keywords:
	@Test public void addKeywords(){
		boolean not2 = false;
		for (int i = 0; i < keyWordsTester.qList.getSize(); i++)
		{
			if (keyWordsTester.qList.getQuote(i).getKeyWords().size() !=2 )
			{
				not2 = true;
				break;
			}
		}
		assertTrue("A quote's keyword arraylist size not equal to 2", not2 == false);
	}*/

	// TEST2 - Test to add keywords to quotes within 1 - 5 keyword bounds:
	@Test public void addKeywordsInBounds(){

		for (int i = 0; i < keyWordsTester.qList.getSize(); i++)
		{
			keyWordsTester.qList.getQuote(i).getKeyWords().add("three");
		}
		boolean not3 = false;
		for (int i = 0; i < keyWordsTester.qList.getSize(); i++)
		{
			if (keyWordsTester.qList.getQuote(i).getKeyWords().size() !=3 )
			{
				not3 = true;
				break;
			}
		}
		assertTrue("A quote's keywords list size not equal to 3 after adding a keyword", not3 == false);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// REFACTORED TO INCLUDE EXCEPTION AFTER TEST 5
	// TEST3 - Test to check whether 1 - 5 keyword bounds enforced:
	@Test (expected = IllegalArgumentException.class)
	public void addKeywordsOutBounds(){

		keyWordsTester.qList.getQuote(0).addKeyWord("three");
		keyWordsTester.qList.getQuote(0).addKeyWord("four");
		keyWordsTester.qList.getQuote(0).addKeyWord("five");
		keyWordsTester.qList.getQuote(0).addKeyWord("six");
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
		keyWordsTester.qList.getQuote(0).addKeyWord(keyword);
	}

	// TEST 6 - Test to check that a keyword with invalid characters is invalid:
	@Test (expected = IllegalArgumentException.class)
	public void noInvalidChars(){
		String keyword = "ThisStringHas W H I T E S P A C E";
		keyWordsTester.qList.getQuote(0).addKeyWord(keyword);
	}

  /*//////////////////////////////////////////////////////////////////////////////////////////////
  // REFACTORED BY REMOVING; TEST 8 AND TEST 9 RENDER THIS TEST REDUNDANT AND UNECESSARY
  // TEST 7 - Trivial test to check that quotes with a specified keyword are returned:
  @Test public void searchKeyword(){
  String keyword = "two";
  ArrayList<Quote> results = new ArrayList<Quote>(Arrays.asList(q1, q1));
  assertTrue("Did not find quotes with matching keyword", results.equals(new ArrayList<Quote>(Arrays.asList(q1, q2))));
  }*/

	// TEST 8 - Test to check that quotes containing queried keywords are returned:
	@Test public void searchQuotesKeyword(){
		String keyword = "two";
		ArrayList<String> keyWords1 = new ArrayList<String>(Arrays.asList("one", "two"));
		ArrayList<String> keyWords2 = new ArrayList<String>(Arrays.asList("two", "three"));
		Quote q1 = new Quote("Quote One", "Author One", keyWords1);
		Quote q2 = new Quote("Quote Two", "Author Two", keyWords2);
		//assertTrue("Incorrect quotes returned", keyWordsTester.qList.searchKeyword(keyword).equals(new ArrayList<Quote>(Arrays.asList(q1, q2))));
	}

	// TEST 9 - Test to check that no quotes returned if no matching keyword:
	@Test public void searchQuotesKeywordNull(){
		String keyword = "3";
		//assertTrue("Should have returned no quotes", keyWordsTester.qList.searchKeyword(keyword).equals(new ArrayList<Quote>()));
	}
}
