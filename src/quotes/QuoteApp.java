
package quotes;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*Authors:
 * Claire Cecil
 * David Guo
 * Alya Nowilaty
 */

class QuoteApp {

	// Static Variables
	private static Scanner scanner = new Scanner(System.in);
	static QuoteSaxParser parser;
	static QuoteList qList;
	static ArrayList<String> userSearches = new ArrayList<String>();
	static String path;

	// Main function to run I/O loop
	public static void main(String[] args) throws Exception {
		// User chooses location of "quotes.xml" as command-line argument
		parser = new QuoteSaxParser(args[0]); // Get path to xml file

		// Initialize a few necessary variables
		qList = parser.getQuoteList();
		String command = "none";
		path = args[0];
		boolean done = false;
		out.println("~~~~~~~~~~~~~Random Quote of the Day~~~~~~~~~~~~~~~");
		Quote welcomeQuote = qList.getRandomQuote();
		printQuote(welcomeQuote);
		// I/O loop to run until user quits program. Also check for valid input command.
		while (!done) {
			commandsMenu();
			// Print a carot for visibility of which line user input is typing on
			out.print(">");
			command = scanner.nextLine();
			if (command.equalsIgnoreCase("aq")) {
				// Add new quote to the xml file
				getNewQuote();
			} else if (command.equalsIgnoreCase("rq")) {
				// Get a random quote
				randomQuote();
			} else if (command.equalsIgnoreCase("rs")) {
				// Display recent searches
				recentSearches();
			} else if (command.equalsIgnoreCase("s")) {
				// Search quotes
				searchQuotes();
			} else if(command.equalsIgnoreCase("sk")) {
				// Search keyword
				searchKeyword();
			} else if (command.equalsIgnoreCase("q")) {
				// Write quotes to XML file and Quit
				writeXML();
				done = true;
			} else {
				out.println("Invalid command, try again.");
			}
		}
	}

	private static void commandsMenu() {
		out.println("                       ==================                       ");
		out.println("                       |    Main Menu   |                       ");
		out.println("                       ==================                       ");
		out.println("================================================================");
		out.println("|                   aq = Add a new Quote                       |");
		out.println("|                   rq = Get Random Quote                      |");
		out.println("|                   rs = Display Recent Searches               |");
		out.println("|                   s = Search Quotes                          |");
		out.println("|                   sk = Search Keyword                       |");
		out.println("|                   q = Quit Program                           |");
		out.println("================================================================\n\n");
	}

	private static void randomQuote() {
		out.println("                       ==================                                         ");
		out.println("                       |  Random Quote  |                                         ");
		out.println("                       ==================                                         ");
		out.println("================================================================================\n");
		Quote random = qList.getRandomQuote();
		printQuote(random);
	}

	private static void recentSearches() {
		out.println("                       ===================                                         ");
		out.println("                       | Recent Searches |                                         ");
		out.println("                       ===================                                         \n");
	        recentUserSearch();
	}

	// Helper function for recentSearches() that keeps logic to obtaining recent user searches
	private static void recentUserSearch() {
		out.println("=================");
		out.println("| User Searches |");
		out.println("=================");
		if(userSearches.isEmpty()) {
			out.println("\n****There are no recent user searches.****\n");
		} else {
			for(int i = 0; i < userSearches.size(); i ++) {
				out.println(i + 1 + ". " + userSearches.get(i));
			}
		}
		out.println("=================\n\n");
	}

	private static void searchQuotes() {
		out.println("                       ==================                                         ");
		out.println("                       |  Search Quotes |                                         ");
		out.println("                       ==================                                         ");
		out.println("================================================================================\n");
		out.println("Select a scope- (q)uote (a)uthor (b)oth: ");
		String inputScope = "none";
		int inputMode = 0;
		String inputString = "none";

		// Loop to check for valid input argument
		boolean valid = false;
		while( !valid ) {
			inputScope = scanner.nextLine();
			if (inputScope.equalsIgnoreCase("q")) {
				// Quotes only search
				inputMode = 1;
				valid = true;
			} else if (inputScope.equalsIgnoreCase("a")) {
				// Author only search
				valid = true;
			}  else if (inputScope.equalsIgnoreCase("b")) {
				// Search both quotes and authors
				inputMode = 2;
				valid = true;
			} else {
				out.println("Invalid command, try again.");
			}
		}
		out.println("\nType in search string: ");
		inputString = scanner.nextLine();

		// Update arraylists holding information about recent searches
		addUserSearch(inputString);

		QuoteList results = qList.search(inputString, inputMode);
		out.println("\nResults: \n");
		out.println("==================================================================================");
		if (results.getSize() == 0) {
			out.println("\n****There are no matching quotes.****\n");
			out.println("================================================================================\n");
		} else {
			for (int i = 0; i < results.getSize(); i++) {
				printQuote(results.getQuote(i));
			}
		}

	}

