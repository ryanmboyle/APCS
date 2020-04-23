import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class SmartComputerPlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartComputerPlayer
{
    private GameBoard board;
    private ArrayList<GameBoard> memory;
    
    public SmartComputerPlayer (GameBoard gameBoard)
    {
        board = gameBoard;
        memory = new ArrayList<GameBoard> ();
    }
    
    //in progress
    public int takeTurn (int humanMove)
    {
        if (memory.size()>=1)
        {
            int pos = checkMemory(humanMove);
            return pos;
        }
        else
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
    
    public void remember(GameBoard pastBoard)
    {
        memory.add(pastBoard);
        System.out.println("memory: ");
        for (int i=0; i<memory.size(); i++)
        {
            System.out.println(memory.get(i));
        }
    }
    
    //in progress
    public int checkMemory(int humanMove)
    {
        
        return 0;
    }
    
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
