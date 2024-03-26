//TODO: add an explanation on how the grid works.
//TODO: Add checkpoints along the grid
//TODO: Allow one to change words
//TODO: Add word # markers when it prints out.
//TODO: Add ability to use an image???

import java.util.ArrayList;
import java.util.Scanner;

import Tools.CoordedChar;
import Tools.Result;
import Tools.Utility;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);


        System.out.println("Please enter the # of Columns");
        int columns = Utility.recieveEnsuredNumber(reader);

        System.out.println("Please enter the # of Rows");
        int rows = Utility.recieveEnsuredNumber(reader);

        int totalChars = columns * rows;

        CoordedChar[] map = new CoordedChar[totalChars];

        int xLoc = 0;
        int yLoc = 0;

        //Time to get the grid...
        System.out.println("Now please enter each letter from left to right, bottom up. (Type each individual char then enter)");
        for (int index = 0; index < totalChars; index++ ) { 

            System.out.println("(" + Integer.toString(xLoc + 1) + " , " + Integer.toString(yLoc + 1) + ")? ");
            
            map[index] = new CoordedChar(xLoc, yLoc, Utility.recieveEnsuredLetter(reader));

            xLoc += 1;

            if (xLoc == columns) { //makes x wrap to y once the last column in the row has been entered.
                yLoc += 1;
                xLoc = 0;
            }
        }
        //Alright, done with getting letters. Time to find words.

        System.out.println("How many words do you need to find?");
        int wordGoal = Utility.recieveEnsuredNumber(reader);
        
        String[] words = new String[wordGoal];

        for (int wordCount = 0; wordCount < wordGoal; wordCount++) {
            System.out.println("What is word #" + Integer.toString(wordCount + 1) + "? ");

            words[wordCount] = Utility.recieveEnsuredWord(columns, rows, reader);
        }

        //Time to Find words :O

        Result[] results = new Result[wordGoal];

        ArrayList<CoordedChar> possibleWordStarts = new ArrayList<>();

        for (int currentWorkingWordIndex = 0; currentWorkingWordIndex < wordGoal; currentWorkingWordIndex++) { //"For each word"

            String currentWorkingWord = words[currentWorkingWordIndex];
            
            for (int scanPos = 0; scanPos < map.length; scanPos++) { // for each position in map[]
                if (map[scanPos].getLetter() == currentWorkingWord.charAt(0)) { //gets each CoordedChar with the starting letter of the currentWorkingWord.
                    possibleWordStarts.add(map[scanPos]);
                }
                
            }
            // Dimension 1 (Starting pos)
                // Dimension 2 (Direction)
                    // Dimension 3 (finish word)
            for (int i = 0; i < possibleWordStarts.size(); i++) { // for each possible starting position
                int Y = possibleWordStarts.get(i).getY();
                int X = possibleWordStarts.get(i).getX();
                String strX = Integer.toString(X + 1);
                String strY = Integer.toString(Y + 1);
                String strXM1 = Integer.toString(X);
                String strYM1 = Integer.toString(Y);
                String strXP1 = Integer.toString(X + 2);
                String strYP1 = Integer.toString(Y + 2);

                if (X + 1 >= currentWorkingWord.length() && Y + 1 >= currentWorkingWord.length()) { // Upper left check
                    if (Utility.searchForWord(X, Y, currentWorkingWord, 0, map)) {
                        results[currentWorkingWordIndex] = new Result("(" + strX + ", " + strY + ")", "(" + strXM1 + ", " +  strYM1 + ")", currentWorkingWord);
                    }
                }
                if (Y + 1 >= currentWorkingWord.length()) { //Top middle check
                    if (Utility.searchForWord(X, Y, currentWorkingWord, 1, map)) {
                        results[currentWorkingWordIndex] = new Result("(" + strX + ", " + strY + ")", "(" + strX + ", " +  strYM1 + ")", currentWorkingWord);
                    }
                }
                if (Y + 1 >= currentWorkingWord.length() && X <= columns - currentWorkingWord.length()) { // Upper right check
                    if (Utility.searchForWord(X, Y, currentWorkingWord, 2, map)) {
                        results[currentWorkingWordIndex] = new Result("(" + strX + ", " + strY + ")", "(" + strXP1 + ", " +  strYM1 + ")", currentWorkingWord);
                    }
                }
                if (X + 1 >= currentWorkingWord.length()) { // Left check
                    if (Utility.searchForWord(X, Y, currentWorkingWord, 7, map)) {
                        results[currentWorkingWordIndex] = new Result("(" + strX + ", " + strY + ")", "(" + strXM1 + ", " +  strY + ")", currentWorkingWord);
                    }
                }
                if (X <= columns - currentWorkingWord.length()) {// Right check
                    if (Utility.searchForWord(X, Y, currentWorkingWord, 3, map)) {
                        results[currentWorkingWordIndex] = new Result("(" + strX + ", " + strY + ")", "(" + strXP1 + ", " +  strY + ")", currentWorkingWord);
                    }
                }
                if (X + 1 >= currentWorkingWord.length() && Y <= rows - currentWorkingWord.length()) { // Lower left check
                    if (Utility.searchForWord(X, Y, currentWorkingWord, 6, map)) {
                        results[currentWorkingWordIndex] = new Result("(" + strX + ", " + strY + ")", "(" + strXM1 + ", " +  strYP1 + ")", currentWorkingWord);
                    }
                }
                if (Y <= rows - currentWorkingWord.length()) { // Bottom middle check
                    if (Utility.searchForWord(X, Y, currentWorkingWord, 5, map)) {
                        results[currentWorkingWordIndex] = new Result("(" + strX + ", " + strY + ")", "(" + strX + ", " +  strYP1 + ")", currentWorkingWord);
                    }
                }
                if (X <= columns - currentWorkingWord.length() && Y <= rows - currentWorkingWord.length()) { // Lower right check
                    if (Utility.searchForWord(X, Y, currentWorkingWord, 4, map)) {
                        results[currentWorkingWordIndex] = new Result("(" + strX + ", " + strY + ")", "(" + strXP1+ ", " +  strYP1 + ")", currentWorkingWord);
                    }
                }
            }
            possibleWordStarts.clear();
        }
        //finally, time to print results.
        System.out.println("\n\u001B[46m~~~ Results ~~~ \u001B[0m");
        for (int numOfResults = 0; numOfResults < results.length; numOfResults++) {
            if (results[numOfResults] == null) {
            System.out.println("Sorry, we couldent find \u001B[31m" + words[numOfResults] + "\u001B[37m in the grid.");
            }
            else {
            System.out.println("\u001B[32m" + Utility.capitalizeString(results[numOfResults].getWord()) + "\u001B[37m starts at \u001B[36m" + results[numOfResults].getPosition1() + "\u001B[37m and goes toward \u001B[36m" + results[numOfResults].getPosition2() + "\u001B[37m.");
            }
        }  
    }  
}