package Tools;
public class Character{
    char letter;

    public char getLetter() {
        return this.letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
    
    int x, y;

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Character(int x,int y, char letter) {
        this.x = x;
        this.y = y;
        this.letter = letter;
    }
}