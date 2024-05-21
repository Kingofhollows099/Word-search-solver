package Tools;
import java.util.Scanner;

public class Utility {
    Scanner reader = new Scanner(System.in);
    
    /*
     * Returns the CoordedChar at the X and Y position specified
     */
    
    public static CoordedChar searchAtCoordinate(int targetX, int targetY, CoordedChar[] array) {
        for (int spot = 0; spot < array.length ; spot++ ) {
            if (array[spot].getX() == targetX && array[spot].getY() == targetY) { // if the X value of map[index] is targetX and the Y value of map[index] is targetY:
                return array[spot];
            }
        }
        return null; // return null if the CoordedChar was for some reason unable to be found.
    }

/*
 * Searches through the input CoordedChar array for a word in the specified directon. 
 * Direction is decided by an integer 0-7. See layout below
 * 0 1 2
 * 7 w 3
 * 6 5 4
 */
    public static boolean searchForWord(int startX, int startY, String word, int direction, CoordedChar[] array) {
        for (int whereAmI = 1; whereAmI < word.length(); whereAmI++) {
            switch (direction) {
                default:
                return false;
            
                case 0: //Up left check
                    if (word.charAt(whereAmI) == searchAtCoordinate(startX - whereAmI, startY - whereAmI, array).getLetter()) {
                        continue;
                    }
                    else {
                        return false;
                    }
                
                case 1:  //Up check
                    if (word.charAt(whereAmI) == searchAtCoordinate(startX, startY - whereAmI, array).getLetter()) {
                        continue;
                    }
                    else {
                        return false;
                    }
                
                case 2: //Up right check
                    if (word.charAt(whereAmI) == searchAtCoordinate(startX + whereAmI, startY - whereAmI, array).getLetter()) {
                        continue;
                    }
                    else {
                        return false;
                    }
                
                case 3: //Right check
                    if (word.charAt(whereAmI) == searchAtCoordinate(startX + whereAmI, startY, array).getLetter()) {
                        continue;
                    }
                    else {
                        return false;
                    }
                
                case 4: //Down right check
                    if (word.charAt(whereAmI) == searchAtCoordinate(startX + whereAmI, startY + whereAmI, array).getLetter()) {
                        continue;
                    }
                    else {
                        return false;
                    }
                case 5: //Down check
                    if (word.charAt(whereAmI) == searchAtCoordinate(startX, startY + whereAmI, array).getLetter()) {
                        continue;
                    }
                    else {
                        return false;
                    }
                case 6: //Down left check
                    if (word.charAt(whereAmI) == searchAtCoordinate(startX - whereAmI, startY + whereAmI, array).getLetter()) {
                        continue;
                    }
                    else {
                        return false;
                    }
                case 7: //Left check
                    if (word.charAt(whereAmI) == searchAtCoordinate(startX - whereAmI, startY, array).getLetter()) {
                        continue;
                    }
                    else {
                        return false;
                    }
            }
        }
        return true;
    }

 /*
  * Check if input is _____
  */

    public static boolean checkIfIsNum (String input) { // A number
        if (input == "") {
            return false;
        }
        for (int letter = 0; letter < input.length(); letter++) {
            if(!Character.isDigit(input.charAt(letter))) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfIsLetter (char input) {// A letter (input char)
        if ("abcdefghijklmnopqrstuvwxyz".contains(String.valueOf(input))) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean checkIfIsLetter (String input) {// A letter (input string)
        if (input == "" || input.length() > 1) {
            return false;
        }
        else if ("abcdefghijklmnopqrstuvwxyz".contains(input)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean checkIfIsWord (String input) {// A valid word composed only of letters
        for (int index = 0; index < input.length(); index++) {
            if(!Utility.checkIfIsLetter(input.charAt(index))) {
                return false;
            }
        }
        return true;
    }
    public static String recieveEnsuredWord(int maxX, int maxY, Scanner reader, String[] words) {// A valid word composed only of letters
        String output = "";
        
        do {
            if (!Utility.checkIfIsWord(output)) {
                System.out.println("You input something that had invalid characters in it. Please try again.");
            }
            else if (!(output.length() <= maxX || output.length() <= maxY)) {
                System.out.println("You input a word that was too long for the grid. Please try again.");
            }
            output = UserInterrupts.interruptCheck(reader.nextLine());
        } while (!Utility.checkIfIsWord(output) && !(output.length() <= maxX || output.length() <= maxY));
        return output;
    }

/*
 * Recieve an input from the user that is guarenteed to be _____
 */
    public static char recieveEnsuredLetter (Scanner reader) { // A letter. This does not need words, as wrods have not yet been added.
        boolean ran = false;
        String output = "";
        do {
            if (ran == true) {
                System.out.println("You input something that was not a letter. Please try again.");
            }
            output = UserInterrupts.interruptCheck(reader.nextLine()).toLowerCase();
            ran = true;
        } while (!Utility.checkIfIsLetter(output));
        return output.charAt(0);
    }
    public static int recieveEnsuredNumber(Scanner reader) {// A number. This does not need words, as wrods have not yet been added.
        boolean ran = false;
        String output = "";
        do {
            if (ran == true && !(output == "<interrupted>")) {
                System.out.println("You input something that was not a number. Please try again.");
            }
            output = UserInterrupts.interruptCheck(reader.nextLine());
            ran = true;
        } while (!Utility.checkIfIsNum(output));
        return Integer.parseInt(output);
    }

    public static String recieveEnsureWord (Scanner reader) { // A valid word (slim version) (for help tool)
        String output = "";
        do {
            if (!Utility.checkIfIsWord(output)) {
                System.out.println("You input something that had invalid characters in it. Please try again.");
            }
            output = UserInterrupts.interruptCheck(reader.nextLine());
        } while (!Utility.checkIfIsWord(output));
        return "hello"; //TODO: this is placeholder
    }

    /*
     * Miscellaneous
     */

    public static String capitalizeString(String input) { // Capitalizes the first letter of a string
        char array[] = input.toCharArray();
        array[0] = String.valueOf(array[0]).toUpperCase().charAt(0);
        String output = String.valueOf(array);
        
        return output;
    }
}
