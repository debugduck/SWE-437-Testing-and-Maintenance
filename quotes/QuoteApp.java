package quotes;
import java.util.Scanner;
import static java.lang.System.out;

class QuoteApp {


    private static ArrayList<String> userSearches = new ArrayList<>();
    private static ArrayList<String> communitySearches = new ArrayList<>();
    private static Scanner scanner = new Scanner( System.in );
    private static QuoteSaxParser parser = new QuoteSaxParser("quotes.xml");
	 private static QuoteList qList = parser.getQuoteList();

    public static void main(String[] args) {

        commandsMenu();
    	String command = "none";
        boolean done = false;
        while (!done) {
        	out.println(">");
        	command = scanner.nextLine();
        	if (command.equalsIgnoreCase("c")) {
        		commandsMenu();
        	} else if (command.equalsIgnoreCase("rq")) {
        		randomQuote();
        	} else if (command.equalsIgnoreCase("rs")) {
        		recentSearches();
        	} else if (command.equalsIgnoreCase("s")) {
        		searchQuotes();
        	} else if (command.equalsIgnoreCase("q")) {
        		done = true;
        	} else {
        		out.println("Invalid command, try again.");
        	}
        }
    }

    private static void commandsMenu() {
    	out.println("~~~~~~~~~~~~~~~~~COMMANDS~~~~~~~~~~~~~~~~~");
    	out.println("c = Display Commands List");
    	out.println("rq = Get Random Quote");
    	out.println("rs = Display Recent Searches");
    	out.println("s = Search Quotes");
    	out.println("q = Quit Program");
    	out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void randomQuote() {
     Quote quote = qList.getRandomQuote();
     out.println("~~~~~~~~~~~~~~~~~RANDOM QUOTE~~~~~~~~~~~~~~~~~");
     printQuote(quote);
     out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
   }

    private static void recentSearches() {
    	out.println("~~~~~~~~~~~~~~~~~RECENT SEARCHES~~~~~~~~~~~~~~~~~");
    	out.print("Do you want to display recent user searches or community searches? (u or c): ");
    	String input = "none";
    	boolean valid = false;
    	while( !valid ) {
        	input = scanner.nextLine();
        	if (input.equalsIgnoreCase("u")) {
        		valid = true;
        		recentUserSearch();
        	} else if (input.equalsIgnoreCase("c")) {
        		valid = true;
        		recentCommSearch();
        	} else {
        		out.println("Invalid command, try again.");
        	}
    	}
    	out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void recentUserSearch() {
	userSearches.add("henlo");
	userSearches.add("mr lizer");
	for(int i = 0; i < userSearches.size(); i++) {
		out.println(i + 1 + ". " + userSearches.get(i));
	}
    }

    private static void recentCommSearch() {
	communitySearches.add("henlo");
	communitySearches.add("mr lizer");
	for(int i = 0; i < communitySearches.size(); i++) {
		out.println(i + 1 + ". " + communitySearches.get(i));
	}
    }

    private static void searchQuotes() {
    	out.println("~~~~~~~~~~~~~~~~~SEARCH QUOTES~~~~~~~~~~~~~~~~~");
    	out.println("Select a scope- quote (q) author (a) both (b): ");
    	String inputScope = "none";
    	int inputMode = 0;
    	String inputString = "none";
    	boolean valid = false;
    	while( !valid ) {
    		inputScope = scanner.nextLine();
        	if (inputScope.equalsIgnoreCase("q")) {
        		inputMode = 1;
        		valid = true;
        	} else if (inputScope.equalsIgnoreCase("a")) {
        		valid = true;
        	}  else if (inputScope.equalsIgnoreCase("b")) {
        		inputMode = 2;
        		valid = true;
        	} else {
        		out.println("Invalid command, try again.");
        	}
    	}
    	out.println("Type in search string: ");
    	inputString = scanner.nextLine();

    	QuoteList results = qList.search(inputString, inputMode);
    	out.println("Results: ");
    	for (int i = 0; i < results.getSize(); i++) {
    		printQuote(results.getQuote(i));
    	}
    	out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void printQuote(Quote q) {
      out.println("\"" + q.getQuoteText() + "\"");
      out.println(" - " + q.getAuthor());
    }
}
