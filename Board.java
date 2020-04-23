
/**
 * interface Board specifies all methods needed
 * to interact with a 2D structure in a generalized
 * manner.
 * 
 * implementations of this class should provide
 * a constructor that is passed the dimension for the
 * 2D array.
 * 
 * Note:  the design of this interface stems from the
 * class discussion leadup to this assignment and is
 * a result of the design decisions made in that planning
 * session.
 * 
 * In addition you may want to add methods to your 
 * implementing class that are particular
 * to a Tic Tac Toe board including
 *      // return a list of available move positions
 *      public List<Integer> getAvailableMoves ()
 *      // return true if the target character forms a WIN in Tic Tac Toe
 *      public boolean check(char target)
 *      // other methods if you determine they are needed   
 *
 * @author J. Smith
 * @version February 2020
 */
public interface Board
{
    
    /*
     * @pre rows == cols, the structure must be square
     * @return the dimension of the board, # rows or #cols
     */
    public int size ();
    
    /*
     * set all elements of the board to a default value
     */
    public void clear ();
    
    /*
     * @return true if the input symbol is valid on this board
     *  false otherwise
     */    
    public boolean isValidSymbol (char symbol);
    
    /*
     * @return true if position is within the grid, false otherwise
     */
    public boolean isValidPos (int position);
    
    /*
     *  put replaces the character in position
     *  with the new symbol
     *  @param position 0 <= position < size()*size()
     *  @param symbol must be valid on this board
     *  @return -1 if position is invalid, 
     *      otherwise returns position
     *  @throws IllegalArgumentException is symbol is not valid
     */
    public int put (int position, char symbol);
    
    /*
     * @return Return a String format of the 2D board
     * with spaces between symbols and returns
     * at the end of rows.
     */
    public String toString ();
    
    /*
     * @param 0 <= position < size()*size()
     * @return true if the symbol at position is EMPTY, false otherwise
     * @throw IllegalArgumentException if position is invalid
     */
    public boolean isEmpty (int position);
    
    /*
     * @return a copy of this board
     */
    public Board copy ();
    
    /**
     * override the Object equals method
     * to compare this board to
     * the other board symbol for symbol
     * 
     * It is necessary to cast other to Board using 
     * this line of code -- put this as the first line
     * in your program.
     * 
     * E otherboard = (E)other; 
     * 
     * Where E is the name of your board implementation
     * 
     */
    @Override
    public boolean equals (Object other);
   
}
