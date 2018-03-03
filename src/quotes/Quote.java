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
	public Quote () {}

	// Constructor that assigns both strings
	public Quote (String author, String quoteText, ArrayList<String> keywords)
	{
		this.author = author;
		this.quoteText = quoteText;
    if (!keywords.isEmpty())
    {
		    this.keyWords = keywords;
		    Collections.sort(keyWords);
		}
		else
			this.keyWords = new ArrayList<String>();
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

  // Sets a whole list of keywords to KeyWords
	public void setKeyWords (ArrayList<String> keyWords)
  {
    if (keyWords.size() > 5)
    {
      for (String keyword : keyWords)
        if (checkKeyword(keyword))
          continue;
    }
    else
      throw new IllegalArgumentException("Input keywords list contains more than 5 keywords.");

    this.keyWords = keyWords;
    Collections.sort(keyWords);
	}

  // Adds a single keyword a the KeyWords list
  public void addKeyWord (String keyword)
  {
    if (checkKeyCapacity())
    {
      if (checkKeyword(keyword))
      {
        keyWords.add(keyword);
        Collections.sort(keyWords);
      }
    }
  }

  // Checks that current capacity of KeyWords does not exceed 5 before adding new keyword
	private boolean checkKeyCapacity()
  {
		if (this.keyWords.size() == 5)
			throw new IllegalArgumentException("Keyword per quote limit reached.");

    return true;
	}

  // Checks that a keyword conforms to constraints of 44 characters + no whitespace
  public boolean checkKeyword(String keyword)
  {
    if (keyword.length() > 44)
      throw new IllegalArgumentException("Keyword length exceeds 44 characters.");

    if (containsInvalidChar(keyword))
      throw new IllegalArgumentException("Keyword should not contain whitespace.");

    return true;
  }

  // Checks that a given keyword contains whitespace
  private boolean containsInvalidChar(String keyword)
  {
    for (int i = 0; i < keyword.length(); i++)
      if (Character.isWhitespace(keyword.charAt(i)) || !Character.isLetterOrDigit(keyword.charAt(i)))
        return true;

    return false;
  }

  @Override
  public String toString ()
  {
    return "Quote {" + "author='" + author + '\'' + ", quoteText='" + quoteText + '\'' + ", keyWords='" + keyWords.toString() + '}';
  }
}
