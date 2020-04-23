
/**
 * Write a description of class Player here.
 *
 * @author Ryan Boyle
 * @version 3/4/20
 */
public class HumanPlayer implements PlayerInterface
{
    private GameBoard board;
    
    public HumanPlayer (GameBoard gameBoard)
    {
        board = gameBoard;
    }
    
    /*
     * @pre 0 <= position < board.size()*board.size()
     * makes a move 0..board.size()*board.size()
     */ 
    public void takeTurn (int position)
    {
        if (position>=0 && position<(board.size()*board.size()))
        {
            board.put(position, 'X');
        }
    }
}
