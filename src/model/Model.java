package model;

import view.View;

public class Model {
    // Set a static global variable to change the board size
    public static int BOARD_SIZE = 3;

    private View v;
    private int playerId;
    private int movesCount;
    private char[][] board;
    private String message;

    // default constructor
    public Model() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.movesCount = BOARD_SIZE * BOARD_SIZE;
        this.playerId = 1;
    }

    // initializing the reference of view class
    public void registerView(View v) {
        this.v = v;
    }

    //setters and getters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // function to update the board model
    public void PlayMove(int x, int y) {

        if(getMovesCount() > 0){
            // mark the board with x or o depending on playerId
            if(playerId%2 != 0)
                board[x][y] = 'X';
            else
                board[x][y] = 'O';

            // reduce the count of moves left
            setMovesCount(--movesCount);

            // check if playerId has won or if game is draw,
            // and send message accordingly to view, also update the view
            if(isWinner(x, y)) {
                setMessage("Player " + playerId + " is Winner!");
                v.isWinner(x, y, board[x][y], getMessage());
            }
            else if(getMovesCount()==0) {
                setMessage("No Winner! Game ended in a Draw");
                v.isWinner(x, y, board[x][y], getMessage());
            }
            else {
                if(playerId%2 != 0) {
                    // toggle the playerId
                    setPlayerId(2);
                    setMessage("'O':  Player " +getPlayerId());
                }
                else {
                    setPlayerId(1);
                    setMessage("'X':  Player " +getPlayerId());

                }
                // update the board with message for next player
                v.update(x, y, board[x][y], getMessage());
            }
        }
    }

    // function to check if there is a winner
    public boolean isWinner(int x, int y) {
        int countRow = 0;
        int countCol = 0;
        int countLDiag = 0;
        int countRDiag = 0;
        char symbol;
        if(getPlayerId() %2 !=0)
            symbol = 'X';
        else
            symbol = 'O';

        for(int i=0; i<board.length;i++) {
            if(board[x][i] == symbol)
                countRow++;
            if(board[i][y] == symbol)
                countCol++;
            if(board[i][i] == symbol)
                countRDiag++;
            if(board[board.length-1-i][i] == symbol)
                countLDiag++;
        }

        if(countCol==board.length || countRow==board.length
                || countLDiag == board.length || countRDiag == board.length)
            return true;

        return false;
    }

    // function to clear the model and reset it to initial state
    public void ResetModel() {
        movesCount = BOARD_SIZE * BOARD_SIZE;
        setPlayerId(1);
        setMessage("");
        for(int i=0; i<board.length;i++) {
            for(int j=0; j<board.length;j++) {
                board[i][j] = '\0';
            }
        }
        v.resetGame();
    }
}