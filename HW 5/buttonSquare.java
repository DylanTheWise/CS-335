package MineSweeper;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class buttonSquare extends JButton implements MouseListener{
	
	 int numX = 0;
	 int numY = 0;
	 
	 public buttonSquare(Icon image, int x, int y) {
	        super(image);
	        numX = x;
	        numY = y;
	 }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(this);
		System.out.println(this.numX);
		System.out.println(this.numY);
		
		if(e.getButton() == MouseEvent.BUTTON1)
	    {
			ImageIcon newSquare = new ImageIcon("C:\\Users\\Dylan\\workspace\\HW 5\\src\\MineSweeper\\none.png");
	        Image image = newSquare.getImage(); // transform it 
	        Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	        newSquare = new ImageIcon(newimg);
			
			this.setIcon(newSquare);
	      //label.setText("Detected Mouse Left Click!");
	    }	    
	    else if(e.getButton() == MouseEvent.BUTTON3)
	    {
	    	ImageIcon newSquare = new ImageIcon("C:\\Users\\Dylan\\workspace\\HW 5\\src\\MineSweeper\\flag.png");
	        Image image = newSquare.getImage(); // transform it 
	        Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	        newSquare = new ImageIcon(newimg);
	        
	        this.setIcon(newSquare);
	      //label.setText("Detected Mouse Right Click!");
	    }
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
