
/**
 * Write a description of class GameBoard here.
 *
 * @author Ryan Boyle
 * @version 3/2/20
 */
public class GameBoard implements Board
{
    private char[][] board;
    
    public GameBoard(int size)
    {
        board = new char[size][size];
        for (int r=0; r<size(); r++)
        {
            for (int c=0; c<size(); c++)
            {
                board[r][c] = '-';
            }
        }
    }
    
    /*
     * @pre rows == cols, the structure must be square
     * @return the dimension of the board, # rows or #cols
     */
    public int size ()
    {
        return board.length;
    }
    
    /*
     * set all elements of the board to a default value
     */
    public void clear ()
    {
        for (int r=0; r<size(); r++)
        {
            for (int c=0; c<size(); c++)
            {
                board[r][c] = '-';
            }
        }
    }
    
    /*
     * @return true if the input symbol is valid on this board
     *  false otherwise
     */    
    public boolean isValidSymbol (char symbol)
    {
        
        if(symbol=='X' || symbol=='O' || symbol=='-')
            return true;
        else
            return false;
    }
    
    /*
     * @return true if position is within the grid, false otherwise
     */
    public boolean isValidPos (int position)
    {
        if(position <= (size())*(size())-1 && position >= 0)
        {
            return true;
        }
        else
            return false;
    }
    
    /*
     *  put replaces the character in position
     *  with the new symbol
     *  @param position 0 <= position < size()*size()
     *  @param symbol must be valid on this board
     *  @return -1 if position is invalid, 
     *      otherwise returns position
     *  @throws IllegalArgumentException is symbol is not valid
     */
    public int put (int position, char symbol)
    {
        if (isValidSymbol(symbol)==false)
        {
            throw new IllegalArgumentException("invalid symbol");
        }
        else if (isValidPos(position)==false)
            return -1;
        else
        {
            int row = posToRow(position);
            int col = posToCol(position);
            board[row][col] = symbol;
            return position;
        }
    }
   
    
    /*
     * @return Return a String format of the 2D board
     * with spaces between symbols and returns
     * at the end of rows.
     */
    public String toString ()
    {
        String fullBoard="";
        for (int r=0; r<size(); r++)
        {
            for (int c=0; c<size(); c++)
            {
                fullBoard += (board[r][c]+" ");
            }
            fullBoard += "\n";
        }
        //System.out.println(fullBoard);
        return fullBoard;
    }
    
    /*
     * @param 0 <= position < size()*size()
     * @return true if the symbol at position is EMPTY, false otherwise
     * @throw IllegalArgumentException if position is invalid
     */
    public boolean isEmpty (int position)
    {
        if (isValidPos(position)==false)
        {
            throw new IllegalArgumentException("invalid position");
        }
        else if(board[posToRow(position)][posToCol(position)]=='-')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isFull()
    {
        for (int r=0; r<size(); r++)
        {
            for (int c=0; c<size(); c++)
            {
                if (board[r][c] == '-')
                    return false;
            }
        }
        return true;
    }
    
    /*
     * @return a copy of this board
     */
    public GameBoard copy ()
    {
        GameBoard boardCopy = new GameBoard(board.length);
        for (int r=0; r<size(); r++)
        {
            for (int c=0; c<size(); c++)
            {
                boardCopy.put(rowColToPos(r, c), board[r][c]);
            }
        }
        
        return boardCopy;
    }
    
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
    public boolean equals (Object other)
    {
        GameBoard otherboard = (GameBoard)other;
        for (int r=0; r<size(); r++)
        {
            for (int c=0; c<size(); c++)
            {
                if (otherboard.board[r][c]!=board[r][c])
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    /*
     * posToRow
     * @param int 0 <= pos < BOARD_SIZE*BOARD_SIZE
     * @return the row the position is located on
     */
    private int posToRow (int pos)
    {
        int row = pos/board.length;

        return row;
    }

    /*
     * posToCol
     * @param int 0 <= pos < BOARD_SIZE*BOARD_SIZE
     * @return the column the position is located on
     */
    private int posToCol (int pos)
    {
        int col = pos%board.length;

        return col;
    }

    /*
     * @return the position 0..8 given a (row, col) pair
     */
    private int rowColToPos (int row, int col)
    {
        int pos = row*board.length + col;
        return pos;
    }
    
    
    public void main ()
    {
        //put(0, 'X');
        toString();
        GameBoard num2 = copy();
        num2.put(0, 'X');
        num2.toString();
        System.out.println(equals(num2));
    }
    
    public boolean win(char symbol)
    {
        //diagonal starting top left
        
        int counter = 0;
        for (int r=1; r<size(); r++)
        {
            if (board[r][r]==symbol && board[r-1][r-1]==symbol)
            {
                counter++;
            }
            
            if(counter==2)
            {
                //System.out.println("win diagonal");
                return true;
            }
        }
        
        //diagonal starting bottom right
        counter = 0;
        for (int r=size()-2; r>=0; r--)
        {
            if (board[r][r]==symbol && board[r+1][r+1]==symbol)
            {
                counter++;
            }
            
            if(counter==2)
            {
                //System.out.println("win diagonal");
                return true;
            }
        }
        
        //horizontal
        counter = 0;
        for (int r=0; r<size(); r++)
        {
            for (int c=1; c<size(); c++)
            {
                if (board[r][c]==symbol && board[r][c-1]==symbol)
                {
                    counter++;
                }
                
                if(counter==2)
                {
                    //System.out.println("win horizontal");
                    return true;
                }
            }
            counter=0;
        }
        
        //vertical
        counter=0;
        for (int c=0; c<size(); c++)
        {
            for (int r=1; r<size(); r++)
            {
                if (board[r][c]==symbol && board[r-1][c]==symbol)
                {
                    counter++;
                }
                
                if(counter==2)
                {
                    //System.out.println("win vertical");
                    return true;
                }
            }
            counter=0;
        }
        return false;
    }
    
    //in progress
    public String analyzeWin(char symbol)
    {
        //diagonal starting top left
        
        int counter = 0;
        for (int r=1; r<size(); r++)
        {
            if (board[r][r]==symbol && board[r-1][r-1]==symbol)
            {
                counter++;
            }
            
            if(counter==2)
            {
                //System.out.println("win diagonal");
                return "digonaltopleft";
            }
        }
        
        //diagonal starting bottom right
        counter = 0;
        for (int r=size()-2; r>=0; r--)
        {
            if (board[r][r]==symbol && board[r+1][r+1]==symbol)
            {
                counter++;
            }
            
            if(counter==2)
            {
                //System.out.println("win diagonal");
                return "digonaltopright";
            }
        }
        
        //horizontal
        counter = 0;
        for (int r=0; r<size(); r++)
        {
            for (int c=1; c<size(); c++)
            {
                if (board[r][c]==symbol && board[r][c-1]==symbol)
                {
                    counter++;
                }
                
                if(counter==2)
                {
                    //System.out.println("win horizontal");
                    return r+"horizontal";
                }
            }
            counter=0;
        }
        
        //vertical
        counter=0;
        for (int c=0; c<size(); c++)
        {
            for (int r=1; r<size(); r++)
            {
                if (board[r][c]==symbol && board[r-1][c]==symbol)
                {
                    counter++;
                }
                
                if(counter==2)
                {
                    //System.out.println("win vertical");
                    return c+"vertical";
                }
            }
            counter=0;
        }
        return "";
    }
}
