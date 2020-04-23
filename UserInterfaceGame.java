
/**
 * class UserInterfaceGame is an example of a start that
 * uses the Parser, CommandWord, Commands structure
 * for a text based game.
 * 
 * Handout for APCS 2020
 * To be modified by students
 *
 * @author J. Smith
 * @version February 2020
 */
public class UserInterfaceGame
{
    private Parser parser;
    private HumanPlayer human;
    private ComputerPlayer comp;
    private SmartComputerPlayer smartComp;
    private GameBoard board;
    
    /**
     * Constructor for objects of class TicTacToeGame
     */
    public UserInterfaceGame()
    {
        parser = new Parser();
        board = new GameBoard(3);
        human = new HumanPlayer(board);
        comp = new ComputerPlayer(board);
        smartComp = new SmartComputerPlayer(board);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        
        while (true) {
            Command command = parser.getCommand();
            //System.out.println("command " + command.getCommandWord());

            boolean turnStatus = processCommand(command);
            if (!turnStatus) // bad info entered
            {
                printError();
                
            }

            else
            {
                break;
            }
            

        }
        gameOver();

    }

    /**
     * Start a new game
     */
    private void freshStart ()
    {
        GameBoard copy = board.copy();
        smartComp.remember(copy);
        board.clear();
        play();
    }

    private void gameOver()
    {
        System.out.println("Thank you for playing TicTacToe");

    }
    
    private void printError ()
    {
        System.out.println("Hmm... try again.");
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("You are playing TicTacToe");
        System.out.println("You should never win, but first");
        System.out.println("you must play LOTS of games against the computer");
        System.out.println("to train it to play well.");
        
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        boolean turnState = false;
        String commandWord = command.getCommandWord();
        if (commandWord.equals("quit")) {
            turnState = quit(command);
        }
        else if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("move")) {
            turnState = move (command);
        }
        return turnState;
    }

    private void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()||command.hasThirdWord()) {
            System.out.println("Quit what?");
            return false;
            
        }
        else {
            return true; 
            // signal that we want to quit
            
        }
    }

    /** 
     * "Move" was entered. 
     * make the move for the player
     * @return true if the player moved correctly, false if error
     * 
     */
    private boolean move(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Move command requires a position number 0..8");
            return false;
        }
        else {
            int position = Integer.parseInt(command.getSecondWord());
            if (board.isEmpty(position)==false)
            {
                System.out.println("That position is taken");
                return false;
            }
            System.out.println("Player is moving to position " + position);
            human.takeTurn(position);
            System.out.println(board);
            //win? tie?
            if(board.win('X'))
            {
                System.out.println("Game over, you won!");
                //return true;
                freshStart();
            }
            else if (board.isFull() && !board.win('X'))
            {
                System.out.println("Game over, it was a tie");
                //return true;
                freshStart();
            }
            
            //in progress
            
            int compPos=smartComp.takeTurn(position);
            System.out.println("Computer is moving to position " + compPos);
            System.out.println(board);
            //win? tie?
            if(board.win('O'))
            {
                System.out.println("Game over, you lost :(");
                //return true;
                freshStart();
            }
            else if (board.isFull() && !board.win('O'))
            {
                System.out.println("Game over, it was a tie");
                //return true;
                freshStart();
            }
            //return true;
            return false;
        }
    }
    
}
