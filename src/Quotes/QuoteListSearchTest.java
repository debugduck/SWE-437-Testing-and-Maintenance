package quotes;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;

@RunWith (Parameterized.class)
public class QuoteListSearchTest
{
	//test fixture
	private QuoteList qList; 

	//args for each test call
	public String input;
	public int mode;
	public QuoteList correctQList;

	//constructor
	public QuoteListSearchTest(String input, int mode, QuoteList correctQList)
	{
		this.input = input;
		this.mode = mode;
		this.correctQList = correctQList;
	}

	//List of all input calls we are testing
	@Parameters
	public static Collection<Object[]> searchValues()
	{
		Quote q1 = new Quote("foobar", "one fish");
		Quote q2 = new Quote("foobar foobar", "two fish");
		Quote q3 = new Quote("nobody", "red fish");
		Quote q4 = new Quote("rainbow fish", "red white and blue");
		QuoteList foobarList = new QuoteList();
		foobarList.setQuote(q1);
		foobarList.setQuote(q2);
		QuoteList fishQuoteList = new QuoteList();
		fishQuoteList.setQuote(q1);
		fishQuoteList.setQuote(q2);
		fishQuoteList.setQuote(q3);
		QuoteList fishBothList = new QuoteList();
		fishBothList .setQuote(q1);
		fishBothList .setQuote(q2);
		fishBothList .setQuote(q3);
		fishBothList .setQuote(q4);
		QuoteList emptyList = new QuoteList();
		return Arrays.asList ( new Object [][] {{ "foobar", 0, foobarList},
			{ "fish", 1, fishQuoteList},  { "fish", 2, fishBothList}, { "hoopah", -5, emptyList}});  
	}


	@Before
	public void setUp()
	{
		qList = new QuoteList();
	}

	@After
	public void tearDown()
	{
		qList = null;
	}

	// helper function to populate the dummy QuoteList
	public void populate(QuoteList quoteList)
	{
		Quote q1 = new Quote("foobar", "one fish");
		Quote q2 = new Quote("foobar foobar", "two fish");
		Quote q3 = new Quote("nobody", "red fish");
		Quote q4 = new Quote("rainbow fish", "red white and blue");
		quoteList.setQuote(q1);
		quoteList.setQuote(q2);
		quoteList.setQuote(q3);
		quoteList.setQuote(q4);
	}

	//equals function to test if output is correct
	//first checks if both qLists are same size
	//then iterates through QuoteArray of each list to check if author and string are the same for both QuoteArray
	//if there is a mismatch, return false, else return true at the very end
	public boolean equals(QuoteList aQList, QuoteList bQList)
	{
		//System.out.println(aQList.getSize() + " " + bQList.getSize());
		if (aQList.getSize() != bQList.getSize())
		{
			return false;
		}
		for (int i = 0; i < aQList.getSize(); i++)
		{
			Quote aQuote = aQList.getQuote(i);
			Quote bQuote = bQList.getQuote(i);
			//System.out.println(aQuote.toString() + " " + bQuote.toString());
			if (!(aQuote.getAuthor().equals(bQuote.getAuthor()) &&
					aQuote.getQuoteText().equals(bQuote.getQuoteText())))
			{
				return false;
			}
		}

		return true;
	}

	@Test
	public void testPopulated()
	{
		populate(this.qList);
		assertTrue(("Populated list failed for inputs " + input.toString()),
				true == equals(this.qList.search(input, mode), correctQList));
	}

	@Test
	public void testEmptyAuthorMode()
	{
		assertTrue("Empty list failed for foobar in mode 0 ",
				true == equals(this.qList.search("foobar", 0), new QuoteList()));
	}

	@Test
	public void testEmptyTextMode()
	{
		assertTrue("Empty list failed for fish in mode 1 ",
				true == equals(this.qList.search("fish", 1), new QuoteList()));
	}

	@Test
	public void testEmptyBothMode()
	{
		assertTrue("Empty list failed for fish in mode 2 ",
				true == equals(this.qList.search("fish", 2), new QuoteList()));
	}

	@Test
	public void testEmptyAuthor()
	{
		assertTrue("Empty list failed for hoopah in mode -5 ",
				true == equals(this.qList.search("hoopah", -5), new QuoteList()));
	}

}
