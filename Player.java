package tictactoe;

public class Player {
    String name;
    String sign;

    public Player(String name, String sign) {
        this.name = name;
        this.sign = sign;

    }
    public String getSign() {
        return sign;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String text) {
        this.name = text;
    }
}
