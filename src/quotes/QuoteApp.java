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
    
	//Static Variables
    private static Scanner scanner = new Scanner(System.in);
    static QuoteSaxParser parser;
	static QuoteList qList;
	static ArrayList<String> userSearches = new ArrayList<String>();
	static ArrayList<String> communitySearches = new ArrayList<String>();
	static String path;

	//main function to run I/O loop
    public static void main(String[] args) { 
        //User chooses location of "quotes.xml" as command-line argument
    	parser = new QuoteSaxParser(args[0]);
    	
    	//Initialize a few necessary variables
        qList = parser.getQuoteList();
    	String command = "none";
        boolean done = false;
        
        //I/O loop to run until user quits program. Also check for valid input command.
        commandsMenu();
        while (!done) {
        	
        	//Print a carot for visibility of which line user input is typing on
        	out.print(">");
        	
        	command = scanner.nextLine();
        	if (command.equalsIgnoreCase("c")) {
        		//Display command list
        		commandsMenu();
        	} else if (command.equalsIgnoreCase("rq")) {
        		//Get a random quote
        		randomQuote();
        	} else if (command.equalsIgnoreCase("rs")) {
        		//Display recent searches
        		recentSearches();
        	} else if (command.equalsIgnoreCase("s")) {
        		//Search quotes
        		searchQuotes();
        	} else if (command.equalsIgnoreCase("q")) {
        		//Quit
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
    	out.println("~~~~~~~~~~~~~~~~~RANDOM QUOTE~~~~~~~~~~~~~~~~~");
    	Quote random = qList.getRandomQuote();
    	printQuote(random);
    	
    	out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    private static void recentSearches() {
    	out.println("~~~~~~~~~~~~~~~~~RECENT SEARCHES~~~~~~~~~~~~~~~~~");
    	out.print("Do you want to display recent user searches or community searches? (u or c): ");
    	String input = "none";
    	
    	//Loop to ensure valid input command
    	boolean valid = false;
    	while( !valid ) {
        	input = scanner.nextLine();
        	if (input.equalsIgnoreCase("u")) {
        		//User Searchees
        		valid = true;
        		recentUserSearch();
        	} else if (input.equalsIgnoreCase("c")) {
        		//Community Searchese
        		valid = true;
        		recentCommSearch();
        	} else {
        		out.println("Invalid command, try again.");
        	}
    	}
    	out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    //helper function for recentSearches() that keeps logic to obtaining recent user searches
    private static void recentUserSearch() {
    	out.println("User Searches: ");
    	for(int i = 0; i < userSearches.size(); i ++) {
    		out.println(i + 1 + ". " + userSearches.get(i));
    	}
    }
    
    //helper function for recentSearches() that keeps logic to obtaining recent community searches
    private static void recentCommSearch() {
    	out.println("Community Searches: ");
    	for(int i = 0; i < communitySearches.size(); i ++) {
    		out.println(i + 1 + ". " + communitySearches.get(i));
    	}
    }
    
    private static void searchQuotes() {
    	out.println("~~~~~~~~~~~~~~~~~SEARCH QUOTES~~~~~~~~~~~~~~~~~");
    	out.println("Select a scope- quote (q) author (a) both (b): ");
    	
    	String inputScope = "none";
    	int inputMode = 0;
    	String inputString = "none";
    	
    	//Loop to check for valid input argument
    	boolean valid = false;
    	while( !valid ) {
    		inputScope = scanner.nextLine();
        	if (inputScope.equalsIgnoreCase("q")) {
        		//Quotes only search
        		inputMode = 1;
        		valid = true;
        	} else if (inputScope.equalsIgnoreCase("a")) {
        		//Author only search
        		valid = true;
        	}  else if (inputScope.equalsIgnoreCase("b")) {
        		//Search both quotes and authors
        		inputMode = 2;
        		valid = true;
        	} else {
        		out.println("Invalid command, try again.");
        	}
    	}
    	
    	out.println("Type in search string: ");
    	inputString = scanner.nextLine();
    	
    	//Update arraylists holding information about recent searches
    	addUserSearch(inputString);
    	addCommSearch(inputString);
    	
    	QuoteList results = qList.search(inputString, inputMode);
    	out.println("Results: ");
    	for (int i = 0; i < results.getSize(); i++) {
    		printQuote(results.getQuote(i));
    	}
    	
    	out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    //Helper function to print out a quote in the same format as the web app
    private static void printQuote(Quote q) {
    	out.println(q.getQuoteText());
    	out.println("\t -" + q.getAuthor());
    }
    
    //Helper function to add to user search arraylist and maintain most recent 5 searches
    private static void addUserSearch(String s) {
    	if (userSearches.size() == 5) {
    		userSearches.remove(0);
    	}
    	userSearches.add(s);
    }

    //Helper function to add to community search arraylist and maintain most recent 5 searches
    private static void addCommSearch(String s) {
    	if (communitySearches.size() == 5) {
    		communitySearches.remove(0);
    	}
    	communitySearches.add(s);
    }
    
}
