package ir.maktab;

public class Main {
    public static void main(String[] args) {
        do {
            BoardGame boardGame = new BoardGame();
            boardGame.play();
        } while (restartGame() != 2);
    }
    //    For restart the game get an input from user and return 2 for exit and return 1 for play again.
    public static int restartGame() {
        Input input = new Input(
                "Do you want to play again?\nEnter 1 for play again.\nEnter 2 for exit.\n",
                2,
                1,
                null);
        return input.getInputFromConsole();
    }
}


