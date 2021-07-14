package ir.maktab;

/*
* Notice: playerName have to be less then 20 characters otherwise its value is null.
*/
public class Player {
    private String playerName;
    private final char playerSign;

    public Player(String playerName, char playerSign) {
        setPlayerName(playerName);
        this.playerSign = playerSign;
    }

    public String getPlayerName() {
        return playerName;
    }

    // Player name must have less then 20 character for setting value to it.
    private void setPlayerName(String playerName) {
        if(playerName.length() <= 20)
            this.playerName = playerName;
    }

    public char getPlayerSign() {
        return playerSign;
    }
}
