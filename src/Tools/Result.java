package Tools;
public class Result {
    String position1;
    String position2;
    String word;

    public String getPosition1() {
        return this.position1;
    }

    public void setPosition1(String position1) {
        this.position1 = position1;
    }

    public String getPosition2() {
        return this.position2;
    }

    public void setPosition2(String position2) {
        this.position2 = position2;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Result(String position1, String position2, String word) {
        this.position1 = position1;
        this.position2 = position2;
        this.word = word;
    }
}
