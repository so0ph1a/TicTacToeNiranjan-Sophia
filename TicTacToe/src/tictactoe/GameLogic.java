package tictactoe;

public class GameLogic
{

	public boolean checkWin(Board board, char player)
    {
    	for(int i = 0; i < 3; i++)
    	{
    		if(board.getCell(i, 0) == player && board.getCell(i, 1) == player
    		   && board.getCell(i, 2) == player)
    		{
    			return true;
    		}
    	}
    	
    	for(int i = 0; i < 3; i++)
    	{
    		if(board.getCell(0, i) == player && board.getCell(1, i) == player && board.getCell(2, i) == player)
    		{
    			return true;
    		}
    				
    	}
    	
    	for(int i = 0; i < 3; i++)
    	{
    		if(board.getCell(0, 0) == player && board.getCell(1, 1) == player && board.getCell(2, 2) == player)
    		{
    			return true;
    		}
    	}
    	
    	for(int i = 0; i < 3; i++)
    	{
    		if(board.getCell(0, 2) == player && board.getCell(1, 1) == player && board.getCell(2, 0) == player)
    		{
    			return true;
    		}
    	}
    	return false;
    }
	
	
	public boolean isGameOver(Board board)
	{
		return checkWin('X') || checkWin('O') || isDraw();
	}

	
	
	
}
