package MineSweeper;

//due to the fact that I had a project where I needed to make minesweeper in
//CS 215 I just took the code I had previously written there and reused it here.

public class Board {
	protected boolean mined[][];
	protected boolean flagged[][];
	protected boolean revealed[][];
	
	protected int h = 0;
	protected int w = 0;
	
	protected boolean loss;
	protected boolean over;
	
	public Board(int height, int width)
	{
		h = height;
		w = width;
		
		mined = new boolean[h][w];
		flagged = new boolean[h][w];
		revealed = new boolean[h][w];
		
		loss = false;
		over = false;
		
		//make all positions false for all flags
		for(int i = 0; i < h; i++)
		{
			for(int j = 0; j < w; j++)
			{
				mined[i][j] = false;
				flagged[i][j] = false;
				revealed[i][j] = false;
			}
		}
	}
	
	//set the current position to be revealed or not
	public void SetReveal(int x, int y, boolean set)
	{
		revealed[y][x] = set;
	}
	
	//set the current position to be flagged or not
	public void SetFlag(int x, int y, boolean set)
	{
		flagged[y][x] = set;
	}
	
	public void SetMine(int x, int y, boolean set)
	{
		mined[y][x] = set;
	}
	
	//check if revealed
	public boolean CheckReveal(int x, int y)
	{
		return revealed[y][x];
	}
	
	//check if mined
	public boolean CheckMine(int x, int y)
	{
		return mined[y][x];
	}
	
	//check if flagged
	public boolean CheckFlag(int x, int y)
	{
		return flagged[y][x];
	}
}
