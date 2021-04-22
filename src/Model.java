//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;


/**
 * This file is to be completed by you.
 *
 * @author <s2093748>
 */
public final class Model {
    // ===========================================================================
    // ================================ CONSTANTS ================================
    // ===========================================================================
    // The most common version of Connect Four has 6 rows and 7 columns.
    public char player = 'X';


    boolean winner = false;
    boolean concede_now = false;
    boolean validPlay = true;
    public int numberToConnect;
    public int rows;
    public int columns;

    public  final int DEFAULT_NR_ROWS = 6;
    public  final int DEFAULT_NR_COLS = 7;
    // ========================================================================
    // ================================ FIELDS ================================
    // ========================================================================
    private int nrRows;
    private int nrCols;
    public char[][] board;


    public int x;


    // =============================================================================
    // ================================ CONSTRUCTOR ================================
    // =============================================================================
    public Model() {

        // Initialise the board size to its default values.
        nrRows = DEFAULT_NR_ROWS;
        nrCols = DEFAULT_NR_COLS;
        this. board =new char[nrRows][nrCols];
    }








    // ====================================================================================
    // ================================ MODEL INTERACTIONS ================================
    // ====================================================================================

    public void computer() {
        x = (int) (Math.random() * (board[0].length));
        validPlay = isMoveValid(x, board);
        while (!validPlay) {//If the column is full the computer reenters the number over again until an input where the column is not full is made
            x = (int) (Math.random() * (board[0].length));//Integer numbers are produced which are only in the column length
            validPlay = isMoveValid(x, board);//Changes the boolean of validPlay
        }



        for (int row = board.length - 1; row >= 0; row--) {//For loop is to stack the players characters on top of each other
            if (board[row][x] == ' ') {//This is so that the move wont be made on another character
                board[row][x] = player;// to implement the character into the grid
                break;
            }
        }

        winner = isWinnerVertical(player, board)|| isWinnerHorizontal(player,board)||isWinnerDownDiagonal(player,board)||isWinnerUpwardDiagonal(player,board);//After each move it will check if the move is a winner by checking all of the functions and breaks the loop in Controller if winner is true
    }

    public boolean isMoveValid(int move, char[][] board) {
        if (move != -1) {// This is the move if someone concedes
            if (move < 0 || move >= board[0].length || board[0][move] != ' ') {
                return false;// The move is only valid if the person didn't enter an integer out of bounds and if the column is not full else the person would have to reenter its move by the loop in controller
            }

        }
        return true;
    }


    public void makeMove(int move) {
        concede_now = concede(move);//If the player doesn't type 0 a move is placed
        validPlay = isMoveValid(move, board);//Only if move is valid the move will be placed on the grid

        if (move != -1) {
            for (int row = board.length - 1; row >= 0; row--) {//For loop is to stack the players characters on top of each other
                if (board[row][move] == ' ') {//This is so that the move wont be made on another character
                    board[row][move] = player;// to implement the character into the grid
                    break;
                }
            }
        }
        winner = isWinnerVertical(player, board)|| isWinnerHorizontal(player,board)||isWinnerDownDiagonal(player,board)||isWinnerUpwardDiagonal(player,board) ;//After each move it will check if winner is made and breaks the loop in controller if winner is true
    }
// Below is my attempt to load a game
    /*
    private boolean XTurn;
    private String XTurnStr;
    private char X;
    private char O;
    // Constructor. Takes in all variables that need to be saved
    Model(char X, char O, boolean redsTurn, char[][] board) {
        this.X = X;
        this.O = O;
        this.XTurn = redsTurn;
        this.board = board;
    }

    // Method to save the variables into file using buffered writer
    public void writeToFile() {
        // convert redsTurn to string as the writer only writes strings
        if (this.XTurn) {
            this.XTurnStr = "true";
        } else {
            this.XTurnStr= "false";
        }

        try { // buffered writer must be in an try-catch exception
            // instantiate a new BufferedWriter that creates and writes on the "savedGame.txt" file
            BufferedWriter saveGameBufferedWriter = new BufferedWriter(new FileWriter("javaConnect4SavedGame.txt"));
            // write into the file
            saveGameBufferedWriter.write(this.X);
            saveGameBufferedWriter.newLine();
            saveGameBufferedWriter.write(this.O);
            saveGameBufferedWriter.newLine();
            saveGameBufferedWriter.write(this.XTurnStr);
            saveGameBufferedWriter.newLine();

            // iterate through the 2D array
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    // if the position is not empty, write the colour of the token there
                     {
                        char newToken = this.board[r][c];

                        //saveGameBufferedWriter.write(newToken.stringOutput());
                        saveGameBufferedWriter.newLine();
                    }

                }
            } // end of array iteration

            // flush and close buffered writer
            saveGameBufferedWriter.flush();
            saveGameBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // end of writeToFile()
*/


