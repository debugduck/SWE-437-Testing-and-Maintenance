
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
		Quote q1 = new Quote("Quote One", "Author One", keyWords1);
		//Quote q2 = new Quote("", "", keyWords1);
		//Quote q3 = new Quote("", "", keyWords1);
		//Quote q4 = new Quote("", "", keyWords1);
		QuoteList qList = new QuoteList();
		qList.setQuote(q1);
		//qList.setQuote(q2);
		//qList.setQuote(q3);
		//qList.setQuote(q4);
		keyWordsTester.qList = qList;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// REFACTORED BY REMOVING AFTER TEST 3:
	// TEST1 - Trivial test to check that a quote can now retain keywords:
	@Test public void addKeywords(){
		assertTrue("Quote keyword arraylist size not equal to 2", keyWordsTester.qList.getQuote(0).getKeyWords().size() == 2);
	}

	// TEST2 - Test to add keywords to quotes within 1 - 5 keyword bounds:
	@Test public void addKeywordsInBounds(){

		keyWordsTester.qList.getQuote(0).getKeyWords().add("three");
		assertTrue("Keywords list size not equal to 3", keyWordsTester.qList.getQuote(0).getKeyWords().size() == 3);
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


	// TEST 7 - Test to check that quotes containing queried keywords are returned:
	@Test public void searchQuotesKeyword(){
		String keyword = "two";
		ArrayList<String> keyWords1 = new ArrayList<String>(Arrays.asList("one", "two"));
		Quote q1 = new Quote("Quote One", "Author One", keyWords1);
		//assertTrue("Incorrect quotes returned", keyWordsTester.qList.searchKeywords(keyword).equals(new ArrayList<Quote>(Arrays.asList(q1))));
	}

	// TEST 8 - Test to check that no quotes returned if no matching keyword:
	@Test public void searchQuotesKeyworNull(){
		String keyword = "3";
		//assertTrue("Should have returned no quotes", keyWordsTester.qList.searchKeywords(keyword).equals(new ArrayList<Quote>()));
	}
}
