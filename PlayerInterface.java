
/**
 * interface PlayerInterface
 *
 * @author J. Smith
 * @version May 2018, March 2020
 */
public interface PlayerInterface
{
    /*
     * @pre 0 <= position < board.size()*board.size()
     * makes a move 0..board.size()*board.size()
     */ 
    void takeTurn (int position);  
}
