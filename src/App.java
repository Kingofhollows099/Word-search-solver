import java.util.ArrayList;
// import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);

        System.out.println("Please enter the # of Columns");
        int columns = Integer.parseInt(reader.nextLine());

        System.out.println("Please enter the # of Rows");
        int rows = Integer.parseInt(reader.nextLine());

        int totalChars = columns * rows;

        Character[] map = new Character[totalChars];

        int xLoc = 0;
        int yLoc = 0;


        System.out.println("Now please enter each letter from left to right, bottom up. (Type each indevidual char then enter)");
        for (int index = 0; index < totalChars; index++ ) { //Time to get the grid...
            char input = '!';

            System.out.println("(" + Integer.toString(xLoc + 1) + " , " + Integer.toString(yLoc + 1) + ")? ");
            
            while (true) {
                //gets input, removes (most) special characters, makes it lowercase, and converts it into a char type.
                input = reader.nextLine().replaceAll("[!@#%^&*()_=`~{;:'\\.>,</?\"'} ]", "").substring(0,1).toLowerCase().charAt(0);
                
                //checks if input is a letter
                if (!"abcdefghijklmnopqrstuvwxyz".contains(String.valueOf(input))) {
                    System.out.println("Thats not a valid character. Please try again. ");
                }
                else {
                    break;
                }
            }
            
            map[index] = new Character(xLoc, yLoc, input);

            xLoc += 1;

            if (xLoc == columns) { //makes x wrap to y once the last column in the row has been entered.
                yLoc += 1;
                xLoc = 0;
            }
        }
        //Alright, done with getting letters. Time to find words.

        System.out.println("How many words do you need to find?");
        int wordGoal = Integer.parseInt(reader.nextLine());
        
        String[] words = new String[wordGoal];

        for (int wordCount = 0; wordCount < wordGoal; wordCount++) {
            System.out.println("What is word #" + Integer.toString(wordCount + 1) + "? ");

            //TODO: make it actually ask you for input again if itput is invalid.

            words[wordCount] = reader.nextLine().replaceAll("[!@#%^&*()_=`~{;:'\\\\.>,</?\\\"'} 1234567890]", "").toLowerCase();
        }

        //Time to Find words :O

        Result[] results = new Result[wordGoal];

        ArrayList<Character> possibleWordStarts = new ArrayList<>();

        for (int currentWorkingWordIndex = 0; currentWorkingWordIndex < wordGoal; currentWorkingWordIndex++) { //"For each word"

            String currentWorkingWord = words[currentWorkingWordIndex];
            
            for (int scanPos = 0; scanPos < map.length; scanPos++) { // for each position in map[]
                if (map[scanPos].getLetter() == currentWorkingWord.charAt(0)) { //gets each Character with the starting letter of the currentWorkingWord.
                    possibleWordStarts.add(map[scanPos]);
                }
                
            }
            // Dimension 1 (Starting pos)
                // Dimension 2 (Direction)
                    // Dimension 3 (finish word)
            for (int i = 0; i < possibleWordStarts.size(); i++) { // for each possible starting position
                int Y = possibleWordStarts.get(i).getY();
                int X = possibleWordStarts.get(i).getX();
                String projectedWord = "";

                //TODO: Make it print out " <insert currentWorkingWord> is too long. please try again" if a word is too big to fit in the graph

                if (X >= currentWorkingWord.length() && Y >= currentWorkingWord.length()) { // Upper left check
                    for (int wordPosition = 0; wordPosition < currentWorkingWord.length(); wordPosition++) {
                        
                        
                        
                        projectedWord += "";
                    }
                }
                if (Y >= currentWorkingWord.length() - 1) { //Top middle check

                }
                if (Y >= currentWorkingWord.length() - 1 && X <= columns - currentWorkingWord.length() - 1) { // Upper right check

                }
                if (X >= currentWorkingWord.length() - 1) { // Left check

                }
                if (X <= columns - currentWorkingWord.length() - 1) {// Right check
                
                }
                if (X >= currentWorkingWord.length() - 1 && Y <= rows - currentWorkingWord.length() - 1) { // Lower left check

                }
                if (Y <= rows - currentWorkingWord.length() - 1) { // Bottom middle check

                }
                if (X <= columns - currentWorkingWord.length() - 1 && Y <= rows - currentWorkingWord.length() - 1) { // Lower right check

                }
            }
            possibleWordStarts.clear();
        }
    reader.close();
    }
}
