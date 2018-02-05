package quotes;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

class QuoteApp {
    
        private static Scanner scanner = new Scanner(System.in);
        static QuoteSaxParser parser;
	static QuoteList qList;
	static ArrayList<String> userSearches = new ArrayList<String>();
	static ArrayList<String> communitySearches = new ArrayList<String>();
	static String path;

	
    public static void main(String[] args) { 
        parser = new QuoteSaxParser(args[0]);
        qList = parser.getQuoteList();
    	String command = "none";
        boolean done = false;
        while (!done) {
		commandsMenu();
        	out.print(">");
        	command = scanner.nextLine();
        	if (command.equalsIgnoreCase("rq")) {
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
	out.println("\n\n\n\n\n");
	out.println("                       ==================                                         ");
	out.println("                       |  Random Quote  |                                         ");
	out.println("                       ==================                                         ");
    	out.println("================================================================================\n");
    	Quote random = qList.getRandomQuote();
    	printQuote(random);
    	out.println("\n================================================================================\n");
    }
    
    private static void recentSearches() {
	out.println("                       ===================                                         ");
	out.println("                       | Recent Searches |                                         ");
	out.println("                       ===================                                         \n");
    	out.print("Do you want to display recent (u)ser searches or (c)ommunity searches? (u or c): ");
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
    }
    
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
    
    private static void recentCommSearch() {
	out.println("======================");
	out.println("| Community Searches |");
	out.println("======================");
	if(userSearches.isEmpty()) {
		out.println("\n****There are no recent community searches.****\n");
	} else {
    		for(int i = 0; i < communitySearches.size(); i ++) {
    			out.println(i + 1 + ". " + communitySearches.get(i));
    		}
	}
	out.println("======================\n\n");
    }
    
    private static void searchQuotes() {
    	out.println("=========================================================");
    	out.println("Select a scope- (q)uote (a)uthor (b)oth: ");
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
    	out.println("\nType in search string: ");
    	inputString = scanner.nextLine();
    	addUserSearch(inputString);
    	addCommSearch(inputString);
    	QuoteList results = qList.search(inputString, inputMode);
    	out.println("\nResults: \n");
	out.println("==================================================================================");
    	for (int i = 0; i < results.getSize(); i++) {
    		printQuote(results.getQuote(i));
    	}
    	
    }
    
    private static void printQuote(Quote q) {
    	out.println(q.getQuoteText());
    	out.println("\t -" + q.getAuthor() + "\n");
	out.println("==================================================================================\n");
    }
    
    private static void addUserSearch(String s) {
    	if (userSearches.size() == 5) {
    		userSearches.remove(0);
    	}
    	userSearches.add(s);
    }
    
    private static void addCommSearch(String s) {
    	if (communitySearches.size() == 5) {
    		communitySearches.remove(0);
    	}
    	communitySearches.add(s);
    }
    
}
