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
		return checkWin(board, 'X') || checkWin(board, 'O') || isDraw(board);
	}

	public boolean isDraw(Board board) 
	{
		if (checkWin(board, 'X')|| checkWin(board, 'O'))
		{
			return false;
		}
		
		for (char[]row : board.getGrid())
		{
			for (char cell : row)
			{
				if (cell == 'E')
				{
					return false;
				}
			}
		}
		return true;
		
		
	}
	
	public char getCurrentPlayer(Board board)
	{
		int xCount = 0;
		int oCount = 0;
		
		for(int i = 0; i < board.getSize(); i++)
		{
			for(int j = 0; j < board.getSize(); j++)
			{
				char cell = board.getCell(i, j);
				
				if(cell == 'X')
				{
					xCount++;
				}
				else if(cell == 'O')
				{
					oCount++;
				}
				
			}
		}
	}
	
	public boolean makeMove(Board board, int row, int col)
	{
		if(board.isValidBoardFile() && row >= 0 && row <= 2 && col >= 0 && col <= 2 && board.getCell(row, col) == 'E')
		{
			char player = getCurrentPlayer(board);
		}
		
		board.setCell(row, col, player);
		
		saveBoardToFile(board);
	}


	
	
	
	
}
