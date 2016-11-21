package MineSweeper;

import java.util.*;

import javax.swing.JFrame;

import java.lang.*;

public class MineSweeper {
	Scanner reader = new Scanner(System.in);
	public MineSweeper()
	{
		int height, width, mines; 
		String choice;
		boolean quit = false;
		System.out.println("What is the height of your board: ");
		height = reader.nextInt();
		System.out.println("What is the width of your board: ");
		width = reader.nextInt();
		System.out.println("How many mines do you want: ");
		mines = reader.nextInt();
		reader.nextLine();
		
		BoardLayout frame = new BoardLayout("BoardLayout", height, width);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
		
		Board b = new Board(height, width);
		SetMines(b, mines);
		Display(b);
		
		/*while(!quit && !b.over)
		{
			System.out.println("Do you want to [R]eveal, [F]lag, or [Q]uit?");
			choice = reader.nextLine();
			switch(choice){
			case "r":
				RevealPosition(b);
				CheckGame(b);
				Display(b);
				break;
				
			case "f":
				FlagPosition(b);
				CheckGame(b);
				Display(b);
				break;
				
			case "q":
				quit = true;
				break;
			}
		}
		if(!b.loss && !quit)
		{
			System.out.println("You won the game");
		}
		else 
		{
			if(quit) System.out.println("Quitter!");
			else System.out.println("You tripped a mine");
			Quit(b);
			Display(b);
		}*/
	}
	
	public int[] GetCoords(String[] line)
	{
		int[] coords = new int[2];
		int count = 0;
		
		for(int i = 0; i < line.length; i++)
		{
			if(TryParse(line[i]))
			{
				coords[count] = Integer.parseInt(line[i]);
				count++;
			}
		}
		
		return coords;
	}
	
	public void RevealPosition(Board b)
	{
		String line;
		int x = 0; int y = 0;
		boolean incorrect = false;
		int[] coords = new int[2];
		do
		{
			System.out.println("What coordinate do you want to reveal? (Separate by space)");
			line = reader.nextLine();
			String[] split = line.split("\\s+");
			coords = GetCoords(split);
			y = coords[0]; x = coords[1];
			if((x >= b.w)|| (y >= b.h))
			{
				incorrect = true;
				System.out.println("Incorrect Coordinates");
				
			}
			else
			{
				incorrect = false;
			}
		}while(incorrect);
		Position pos = new Position(x,y);
		Reveal(b, pos);
	}
	
	public void FlagPosition(Board b)
	{
		String line;
		int x = 0; int y = 0;
		boolean incorrect = false;
		int[] coords = new int[2];
		do
		{
			System.out.println("What coordinate do you want to flag? (Separate by space)");
			line = reader.nextLine();
			String[] split = line.split("\\s+");
			coords = GetCoords(split);
			y = coords[0]; x = coords[1];
			if((x >= b.w)|| (y >= b.h))
			{
				incorrect = true;
				System.out.println("Incorrect Coordinates");
				
			}
			else
			{
				incorrect = false;
			}
		}while(incorrect);
		Position pos = new Position(x,y);
		Flag(b, pos);
	}

	public void Flag(Board b, Position pos)
	{
		if(b.CheckFlag(pos.x, pos.y) == true)
		{
			b.SetFlag(pos.x, pos.y, false);
		}else
		{
			b.SetFlag(pos.x, pos.y, true);
		}
	}
	
	public void Reveal(Board b, Position pos)
	{
		if(b.CheckReveal(pos.x,pos.y) == true)
		{
			return;
		}
		
		b.SetReveal(pos.x, pos.y, true);
		b.SetFlag(pos.x, pos.y, false);
		
		if(b.CheckMine(pos.x,pos.y) == true)
		{
			return;
		}
		
		List<Position> posarray = AdjacentPos(b, pos);
		int adjmines = AdjacentMines(b, pos);
		int adjflag = AdjacentFlag(b, pos);
		
		if(adjmines == adjflag)
		{
			for(int i = 0; i < posarray.size(); i++)
			{
				Position p = posarray.get(i);
				if(b.CheckFlag(p.x, p.y) == false)
				{
					Reveal(b, p);
				}
			}
		}
	}

	
	public int AdjacentMines(Board b, Position pos)
	{
		int count = 0;
		List<Position> adjacent = AdjacentPos(b, pos);
		for (int i = 0; i < adjacent.size(); i++) {
			Position adj = adjacent.get(i);
			if (b.CheckMine(adj.x, adj.y))
				count++;
		}
		return count;
	}

