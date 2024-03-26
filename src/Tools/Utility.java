package Tools;
public class Utility {
    
    public static Character searchAtCoordinate(int targetX, int targetY, Character[] array) {
        Character result = null;
        for (int spot = 0; spot < array.length ; spot++ ) {
            if (array[spot].getX() == targetX || array[spot].getY() == targetY) {
                result = array[spot];
            }
        }
        return result;
    }
    public static boolean searchForWord(int startX, int startY, String word, int direction, Character[] array) {
        for (int whereAmI = 1; whereAmI <= word.length(); whereAmI++) {
            //remember, only one of these will run when the method is called.
            if (whereAmI == word.length()) {
                return true;
            }
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
    }
}

