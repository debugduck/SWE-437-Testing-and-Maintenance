package quotes;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Scanner;

public class KeyWordsTester {

	public QuoteList qList;
	private static Scanner scanner = new Scanner(System.in);
	
	public void getNewQuote() {
		out.println("                       ==================                                         ");
		out.println("                       |   Add a Quote  |                                         ");
		out.println("                       ==================                                         ");
		out.println("================================================================================\n");
		String quote = "";
		String author = "";
		out.println("***RULES FOR QUOTES***");
		out.println("	-Quote must be a minimum of 3 characters and a maximum of 1000 characters");
		out.println("	-Quote/Author must *not* contain the characters <,>, slashes, or double quotes (single quotes are permitted)");
		out.println("	-Author must be a minimum of 3 characters and a maximum of 200 characters\n");
		boolean valid = false;
		String confirm = "";
		while(!valid) {
			out.println("Please enter the quote (WITHOUT the author):");
			quote = scanner.nextLine();
			if(quote.length() < 3 || quote.length() > 1000) {
				out.println("Quote is either less than 3 characters or more than 1000 characters. Please try again.");
			} else if(quote.contains("<") || quote.contains(">") || quote.contains("\\") || quote.contains("/") || quote.contains("\"")) {
				out.println("Quote should not contain< , > , / , \\ , or \"");
			} else {
				valid = true;
				out.println("\nHere's the quote you've entered:\n  \"" + quote + "\"");			
				out.println("Please confirm that the above quote is correct (y)es or (n)o:");
				confirm = scanner.nextLine();
				if (!confirm.equalsIgnoreCase("y") && !confirm.equals("yes")) { // Any input that's not a yes or y will continue prompting for input
					valid = false;
					out.println("Quote aborted.\n");
				}
			}
		}

		valid = false;
		confirm = "";
		while(!valid) {
			out.println("Please enter the author:");
			author = scanner.nextLine();
			if(author.length() < 3 || author.length() > 200) {
				out.println("Author is either less than 3 characters or more than 200 characters. Please try again.");
			} else if(author.contains("<") || author.contains(">") || author.contains("\\") || author.contains("/") || author.contains("\"")) {
				out.println("Author should not contain < , > , / , \\ , or \"");
			} else {
				valid = true;
				out.println("\nHere's the author you've entered:\n  \"" + author + "\"");			
				out.println("Please confirm that the above author is correct (y)es or (n)o:");
				confirm = scanner.nextLine();
				if (!confirm.equalsIgnoreCase("y") && !confirm.equals("yes")) { // any input that's not a yes will continue prompting for input
					valid = false;
					out.println("Quote aborted.\n");
				}

			}
		}
		ArrayList<String> keyWords = new ArrayList<String>();
		boolean notValid = false;
		confirm = "";
		boolean done = false;
		while(!notValid && !done) {
			out.println("Please enter a keyword:");
			String keyword;
			keyword = scanner.nextLine();
			if(keyword.length() > 44) {
				out.println("Keyword given is more than 44 characters. Please try again.");
			} else if(keyword.contains(" ") || keyword.contains("<") || keyword.contains(">") || keyword.contains("\\") || keyword.contains("/") || keyword.contains("\"")) {
				out.println("Keyword should be single word and should not contain < , > , / , \\ , or \"");
			} else {
				out.println("\nHere's the keyword you've entered:\n  \"" + keyword + "\"");			
				out.println("Please confirm that the above keyword is correct (y)es or (n)o:");
				confirm = scanner.nextLine();
				if (confirm.equalsIgnoreCase("n") || confirm.equals("no")) { // any input that is a no with end prompting
					notValid = true;
					out.println("Quote aborted.\n");
				} else
				{
					keyWords.add(keyword);
					if (keyWords.size() < 5)
					{
						out.println("Are you done entering keywords? (y)es or (n)o:");
						confirm = scanner.nextLine();
						if (confirm.equalsIgnoreCase("y") || confirm.equals("yes")) { // any input that's a yes will end the prompting
							done = true;
						}
					} else
					{
						done = true;
					}
				}
			}
		}
		
		out.println("\nYour quote has been successfully added.");
		out.println("\nThank you for adding to our quote library!");
		updateQList(quote, author, keyWords);
	}
	
	//Takes two strings and creates a new Quote object to add to qList
	private void updateQList(String quoteText, String author, ArrayList<String> keyWords) {
		Quote newQuote = new Quote(author, quoteText,keyWords);
		this.qList.setQuote(newQuote);
	}
	
}
