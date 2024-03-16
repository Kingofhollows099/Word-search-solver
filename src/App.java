import java.util.ArrayList;
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

        ArrayList<Character> possibleWordStarts = new ArrayList<>();

        for (int currentWorkingWordIndex = 0; currentWorkingWordIndex < wordGoal; currentWorkingWordIndex++) {

            String currentWorkingWord = words[currentWorkingWordIndex];
            // char[] spelling = new char[currentWorkingWord.length()];

            // for (int characterNum = 0; characterNum < currentWorkingWord.length(); characterNum++) {
            //     spelling[characterNum] = currentWorkingWord.charAt(characterNum);
            // }
            
            for (int i = 0; i < map.length; i++) {
                if (map[i].getLetter() == currentWorkingWord.charAt(0)) {
                    possibleWordStarts.add(map[i]);
                }
            }
        }
    reader.close();
    }
}
