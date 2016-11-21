package MineSweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class BoardLayout extends JFrame implements MouseListener{
    buttonSquare[][] playArea = new buttonSquare[0][0];
    
    int numX = 0;
    int numY = 0;
     
    public BoardLayout(String name, int x, int y) {
        super(name);
        
        numX = x;
        numY = y;
        playArea = new buttonSquare[x][y];
        
        setResizable(false);
    }
     
    public void addComponentsToPane(final Container pane) {

        final JPanel compsToExperiment = new JPanel();
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(numX , numY));
        
        ImageIcon newSquare = new ImageIcon("C:\\Users\\Dylan\\workspace\\HW 5\\src\\MineSweeper\\square.png");
        Image image = newSquare.getImage(); // transform it 
        Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        newSquare = new ImageIcon(newimg);  
        
        for(int i = 0; i < numX; i++)
        {
        	for(int j = 0; j < numY; j++)
        	{
        		buttonSquare newButton = new buttonSquare(newSquare, i, j);
        		newButton.setPreferredSize(new Dimension(30, 30));
        		newButton.addMouseListener(newButton);
        		
        		playArea[i][j] = newButton;
        		
        		controls.add(newButton);
        	}
        } 
        
        pane.add(controls, BorderLayout.NORTH);
        
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
    	BoardLayout frame = new BoardLayout("BoardLayout", 5, 5);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}