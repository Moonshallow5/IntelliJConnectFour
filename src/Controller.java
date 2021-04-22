
/**
 * This file is to be completed by you.
 *
 * @author <s2093748>
 */
public final class Controller {
    private final Model model;
    private final TextView view;


    public Controller(Model model, TextView view) {
        this.model = model;
        this.view = view;
    }
    public void startSession() {
        int start=view.start();
        if(start==1){

            firstOption();
        }
        else if (start == 2) {
            secondOption();
        } else if (start== 3) {
            thirdOption();
        } else if (start == 4) {
            fourthOption();
        }else if(start==5){
            view.gameOver();
        }else if(start==6){
            view.rules();
            startSession();
        }
        else {
            view.enterInRange();
            startSession();
        }
    }
    public void thirdOption(){
        view.displayNewGameMessage();
        int turn=1;
        model.numberToConnect=4;
        model.board=new char[model.DEFAULT_NR_ROWS][model.DEFAULT_NR_COLS];
        initialShow();//Displays the initial board and a message if a person wants to concede
        while (!model.winner && turn <= 42 && !model.concede_now) {//42 is the total whole number of spaces in board
            move();//Player input will be in board and the loop will ask input for the whole board
            turn++;
        }
        end();//Prints the winner or tie game after game finished
        again();//Allows player to play again
    }
    public void fourthOption(){
        view.displayNewGameMessage();
        int turn = 1;
        model.numberToConnect=4;
        model.board = new char[model.DEFAULT_NR_ROWS][model.DEFAULT_NR_COLS];
        initialShow();//Displays the initial board and a message if a person wants to concede
        while (!model.winner && turn <= (42) && !model.concede_now) {//42 is the total number of spaces in board
            computerMove();//Computer makes a move and the move is always valid and move is implemented in the board
            turn++;
            if (model.winner || turn > (42)) {// Added this if statement as the computer can also win thus after move by computer if winner is achieved breaks the main while loop
                view.switchPlayers(model);
                break;
            }

            view.switchPlayers(model);
            move();//Player input will be in board and the loop will ask input for the whole board
            turn++;
        }
        end();//Prints the winner or tie game after game finished
        again();//Allows player to play again
    }
    public void secondOption(){
        view.displayNewGameMessage();
        int turn = 1;
        model.rows = view.displayCustomRows();
        model.columns = view.displayCustomColumns();
        model.numberToConnect = view.howManyConnect();
        dimensions();//Makes sure model.rows,columns and numberToConnect is always valid
        model.board = new char[model.rows][model.columns];
        initialShow();//Displays the initial board and a message if a person wants to concede
        while (!model.winner && turn <= (model.rows * model.columns ) && !model.concede_now) {//model.rows*model.columns is the total number of spaces in the board
            computerMove();//Computer makes a move and the move is always valid and move implemented into the board
            turn++;
            if (model.winner || turn > (model.rows * model.columns)) {// Added this if statement as the computer can also win thus after move by computer if winner is achieved breaks the main while loop
                view.switchPlayers(model);
                break;
            }
            view.switchPlayers(model);
            move();//Player input will be in board and the loop will ask input for the whole board
            turn++;
        }
        end();//Prints the winner or tie game after game finished
        again();//Allows player to play again
    }
    public void computerMove(){
        model.computer();// this allows the computer to pick a random number and output in the grid and due to the loop in function computer() it outputs the character vertically in the respective column
        view.displayBoard(model);//Displays the empty state of the board
        view.displayNumbers(model);
        view.moves(model);

    }
    public void firstOption(){
        view.displayNewGameMessage();
        int turn = 1;
        model.rows = view.displayCustomRows();
        model.columns = view.displayCustomColumns();
        model.numberToConnect = view.howManyConnect();
        dimensions();//Makes sure model.rows,columns and numberToConnect is always valid
        model.board = new char[model.rows][model.columns];
        initialShow();//Displays the initial board and a message if a person wants to concede
        while (!model.winner && turn <= model.rows * model.columns && !model.concede_now) {//model.rows*model.columns is the total number of spaces in the board
            move();//Player input will be in board and the loop will ask input for the whole board
            turn++;
        }
        end();//Prints the winner or tie game after game finished
        again();//Allows player to play again
    }
    public void dimensions(){
        while (model.numberToConnect > Math.max(model.rows, model.columns) || (model.rows <= 1 || model.columns <= 1 || model.numberToConnect <= 1)||model.columns>30|| model.rows>30) {// The number to connect is always <= maximum number between row and column for there to be a winner
            view.tryAgain();                                                             // I made column size and row size to be <=30 because the numbers being produced below the board trough the displayNumbers function must be the same length of column size to be easily read and my rows is only until 30 because the terminal in vscode cannot scroll to the right so the formatting of the table will look extremely ugly in vscode and 30 is a big enough number
            //I had to make number of rows,columns and number to connect to be more than 1 because if not a winner is produced on the first move
            model.rows = view.displayCustomRows();
            model.columns = view.displayCustomColumns();
            model.numberToConnect = view.howManyConnect();
        }

    }
    public void end(){
        view.winner(model);
        view.concede(model);
        view.playAgain();
    }
    public void initialShow(){
        view.initialDisplay(model);//Produce all the blank spaces for the character to be inputted later
        view.concede();
        view.displayBoard(model);//Displays the empty state of the board
        view.displayNumbers(model);
    }

    public void again(){
        boolean playAgain;
        char z = view.readChar();
        if (z == 'Y' || z == 'y') {
            playAgain = true;
        } else {
            playAgain = false;
            view.gameOver();

        }

        while (playAgain) {

            model.winner = false;
            model.player = 'X';
            model.concede_now = false;//Set all values to default
            startSession();// Call the whole function to start again
            playAgain = false;// This is for the player to be allowed to stop the game if needed in the next turn
        }

    }


    public void move(){
        view.makeAMove(model);
        int move = view.askForMove() - 1;
        boolean validPlay = model.isMoveValid(move, model.board);
        while (!validPlay ) {
            view.enterProperly();
            view.makeAMove(model);
            move = view.askForMove() - 1;//Move is re-entered until the move is valid thus it is inside the columns and column is not full
            validPlay = model.isMoveValid(move, model.board);//This allows the boolean of validPlay to then changed
        }
        model.makeMove(move);//Updates the board from the input given by user
        view.switchPlayers(model);
        view.displayBoard(model);// Prints the updated board
        view.displayNumbers(model);// Prints the numbers below the board
    }

}