	private static void searchKeyword() {
		out.println("                       ==================                                         ");
		out.println("                       |  Search Keyword |                                         ");
		out.println("                       ==================                                         ");
		out.println("================================================================================\n");
		out.println("Please enter a keyword:");
		out.println("**Keyword must be one word, no whitespace**");
		out.println("**Keyword must NOT be longer than 44 characters**");
		out.println("**Keywords must *not* contain the characters <,>, slashes, or double quotes (single quotes are permitted)**");
		String keyword = "";

		// Loop to check for valid input argument
		boolean valid = false;
		while( !valid ) {
			keyword = scanner.nextLine();
			if (keyword.length() > 44) {
				out.println("Keyword too long. Please try again.");
			} else if (keyword.contains(" ")) {
				out.println("Keyword contains whitespace. Please try again.");
			} else if (keyword.contains("<") || keyword.contains(">") || keyword.contains("\\") || keyword.contains("/") || keyword.contains("\"")) {
				out.println("Keyword contains invalid characters. Please try again.");
			} else {
				valid = true;
			}
		}

		ArrayList<Quote> results = qList.searchKeyword(keyword);
		out.println("\nResults: \n");
		out.println("==================================================================================");
		if (results.size() == 0) {
			out.println("\n****There are no matching quotes.****\n");
			out.println("================================================================================\n");
		} else {
			for (int i = 0; i < results.size(); i++) {
				printQuote(results.get(i));
			}
		}
				
	
	}

	// Helper function to print out a quote in the same format as the web app
	private static void printQuote(Quote q) {
		out.println(q.getQuoteText());
		out.println("\t -" + q.getAuthor() + "\n");
		out.println("\t \t ================================================================================");
		out.println("\t \t KEYWORDS:");
		if(q.getKeyWords().size() == 0) {
			out.println("\t \t This quote has no keywords.");
		} else {
			out.println("\t \t" + q.getKeyWords().toString());
		}
		out.println("\t \t ================================================================================\n");
	}

	// Helper function to add to user search arraylist and maintain most recent 5 searches
	private static void addUserSearch(String s) {
		if (userSearches.size() == 5) {
			userSearches.remove(0);
		}
		userSearches.add(s);
	}

	private static void getNewQuote() {
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
		out.println("	-Keywords must *not* contain the characters <,>, slashes, or double quotes (single quotes are permitted)");
		out.println("	-Keywords must be one word that is less than 44 characters");
		out.println("	-Max of 5 keywords allowed per quote");
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
					out.println("Quote String aborted.\n");
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
					out.println("Author aborted.\n");
				}

			}
		}
		ArrayList<String> keyWords = new ArrayList<String>();
		valid = false;
		confirm = "";
		boolean done = false;
		while(!valid) {
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
				if (!confirm.equalsIgnoreCase("y") && !confirm.equals("yes")) { // any input that's not a yes will continue prompting for input
					out.println("Keyword aborted.\n");
					continue;
				} else
				{
					keyWords.add(keyword);
					if (keyWords.size() < 5)
					{
						out.println("Are you done entering keywords? (y)es or (n)o:");
						confirm = scanner.nextLine();
						if (confirm.equalsIgnoreCase("y") || confirm.equals("yes")) { // any input that's a yes will end the prompting
							break;
						}
					} else
					{
						break;
					}
				}
			}
		}
		out.println("\nYour quote has been successfully added.");
		out.println("\nThank you for adding to our quote library!");
		updateQList(quote, author, keyWords);
	}

	//Takes two strings and creates a new Quote object to add to qList
	private static void updateQList(String quoteText, String author, ArrayList<String> keywords) {
		Quote newQuote = new Quote(author, quoteText, keywords);
		qList.setQuote(newQuote);
	}

	// Writes quotes listed in a QuoteList type to an XML file
	private static void writeXML() throws Exception {

		BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(path));
		bufferWriter.write("<?xml version=\"1.0\"?>\n");
		bufferWriter.write("<quote-list>\n");

		for (int i = 0; i < qList.getSize(); i++){

			writeQuote(qList.getQuote(i), bufferWriter);
		}

		bufferWriter.write("</quote-list>");

		bufferWriter.close();
	}

	// Writes a single quote to the current BufferWriter stream
	private static void writeQuote(Quote quote, BufferedWriter bw) throws IOException {
		bw.write("\t<quote>\n");
		bw.write("\t\t<quote-text>" + quote.getQuoteText() + "</quote-text>\n");
		bw.write("\t\t<author>" + quote.getAuthor() + "</author>\n");
		bw.write("\t\t<keywords>" + quote.getKeyWords().toString() + "</keywords>\n");
		bw.write("\t</quote>\n");
	}
}
