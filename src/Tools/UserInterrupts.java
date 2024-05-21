package Tools;

public class UserInterrupts {
    // Checks if the user inputs any inturrupts
    public static String interruptCheck (String input) { 
        
        if (input == "<inturrupted>") { //in case they input the output of this
            return "1!a"; // Guaranteed to fail
        }
        else if (input == "/help") {
            help();
            return "<inturrupted>";
        }
        else {
            return input;
        }
    }
    // Help page
    public static void help() {
        System.out.println(
        "You have asked for help. Chose one of the followsing options: \n " +
        "Grid: For an explanation on how the grid works \n" + 
        "Change: For an explanation on how to change letters and words");
        
    }
}