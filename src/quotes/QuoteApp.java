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

	// Helper function to print out a quote in the same format as the web app
	private static void printQuote(Quote q) {
		out.println(q.getQuoteText());
		out.println("\t -" + q.getAuthor() + "\n");
		out.println("================================================================================\n");
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
		out.println("Please enter the quote (*without* the author):");
		quote = scanner.nextLine();
		out.println("\nHere's the quote you just submitted:\n  \"" + quote + "\"");
		out.println("\nNow, please enter the author of the quote.");
		author = scanner.nextLine();
		out.println("\nThe author you have entered is \"" + author + "\".");
		out.println("Thanks for adding to our quote library!\n\n");
		updateQList(quote, author);
	}

	private static void updateQList(String quoteText, String author) {
		Quote newQuote = new Quote(author, quoteText);
		qList.setQuote(newQuote);
	}

	// Writes quotes listed in a QuoteList type to an XML file
	private static void writeXML() throws Exception {

	   try {

			BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(path));
	   	bufferWriter.write("<?xml version=\"1.0\"?>\n");
	   	bufferWriter.write("<quote-list>\n");

	   	for (int i = 0; i < qList.getSize(); i++){

	      	writeQuote(qList.getQuote(i), bufferWriter);
	   	}

	   	bufferWriter.write("</quote-list>");

	   	bufferWriter.close();

		} catch (IOException e) {};
	}

	// Writes a single quote to the current BufferWriter stream
	private static void writeQuote(Quote quote, BufferedWriter bw) throws Exception {

		try {
	   	bw.write("\t<quote>\n");
	   	bw.write("\t\t<quote-text>" + quote.getQuoteText() + "</quote-text>\n");
	   	bw.write("\t\t<author>" + quote.getAuthor() + "</author>\n");
	   	bw.write("\t</quote>\n");

		} catch (IOException e) {};
	}


}
