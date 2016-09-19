package helloOpenGL;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class circle {
	static float cx, cy, r;
	static int num_segments;
	
	public circle()
	{
		cx = -10.0f;
		cy = -10.0f;
		r = 5.0f;
		num_segments = 360;
	}
	
	float getXCoord()
	{
		return cx;
	}
	
	float getYCoord()
	{
		return cy;
	}
	
	float getRadius()
	{
		return r;
	}
	
	int getSegNum()
	{
		return num_segments;
	}
	
	void moveLeft()
	{
		cx = cx - 0.1f;
	}
	
	void moveRight()
	{
		cx = cx + 0.1f;
	}
	
	void moveUp()
	{
		cy = cy + 0.1f;
	}
	
	void moveDown()
	{
		cy = cy - 0.1f;
	}
	
	void increaseRad()
	{
		r = r + 0.1f;
	}
	
	void decreaseRad()
	{
		r = r - 0.1f;
	}
	
	void DrawCircle(GLAutoDrawable gLDrawable) 
	{ 
		final GL2 gl = gLDrawable.getGL().getGL2();
		
		gl.glBegin(GL.GL_LINE_LOOP); 
		for(int i = 0; i < num_segments; i++) 
		{ 
			
			gl.glLoadIdentity();
			
			float theta = 2.0f * 3.1415926f * (float) i / (float) num_segments; //get the current angle 

			float x = r * (float) Math.cos(theta); //calculate the x component 
			float y = r * (float) Math.sin(theta); //calculate the y component 

			gl.glVertex2f(x + cx, y + cy); //output vertex 
			
			gl.glLoadIdentity();

		} 
		
		gl.glEnd(); 
		gl.glFlush();
	}
}