	public int AdjacentFlag(Board b, Position pos)
	{
		int count = 0;
		List<Position> adjacent = AdjacentPos(b, pos);
		for (int i = 0; i < adjacent.size(); i++) {
			Position adj = adjacent.get(i);
			if (b.CheckFlag(adj.x, adj.y))
				count++;
		}
		return count;
	}
	
	public void Display(Board b)
	{
		System.out.format("  %d%3d%3d%3d%3d\n", 0,1,2,3,4,5);
		for(int i = 0; i < b.h; i++)
		{
			System.out.format("%d", i);
			for(int j = 0; j < b.w; j++)
			{
				Position pos = new Position(j,i);
				char out = Appear(b, pos);
				if (out == '0') out = ' ';
				if(j != 0)
				{
					System.out.format("%3c", out);
				}else
				{
					System.out.format(" %c", out);
				}
			}
			System.out.println();
		}
	}
	
	public char Appear(Board b, Position pos)
	{
		if(b.CheckFlag(pos.x, pos.y) == true) return 'F';
		else if(b.CheckReveal(pos.x, pos.y) == true) 
		{
			if(b.CheckMine(pos.x, pos.y) == true)
			{
				return '*';
			}else
			{
				int adjmines = AdjacentMines(b,pos);
				return Character.forDigit(adjmines, 10);
			}
		}/*
		if(b.CheckMine(pos.x, pos.y) == true)
		{
			return '*';
		}*/
		else
		{
			return 'X';
		}
		
	}
	
	public void SetMines(Board b, int mines)
	{
		int count = 0;
		Random ran = new Random();
		
		while(count < mines)
		{
			int x = ran.nextInt(b.w);
			int y  = ran.nextInt(b.h);
			if(!b.CheckMine(x, y))
			{
				b.SetMine(x, y, true);
				count++;
			}
		}

	}
	
	
	public List<Position> AdjacentPos(Board b, Position pos)
	{
		List<Position> posarray = new ArrayList<Position>();
		
		for (int adj_y = pos.y - 1; adj_y <= pos.y + 1; adj_y++) {
			for (int adj_x = pos.x - 1; adj_x <= pos.x + 1; adj_x++) {
				if (adj_x >= 0 && adj_y >= 0 && adj_x < b.w && adj_y < b.h)
				{
					if(adj_x == pos.x && adj_y == pos.y) continue;
					Position adj = new Position(adj_x,adj_y);
					posarray.add(adj);
				}
			}
		}
		
		return posarray;
	}
	
	public boolean TryParse(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
	
	public void CheckGame(Board b)
	{
		Won(b);
		Loss(b);
	}
	
	public void Won(Board b)
	{
		for (int y = 0; y < b.h; y++) {
			for (int x = 0; x < b.w; x++) {
				if (b.CheckMine(x,y) && !b.CheckFlag(x,y)) {
					return;
				} else if (!b.CheckMine(x,y) && !b.CheckReveal(x,y)) {
					return;
				}
			}
		}
		b.over = true;
	}
	
	public void Loss(Board b)
	{
		for (int y = 0; y < b.h; y++)
		{
			for (int x = 0; x < b.w; x++)
			{
				if (b.CheckMine(x,y) && b.CheckReveal(x,y))
				{
					b.over = true;
					b.loss = true;
				}
			}
		}
	}
	
	public void Quit(Board b)
	{
		for (int y = 0; y < b.h; y++)
		{
			for (int x = 0; x < b.w; x++)
			{
				b.SetFlag(x, y, false);
				b.SetReveal(x, y, true);
			}
		}
	}
	
    public static void main(String[] args) {
        MineSweeper game = new MineSweeper();
    }
}
