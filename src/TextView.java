

/**
 * This file is to be completed by you.
 *
 * @author <s2093748>
 */
public final class TextView {




    public TextView() {
    }
    public final void displayNewGameMessage() {

        System.out.println("---- NEW GAME STARTED ----");
    }


    public final int displayCustomRows(){
        System.out.println("Please enter how many rows:");
        return InputUtil.readIntFromUser();
    }
    public final int displayCustomColumns(){
        System.out.println("Please enter how many columns:");

        return InputUtil.readIntFromUser();
    }
    public final char readChar(){
        return InputUtil.readCharFromUser();
    }
    public void moves(Model model){
        System.out.println("X moves to "+(model.x+1));
    }// Prints out move made by the computer





    public final void displayBoard(Model model) {
        String rowDivider = "-".repeat(4 * model.board[0].length + 1);
        System.out.println(rowDivider);

        for (int row = 0; row < model.board.length; row++) {
            System.out.print("| ");// Prints out vertical lines for the first column
            for (int col = 0; col < model.board[0].length; col++) {
                System.out.print(model.board[row][col]);// Prints out the whole 2D array for the characters to be placed in
                System.out.print(" | ");// Prints out the rest of the board with vertical lines
            }
            System.out.println();
            System.out.println(rowDivider);
        }

    }
    public final void initialDisplay(Model model){
        for (int row = 0; row < model.board.length; row++){//Loop for all the rows in the grid
            for (int col = 0; col < model.board[0].length; col++){//Loop for all of the columns in the grid
                model.board[row][col] = ' ';//The whole grid will be represented as empty at first
            }
        }
    }

    public void switchPlayers(Model model){
        if (model.player == 'X'){
            model.player = 'O';//
        }else{
            model.player = 'X';
        }
    }
    public final void displayNumbers(Model model) {
        int i = 1;
        while(i<=model.board[0].length) {//Only breaks the loop when the integer is more than the column of the board length
            int x = i;
            String format = "%1$03d";//Formats the integer to always have length 3 and adds 0 in front if the length is less than 3
            String result =" "+ String.format(format, x);//Add a space first to align the numbers right below the column space and numbers will not be joined
            System.out.print(result);
            i++;
        }//Prints all the numbers right below each column of the grid

        System.out.println();
    }

    public void rules(){
        System.out.println("Connect 4 is a game to be played with 2 players and players will be switched after each turn and each player picks a number which indicates the point to place their checker in the board and the \ntraditional way is to connect all of the same character in any direction 4 times in a row in a board of 6 by 7");
    }
    public void enterInRange(){
        System.out.println("Please enter an integer between 1 to 6");
    }




    public final int askForMove() {
        System.out.print("Select a free column: ");

        return InputUtil.readIntFromUser();
    }
    public  void winner(Model model){
        if (model.winner) {//The function will be called in the Controller but only if isWinner function in Model==true it will print who is the Winner
            if (model.player == 'X') {
                System.out.println("O won");
            } else if(model.player=='O') {
                System.out.println("X won");
            }
        } else if(!model.concede_now  ) {// Since the function will be called after the while loop in Controller and if isWinner function==false in Model it would print tie game
            System.out.println("Tie game");
        }
    }
    public void enterProperly(){
        System.out.println("Please enter a number within the range and make sure the column is not full");
    }
    public int howManyConnect(){
        System.out.println("Please enter a number of how many you want to connect to win");
        return InputUtil.readIntFromUser();
    }public int start(){
        System.out.println("Connect Four");
        System.out.println("What would you like to do?");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("1-Start new game Player vs Player and choose your own dimension of board and number to connect");
        System.out.println("2-Start new game Player vs Computer and choose your own dimension of board and number to connect");
        System.out.println("3-Start new game Player vs Player with default values");
        System.out.println("4-Start new game Player vs Computer with default values");
        System.out.println("5-Exit");
        System.out.println("6-Rules");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.print("Number: ");
        return InputUtil.readIntFromUser();

    }
    public void makeAMove(Model model){
        System.out.print(model.player + " ");
    }
    public void tryAgain(){
        System.out.println("Try again and make sure your rows,column and number to connect more than 1\nYour column number and row number is less than or equal to 30\nAnd your number to connect is less than or equal to the highest number between row and column");
    }
    public void gameOver(){
        System.out.println("Game Over");
    }
    public  void playAgain(){
        System.out.println("Would you like to play again?");
        System.out.println("Enter Y to play again or N to stop playing");
    }
    public void concede(){
        System.out.println("Enter 0 to concede");
    }
    public  void concede(Model model){
        if(model.concede_now){ //When user inputs 0
            if (model.player == 'X') {
                System.out.println("O concedes, X won");

            } else {
                System.out.println("X concedes, O won");

            }
        }
    }
}



