package quotes;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;

public class KeywordSearchTest {

	private KeyWordsTester keyWordsTester;
	
	@Before
	public void setUp() {
		keyWordsTester = new KeyWordsTester();
		ArrayList<String> keyWords1 = new ArrayList<String>(Arrays.asList("one", "1", "won"));
		Quote q1 = new Quote("", "", keyWords1);
		Quote q2 = new Quote("", "", keyWords1);
		Quote q3 = new Quote("", "", keyWords1);
		Quote q4 = new Quote("", "", keyWords1);
		QuoteList qList = new QuoteList();
		qList.setQuote(q1);
		qList.setQuote(q2);
		qList.setQuote(q3);
		qList.setQuote(q4);
		keyWordsTester.qList = qList;
	}
	
	@Test
	public void addQuote()
	{
		keyWordsTester.getNewQuote();
		boolean overLimit = false;
		for (int i = 0; i < keyWordsTester.qList.getSize(); i++)
		{
			if (keyWordsTester.qList.getQuote(i).getKeyWords().size() > 5)
			{
				overLimit = true;
				break;
			}
		}
		assertTrue("Quote has more than 5 keywords.", overLimit == false);
	}
}
