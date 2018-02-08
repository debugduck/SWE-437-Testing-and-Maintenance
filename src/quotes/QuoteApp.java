package quotes;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

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
	public static void main(String[] args) { 
		// User chooses location of "quotes.xml" as command-line argument
		parser = new QuoteSaxParser(args[0]);

		// Initialize a few necessary variables
		qList = parser.getQuoteList();
		String command = "none";
		boolean done = false;

		// I/O loop to run until user quits program. Also check for valid input command.
		while (!done) {
			commandsMenu();      	
			// Print a carot for visibility of which line user input is typing on
			out.print(">");      	
			command = scanner.nextLine();
			if (command.equalsIgnoreCase("rq")) {
				// Get a random quote
				randomQuote();
			} else if (command.equalsIgnoreCase("rs")) {
				// Display recent searches
				recentSearches();
			} else if (command.equalsIgnoreCase("s")) {
				// Search quotes
				searchQuotes();
			} else if (command.equalsIgnoreCase("q")) {
				// Quit
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

	private static void updateQList(String quoteText, String author) {
		Quote newQuote = new Quote(author, quoteText);
		qList.setQuote(newQuote);
	}

}
