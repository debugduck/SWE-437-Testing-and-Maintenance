package quotes;

import java.util.*;
/**
 * Quote data object.
 * @author Mongkoldech Rajapakdee & Jeff offutt
 *         Date: Nov 2009
 * A quote has two parts, an author and a quoteText.
 * This bean class provides getters and setters for both, plus a toString()
 */
public class Quote
{
	private String author;
	private String quoteText;
	private ArrayList<String> keyWords;

	// Default constructor does nothing
	public Quote ()
	{
	}

	// Constructor that assigns both strings
	public Quote (String author, String quoteText, ArrayList<String> keywords)
	{
		this.author = author;
		this.quoteText = quoteText;
		if (checkLimit(keywords))
		{
			keyWords = keywords;
			Collections.sort(keyWords);
		}
		else
			keyWords = new ArrayList<String>();
		
	}

	// Getter and setter for author
	public String getAuthor ()
	{
		return author;
	}
	public void setAuthor (String author)
	{
		this.author = author;
	}

	// Getter and setter for quoteText
	public String getQuoteText ()
	{
		return quoteText;
	}
	public void setQuoteText (String quoteText)
	{
		this.quoteText = quoteText;
	}

	// Getter and setter for keyWords
	public ArrayList<String> getKeyWords ()
	{
		return keyWords;
	}
	public boolean setKeyWords (ArrayList<String> keyWords)
	{
		if (checkLimit(keyWords))
		{
			this.keyWords = keyWords;
			Collections.sort(keyWords);
			return true;
		} else 
		{
			return false;
		}

	}


	@Override
	public String toString ()
	{
		return "Quote {" + "author='" + author + '\'' + ", quoteText='" + quoteText + '\'' + ", keyWords='" + keyWords.toString() + '}';
	}

	private boolean checkLimit(ArrayList<String> keyWords)
	{
		if (keyWords.size() > 5)
			return false;
		for (String s : keyWords)
			if (s.length() > 44)
				return false;
		return true;
	}
}
