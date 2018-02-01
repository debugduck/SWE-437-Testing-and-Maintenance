package quotes;

import java.util.Scanner;
import static java.lang.System.out;
import java.util.ArrayList;

class QuoteApp {

    private static Scanner scanner = new Scanner( System.in );
    private static QuoteSaxParser qParser = new QuoteSaxParser ("C:/Users/anowi/Documents/GitHub/SWE-437-Testing-and-Maintenance/quotes/quotes.xml");
    private static QuoteList quoteList = qParser.getQuoteList();

    public static void main(String[] args) {
        commandsMenu();
    	String command = "none";
        boolean done = false;
        while (!done) {
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

    // Editing required
    private static void randomQuote() {
      Quote quoteTmp = quoteList.getRandomQuote();
    	out.println("~~~~~~~~~~~~~~~~~RANDOM QUOTE~~~~~~~~~~~~~~~~~");
    	out.println("\"" + quoteTmp.getQuoteText() + "\"");
      out.println(" - " + quoteTmp.getAuthor());
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
    	out.println("1. This");
    	out.println("2. is");
    	out.println("3. just");
    	out.println("4. a");
    	out.println("5. Mockup");
    }

    private static void recentCommSearch() {
    	out.println("1. This");
    	out.println("2. is");
    	out.println("3. just");
    	out.println("4. a");
    	out.println("5. Mockup");
    }

    private static void searchQuotes() {
    	out.println("~~~~~~~~~~~~~~~~~SEARCH QUOTES~~~~~~~~~~~~~~~~~");
    	out.println("Select a scope- quote (q) author (a) both (b): ");
    	String inputScope = "none";
    	String inputString = "none";
    	boolean valid = false;
    	while( !valid ) {
    		inputScope = scanner.nextLine();
        	if (inputScope.equalsIgnoreCase("q")) {
        		valid = true;
        	} else if (inputScope.equalsIgnoreCase("a")) {
        		valid = true;
        	}  else if (inputScope.equalsIgnoreCase("b")) {
        		valid = true;
        	} else {
        		out.println("Invalid command, try again.");
        	}
    	}
    	out.println("Type in search string: ");
    	inputString = scanner.nextLine();
    	out.println(">>>Now we do some search in scope " + inputScope + " with string: " + inputString);
    	out.println("Results: ");
    	out.println("1. This");
    	out.println("2. is");
    	out.println("3. just");
    	out.println("4. a");
    	out.println("5. Mockup");

    	out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}
