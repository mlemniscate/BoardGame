package ir.maktab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/*
 * This class for playing board game with play method. When you call play method you can play one set of the game.
 * */
public class BoardGame {
    private int rowNumbers, columnNumber;
    private final Player player1, player2;
    private Input input;
    private final String[][] board;
    private String turn, winner;
    private final int[] unAllowedValues;
    private int row, col;

    //    Constructor of BoardGame
    public BoardGame() {
        player1 = new Player("Hamed", 'X');
        player2 = new Player("Amir", 'O');
        turn = player1.getPlayerName();
        winner = "";
        setRowColumnNumbers();
        board = new String[rowNumbers][columnNumber];
        unAllowedValues = new int[columnNumber];
        Arrays.fill(unAllowedValues, -1);
        empty2dArray();
    }

    //    This method for playing game and only public method of this class that we can use for play board game.
    public void play() {
        int stepCounter = 0;
        while (true) {
            stepCounter++;
            int selectedColumn = getColumnNumber();
            setSignInBoard(selectedColumn);
            printBoard();
            boolean hasWinner = false;
            if (stepCounter >= 7) hasWinner = hasWinner();
            if (hasWinner) {
                winner = turn;
                break;
            } else if (stepCounter == rowNumbers * columnNumber) break;
            turn = turn.equals(player1.getPlayerName()) ?
                    player2.getPlayerName() : player1.getPlayerName();
        }
        printWinner();
    }

    //  Print winner of the game.
    private void printWinner() {
        if (!winner.equals(""))
            System.out.println(winner + " wins! 🏆");
        else
            System.out.println("Nobody wins the game, try again...");
    }

    //    Check all the ways that a player can win the game.
    private boolean hasWinner() {
        return (checkArrayWinner(board[row])) ||
                (hasColumnWinner()) ||
                (hasDiagonalWinner());
    }

    //    Check that board has a column winner or not.
    private boolean hasColumnWinner() {
        String[] columnArray = new String[columnNumber];
        for (int row = 0; row < board.length; row++) {
            columnArray[row] = board[row][col];
        }
        return checkArrayWinner(columnArray);
    }

    // Up to down diagonal search for creating an array and check it.
    private boolean hasDiagonalUpToDownWinner() {
        int rowIndex, colIndex, min;
        ArrayList<String> diagonalArray = new ArrayList<>();
        min = Math.min(row, col);
        rowIndex = row - min;
        colIndex = col - min;
        while (rowIndex < board.length && colIndex < board[0].length) {
            diagonalArray.add(board[rowIndex][colIndex]);
            rowIndex++;
            colIndex++;
        }
        String[] array = new String[diagonalArray.size()];
        return checkArrayWinner(diagonalArray.toArray(array));
    }

    // Down to up diagonal search for creating an array and check it.
    private boolean hasDiagonalDownToUpWinner() {
        int rowIndex, colIndex, min;
        ArrayList<String> diagonalArray = new ArrayList<>();
        min = Math.min(board.length - row - 1, col);
        rowIndex = row + min;
        colIndex = col - min;
        while (rowIndex >= 0 && colIndex < board[0].length) {
            diagonalArray.add(board[rowIndex][colIndex]);
            rowIndex--;
            colIndex++;
        }
        String[] array = new String[diagonalArray.size()];
        return checkArrayWinner(diagonalArray.toArray(array));
    }

    private boolean hasDiagonalWinner() {
        return hasDiagonalDownToUpWinner() || hasDiagonalUpToDownWinner();
    }

    // Check a string that it has a possibility of winning or not. When string has 4 sequence of equal signs return true.
    private boolean checkStringWinner(String input) {
        char sign = turn.equals(player1.getPlayerName()) ?
                player1.getPlayerSign() : player2.getPlayerSign();
        String pattern = String.format("%s{4}", sign);
        return Pattern.compile(pattern).matcher(input).find();
    }

    // Check a 1D array that it has a possibility of winning or not. When array has 4 sequence of equal signs return true.
    private boolean checkArrayWinner(String[] board) {
        String array = "";
        for (String s : board) {
            array += s;
        }
        return checkStringWinner(array);
    }

    // Print board of the game.
    private void printBoard() {
        System.out.println(player1.getPlayerName() + " : " + player1.getPlayerSign());
        System.out.println(player2.getPlayerName() + " : " + player2.getPlayerSign());
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                if (board[row][column].equals("")) System.out.format("|%3s", "");
                else System.out.format("| %s ", board[row][column]);
            }
            System.out.println("|");
        }
    }

    // Set the sign of the player into the board
    private void setSignInBoard(int selectedColumn) {
        char sign = turn.equals(player1.getPlayerName()) ?
                player1.getPlayerSign() : player2.getPlayerSign();
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][selectedColumn - 1].equals("")) {
                board[row][selectedColumn - 1] = "" + sign;
                // Set row and col to know about our location
                this.row = row;
                this.col = selectedColumn - 1;
                // If our column is full, we want to put that column number in unAllowedValues
                if (row == 0) unAllowedValues[columnNumber - 1] = selectedColumn;
                break;
            }
        }
    }

    // Get column number that player want to put his/her sign in that column
    private int getColumnNumber() {
        String message = turn.equals(player1.getPlayerName()) ?
                player1.getPlayerName() : player2.getPlayerName();
        input = new Input(message + " turn\nEnter column number: ",
                "Your board has " + columnNumber + " columns. Maybe your column is full.\nPlease set a valid number!",
                columnNumber,
                0,
                unAllowedValues);
        return input.getInputFromConsole();
    }

    // Get rowNumbers and columnNumber from user by using Input class
    private void setRowColumnNumbers() {
        input = new Input("Enter number of rows(at least 5): ", Integer.MAX_VALUE, 5, unAllowedValues);
        rowNumbers = input.getInputFromConsole();
        input.setInputMessage("Enter number of columns(at least 5): ");
        columnNumber = input.getInputFromConsole();
    }

    // Empty our board array that all the values equal to "" empty String
    private void empty2dArray() {
        for (String[] strings : board) {
            Arrays.fill(strings, "");
        }
    }
}
