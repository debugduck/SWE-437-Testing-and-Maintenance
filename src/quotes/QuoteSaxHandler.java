package quotes;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;

import org.xml.sax.*;

/**
 * SAX handler for SAX Parser
 * @author Mongkoldech Rajapakdee & Jeff Offutt
 *         Date: Nov 2009
 * Uses the sax parser to parse an XML file into a list of quotes
*/

public class QuoteSaxHandler extends DefaultHandler
{
   private QuoteList quoteList = new QuoteList();
   private Quote quoteTmp = null; // temporary Quote
   private String currentElement = null; // current element name

   // Node names in XML file
   private final String QuoteListElem   = "quote-list";
   private final String QuoteElem       = "quote";
   private final String QuoteAuthorElem = "author";
   private final String QuoteTextElem   = "quote-text";
   private final String QuoteKeywordsElem = "keywords";

public QuoteSaxHandler()
{
   super();
}

public QuoteList getQuoteList()
{
   return quoteList;
}

@Override
public void startDocument ()
{
   // System.out.println ("Start document"); // For testing
}

@Override
public void endDocument ()
{
   // System.out.println ("End document"); // For testing
}

@Override
public void startElement (String uri, String name, String qName, Attributes atts)
{
   if (qName.equalsIgnoreCase (QuoteListElem))
   {
      currentElement = QuoteListElem;
   }
   else if (qName.equalsIgnoreCase(QuoteElem))
   {
      currentElement = QuoteElem;
      quoteTmp = new Quote();
   }
   else if (qName.equalsIgnoreCase (QuoteAuthorElem))
   {
      currentElement = QuoteAuthorElem;
   }
   else if (qName.equalsIgnoreCase (QuoteTextElem))
   {
      currentElement = QuoteTextElem;
   }
   else if (qName.equalsIgnoreCase (QuoteKeywordsElem))
   {
      currentElement = QuoteKeywordsElem;
   }
}

@Override
public void endElement (String uri, String name, String qName)
{
   if (qName.equalsIgnoreCase (QuoteElem))
   {
      quoteList.setQuote (quoteTmp);
      quoteTmp = null;
   }
}

@Override
public void characters (char ch[], int start, int length)
{
   String value = new String (ch, start, length);
   if (!value.trim().equals(""))
   {
      if (currentElement.equalsIgnoreCase (QuoteTextElem))
      {
         quoteTmp.setQuoteText (value);
      }
      else if (currentElement.equalsIgnoreCase (QuoteAuthorElem))
      {
         quoteTmp.setAuthor (value);
      }
      else if (currentElement.equalsIgnoreCase (QuoteKeywordsElem))
      {
    	  String [] tempArray = value.split(", ");
    	  tempArray[0] = tempArray[0].substring(1, tempArray[0].length());
    	  tempArray[tempArray.length-1] = tempArray[tempArray.length-1].substring(0, tempArray[tempArray.length-1].length()-1);
    	  ArrayList<String> keywords = new ArrayList<String>(Arrays.asList(tempArray));
    	  quoteTmp.setKeyWords (keywords);
      }
      
   }
}

}
