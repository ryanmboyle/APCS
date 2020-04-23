
/**
 * Write a description of class ComputerPlayer here.
 *
 * @author Ryan Boyle
 * @version 3/6/20
 */
public class ComputerPlayer
{
    private GameBoard board;
    
    public ComputerPlayer (GameBoard gameBoard)
    {
        board = gameBoard;
    }
    
    /*
     * @pre 0 <= position < board.size()*board.size()
     * makes a move 0..board.size()*board.size()
     */ 
    public int takeTurnRandom ()
    {
        int compPos = (int)(Math.random()*8);
        while(board.isEmpty(compPos)==false)
        {
            compPos = (int)(Math.random()*8);
        }
        board.put(compPos, 'O');
        return compPos;
    }
}
