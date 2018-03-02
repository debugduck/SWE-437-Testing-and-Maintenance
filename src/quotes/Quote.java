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

	private boolean checkLimit(ArrayList<String> keyWords){

		if (keyWords.size() > 5){
			throw new IllegalArgumentException("Keyword per quote limit exceeded.");
    }

		for (String s : keyWords){
			if (s.length() > 44){
				throw new IllegalArgumentException("Keyword length exceeds 44 characters.");
      }
      if (containsWhitespace(s)){
        throw new IllegalArgumentException("Keyword should not contain whitespace.");
      }
    }
		return true;
	}

  // Checks that a given keyword contains whitespace:
  private boolean containsWhitespace(String keyword){

    for (int i = 0; i < keyword.length(); i++){

      if (Character.isWhitespace(keyword.charAt(i))){
        return true;
      }
    }
    return false;
  }
}