    public boolean concede(int x) {
        if (x == -1) {//concede only works if the person inputs 0
            winner = true;//To break the loop in controller
            return true;
        } else {
            return false;
        }
    }

    public boolean isWinnerVertical(char player, char[][] board) {
        for (int col = 0; col < board[0].length; col++) {
            int colCount = 0;
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] == player) {//Row increments by 1 and column remains the same if same player in a row vertically colCount increments by 1
                    colCount++;
                } else {
                    colCount = 0;//else if another player comes and blocks the vertical same players colCount becomes 0
                }
                if (colCount >= numberToConnect) {//When colCount equals to the number to connect winner becomes true
                    return true;

                }
            }

        }return false;
    }
    public boolean isWinnerHorizontal(char player, char[][] board) {
        for (int row = 0; row < board.length; row++) {
            int rowCount = 0;
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == player) {//column increments and row remain the same and if its the same player in a row horizontally rowCount increases by 1
                    rowCount++;
                } else {

                    rowCount = 0;//else if another player comes and blocks the horizontal rowCount becomes 0
                }
                if (rowCount >= numberToConnect) {//Only when the increment equal to the number to connect winner becomes true
                    return true;
                }
            }
        }return false;
    }
    public boolean isWinnerDownDiagonal(char player,char[][]board) {
        int connect = numberToConnect;


        for (int i = 0; i + connect <= board.length; i++) {
            for (int j = 0; j + connect <= board[0].length; j++) {// Loop will stop depending on the number to connect

                boolean check = true;
                for (int k = 0; k < connect; k++) {
                    check = check && board[i + k][j + k] == player;// Loop the statement downward diagonally and must be the same player downward diagonally else if there is another character which blocks it check will be false
                }

                if (check) {
                    return true;
                }


            }
        }return false;
    }public boolean isWinnerUpwardDiagonal(char player,char[][]board){
        int connect = numberToConnect;
        for (int i = 0; i + connect <= board.length; i++) {
            for (int j = board[0].length - 1; j - connect + 1 >= 0; j--) {// Loop will stop depending on the number to connect
                boolean check = true;
                for (int k = 0; k < connect; k++)
                    check = check && board[i + k][j - k] == player;// Loop the statement upward diagonally and must be the same player upward diagonally else if there is another character which blocks it check will be false
                if (check)
                    return true;

            }
        }
        return false;
    }

    // =========================================================================
    // ================================ GETTERS ================================
    // =========================================================================
    public int getNrRows() {

        return nrRows;
    }




    public int getNrCols()
    {

        return nrCols;
    }
    // Tried to use getter and setter functions but I did not implement it
    /*
    public int getRows(int a){
        a=this.nrRows;
        return a;
    }
    public int getColumns(int b) {
        b=this.nrCols;
        return b;

    }
    public void setNumber(int num){
        this.nrRows=nrRows;
    }

    public void setNumberCol(int num){
        this.nrCols=nrCols;
    }
*/


}



