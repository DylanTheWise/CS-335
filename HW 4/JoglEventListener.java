package helloOpenGL;

import java.io.File;

import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;


public class JoglEventListener implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	
	float backrgb[] = new float[4]; 
	float rot = 0.3f; 
	
	int mouseX0, mouseY0;	
	float XX = 0.0f, YY = 0.0f;
	float mouseXF = 0.0f, mouseYF = 0.0f;
	int windowWidth, windowHeight;
	float orthoX = 40;
	
	float currentX = 0;
	float currentY = 0;
	float currentZ = 3;
	
	Texture mytexRight = null;
	Texture mytexLeft = null;
	Texture mytexTop = null; 
	Texture mytexBottom = null; 
	Texture mytexBack = null; 
	Texture mytexFront = null; 
	Texture mytexBox = null;
	Texture mytexMap = null;
	
	int texIDRight;
	int texIDLeft;
	int texIDTop;
	int texIDBottom;
	int texIDBack;
	int texIDFront;
	int texIDBox;
	int texIDMap;
	
	float xTrans = 0.0f;
	float yTrans = 0.0f;
	
	float rotateAngleX = 0.0f;
	float rotateAngleY = 0.0f;
	
	//Texture mytex2 = null; 
	int skyBoxIDBuffer;

    	private GLU glu = new GLU();

	
	 public void displayChanged(GLAutoDrawable gLDrawable, 
	            boolean modeChanged, boolean deviceChanged) {
	    }

	    /** Called by the drawable immediately after the OpenGL context is
	     * initialized for the first time. Can be used to perform one-time OpenGL
	     * initialization such as setup of lights and display lists.
	     * @param gLDrawable The GLAutoDrawable object.
	     */
	    public void init(GLAutoDrawable gLDrawable) {
	        GL2 gl = gLDrawable.getGL().getGL2();
	        //gl.glShadeModel(GL.GL_LINE_SMOOTH);              // Enable Smooth Shading
	        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
	        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
	        gl.glEnable(GL.GL_DEPTH_TEST);              // Enables Depth Testing
	        gl.glDepthFunc(GL.GL_LEQUAL); 
	        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);  
            gl.glEnable(GL.GL_TEXTURE_2D);
            glu.gluLookAt(0, 0, 0, 0, 0, 0, 0, 1, 0);
            
            // The Type Of Depth Testing To Do
	        // Really Nice Perspective Calculations
	        //gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);  
	        
	        // load the texture;
	        
	        try {
	        	//get all texture files
	        	 mytexRight = TextureIO.newTexture(new File("C:\\Users\\Dylan\\workspace\\BoxExample\\src\\right.jpg"), false);
	        	 mytexLeft = TextureIO.newTexture(new File("C:\\Users\\Dylan\\workspace\\BoxExample\\src\\left.jpg"), false);
	        	 mytexTop = TextureIO.newTexture(new File("C:\\Users\\Dylan\\workspace\\BoxExample\\src\\top.jpg"), false);
	        	 mytexBottom = TextureIO.newTexture(new File("C:\\Users\\Dylan\\workspace\\BoxExample\\src\\bottom.jpg"), false);
	        	 mytexBack = TextureIO.newTexture(new File("C:\\Users\\Dylan\\workspace\\BoxExample\\src\\back.jpg"), false);
	        	 mytexFront = TextureIO.newTexture(new File("C:\\Users\\Dylan\\workspace\\BoxExample\\src\\front.jpg"), false);
	        	 mytexBox = TextureIO.newTexture(new File("C:\\Users\\Dylan\\workspace\\BoxExample\\src\\box.jpg"), false);
	        	 mytexMap = TextureIO.newTexture(new File("C:\\Users\\Dylan\\workspace\\BoxExample\\src\\dust2.png"), false);
	        	 
	        	 //make IDs for all textures
	        	 texIDRight = mytexRight.getTextureObject();
	        	 texIDLeft = mytexLeft.getTextureObject();
	        	 texIDTop = mytexTop.getTextureObject();
	        	 texIDBottom = mytexBottom.getTextureObject();
	        	 texIDBack = mytexBack.getTextureObject();
	        	 texIDFront = mytexFront.getTextureObject();
	        	 texIDBox = mytexBox.getTextureObject();
	        	 texIDMap = mytexMap.getTextureObject();

	         	 gl.glTexParameteri(GL2.GL_TEXTURE_CUBE_MAP, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
	         	 gl.glTexParameteri(GL2.GL_TEXTURE_CUBE_MAP, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
	         	 gl.glTexParameteri(GL2.GL_TEXTURE_CUBE_MAP, GL2.GL_TEXTURE_WRAP_S, GL2.GL_CLAMP_TO_EDGE);
	         	 gl.glTexParameteri(GL2.GL_TEXTURE_CUBE_MAP, GL2.GL_TEXTURE_WRAP_T, GL2.GL_CLAMP_TO_EDGE);
	         	 gl.glTexParameteri(GL2.GL_TEXTURE_CUBE_MAP, GL2.GL_TEXTURE_WRAP_R, GL2.GL_CLAMP_TO_EDGE);
	         	 
	             
	             
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	       
	    }
	    
	    public void DrawBox(final GL2 gl)
	    {
	    	
	    	//draw crate in
	    	gl.glTranslatef(0.0f, 0.0f, -3.0f);
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDBox);
	        gl.glBegin(GL2ES3.GL_QUADS);
	            gl.glTexCoord2f(1, 0); gl.glVertex3f(  0.5f, -0.5f, -0.5f );
	            gl.glTexCoord2f(0, 0); gl.glVertex3f( -0.5f, -0.5f, -0.5f );
	            gl.glTexCoord2f(0, 1); gl.glVertex3f( -0.5f,  0.5f, -0.5f );
	            gl.glTexCoord2f(1, 1); gl.glVertex3f(  0.5f,  0.5f, -0.5f );
	        gl.glEnd();
	        
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDBox);
	        gl.glBegin(GL2ES3.GL_QUADS);
		        gl.glTexCoord2f(1, 0); gl.glVertex3f( -0.5f, -0.5f, -0.5f );
	            gl.glTexCoord2f(0, 0); gl.glVertex3f( -0.5f, -0.5f,  0.5f );
	            gl.glTexCoord2f(0, 1); gl.glVertex3f( -0.5f,  0.5f,  0.5f );
	            gl.glTexCoord2f(1, 1); gl.glVertex3f( -0.5f,  0.5f, -0.5f );
	        gl.glEnd();
	        
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDBox);
	        gl.glBegin(GL2ES3.GL_QUADS);
	            gl.glTexCoord2f(1, 0); gl.glVertex3f(  0.5f, -0.5f,  0.5f );
	            gl.glTexCoord2f(0, 0); gl.glVertex3f(  0.5f, -0.5f, -0.5f );
	            gl.glTexCoord2f(0, 1); gl.glVertex3f(  0.5f,  0.5f, -0.5f );
	            gl.glTexCoord2f(1, 1); gl.glVertex3f(  0.5f,  0.5f,  0.5f );
	        gl.glEnd();
	        
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDBox);
	        gl.glBegin(GL2ES3.GL_QUADS);
	            gl.glTexCoord2f(1, 0); gl.glVertex3f( -0.5f, -0.5f,  0.5f );
	            gl.glTexCoord2f(0, 0); gl.glVertex3f(  0.5f, -0.5f,  0.5f );
	            gl.glTexCoord2f(0, 1); gl.glVertex3f(  0.5f,  0.5f,  0.5f );
	            gl.glTexCoord2f(1, 1); gl.glVertex3f( -0.5f,  0.5f,  0.5f );
	        gl.glEnd();
	        
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDBox);
	        gl.glBegin(GL2ES3.GL_QUADS);
	            gl.glTexCoord2f(1, 0); gl.glVertex3f(  0.5f,  0.5f, -0.5f );
	            gl.glTexCoord2f(0, 0); gl.glVertex3f( -0.5f,  0.5f, -0.5f );
	            gl.glTexCoord2f(0, 1); gl.glVertex3f( -0.5f,  0.5f,  0.5f );
	            gl.glTexCoord2f(1, 1); gl.glVertex3f(  0.5f,  0.5f,  0.5f );
	        gl.glEnd();
	        
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDBox);
	        gl.glBegin(GL2ES3.GL_QUADS);
	            gl.glTexCoord2f(1, 0); gl.glVertex3f(  0.5f, -0.5f,  0.5f );
	            gl.glTexCoord2f(0, 0); gl.glVertex3f( -0.5f, -0.5f,  0.5f );
	            gl.glTexCoord2f(0, 1); gl.glVertex3f( -0.5f, -0.5f, -0.5f );
	            gl.glTexCoord2f(1, 1); gl.glVertex3f(  0.5f, -0.5f, -0.5f );
	        gl.glEnd();

	    }

	    public void DrawSkyBox(final GL2 gl)
	    {
	    	//bind textures and draw the sides of the box, this will also follow the cameras movements
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDFront);
	        gl.glBegin(GL2ES3.GL_QUADS);
	            gl.glTexCoord2f(1, 0); gl.glVertex3f(  150.0f + currentY*-1, -150.0f, -150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 0); gl.glVertex3f( -150.0f + currentY*-1, -150.0f, -150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 1); gl.glVertex3f( -150.0f + currentY*-1,  150.0f, -150.0f + currentX*-1);
	            gl.glTexCoord2f(1, 1); gl.glVertex3f(  150.0f + currentY*-1,  150.0f, -150.0f + currentX*-1);
	        gl.glEnd();
	        
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDLeft);
	        gl.glBegin(GL2ES3.GL_QUADS);
		        gl.glTexCoord2f(1, 0); gl.glVertex3f( -150.0f + currentY*-1, -150.0f, -150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 0); gl.glVertex3f( -150.0f + currentY*-1, -150.0f,  150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 1); gl.glVertex3f( -150.0f + currentY*-1,  150.0f,  150.0f + currentX*-1);
	            gl.glTexCoord2f(1, 1); gl.glVertex3f( -150.0f + currentY*-1,  150.0f, -150.0f + currentX*-1);
	        gl.glEnd();
	        
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDRight);
	        gl.glBegin(GL2ES3.GL_QUADS);
	            gl.glTexCoord2f(1, 0); gl.glVertex3f(  150.0f + currentY*-1, -150.0f,  150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 0); gl.glVertex3f(  150.0f + currentY*-1, -150.0f, -150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 1); gl.glVertex3f(  150.0f + currentY*-1,  150.0f, -150.0f + currentX*-1);
	            gl.glTexCoord2f(1, 1); gl.glVertex3f(  150.0f + currentY*-1,  150.0f,  150.0f + currentX*-1);
	        gl.glEnd();
	        
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDBack);
	        gl.glBegin(GL2ES3.GL_QUADS);
	            gl.glTexCoord2f(1, 0); gl.glVertex3f( -150.0f + currentY*-1, -150.0f,  150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 0); gl.glVertex3f(  150.0f + currentY*-1, -150.0f,  150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 1); gl.glVertex3f(  150.0f + currentY*-1,  150.0f,  150.0f + currentX*-1);
	            gl.glTexCoord2f(1, 1); gl.glVertex3f( -150.0f + currentY*-1,  150.0f,  150.0f + currentX*-1);
	        gl.glEnd();
	        
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDTop);
	        gl.glBegin(GL2ES3.GL_QUADS);
	            gl.glTexCoord2f(1, 0); gl.glVertex3f(  150.0f + currentY*-1,  150.0f, -150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 0); gl.glVertex3f( -150.0f + currentY*-1,  150.0f, -150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 1); gl.glVertex3f( -150.0f + currentY*-1,  150.0f,  150.0f + currentX*-1);
	            gl.glTexCoord2f(1, 1); gl.glVertex3f(  150.0f + currentY*-1,  150.0f,  150.0f + currentX*-1);
	        gl.glEnd();
	        
	        gl.glBindTexture(GL.GL_TEXTURE_2D, texIDBottom);
	        gl.glBegin(GL2ES3.GL_QUADS);
	            gl.glTexCoord2f(1, 0); gl.glVertex3f(  150.0f + currentY*-1, -150.0f,  150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 0); gl.glVertex3f( -150.0f + currentY*-1, -150.0f,  150.0f + currentX*-1);
	            gl.glTexCoord2f(0, 1); gl.glVertex3f( -150.0f + currentY*-1, -150.0f, -150.0f + currentX*-1);
	            gl.glTexCoord2f(1, 1); gl.glVertex3f(  150.0f + currentY*-1, -150.0f, -150.0f + currentX*-1);
	        gl.glEnd();

	    }
	    
	    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, 
	            int height) {
	        final GL2 gl = gLDrawable.getGL().getGL2();
	        
	        windowWidth = width;
	    	windowHeight = height;

	        if (height <= 0) // avoid a divide by zero error!
	            height = 1;
	        final float h = (float) width / (float) height;
	        gl.glViewport(width/2*0, 0, width, height);
	        gl.glMatrixMode(GL2.GL_PROJECTION);
	        gl.glLoadIdentity();
	        glu.gluPerspective(45.0f, h, 1.0, 1000.0);
	        
	        gl.glMatrixMode(GL2.GL_MODELVIEW);
	        
	        gl.glLoadIdentity();
	     
	    }
	    

		@Override
		public void display(GLAutoDrawable gLDrawable) {
			// TODO Auto-generated method stub
			final GL2 gl = gLDrawable.getGL().getGL2();

			gl.glClearColor(backrgb[0]+0.5f, 0+0.5f, 0.5f, 1);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			
			//backrgb[0]+=0.0005;
			if (backrgb[0]> 1) backrgb[0] = 0; 
			
			//handle translation
			if (xTrans != 0.0f || yTrans != 0.0f)
			{
				gl.glTranslatef(yTrans, 0.0f, xTrans);
				xTrans = 0;
				yTrans = 0;
			}

			//handle rotation
			if (rotateAngleX != 0.0f || rotateAngleY != 0.0f)
			{
				gl.glRotatef(-rotateAngleX, 0.0f, currentX, 0.0f);
				gl.glRotatef(-rotateAngleY, currentY, 0.0f, 0.0f);
				
				rotateAngleX = 0.0f;
				rotateAngleY = 0.0f;
			}
			
			gl.glPopMatrix();
			
			gl.glPushMatrix();
			
			gl.glDrawArrays(GL2.GL_TRIANGLES, 0, 36);
			gl.glBindVertexArray(0);
			
			
			//draw skybox
			DrawSkyBox(gl);
	        gl.glPopMatrix();
	        
	        //draw crate
	        gl.glPushMatrix();
	        DrawBox(gl);
	        gl.glPopMatrix();
	        
		}

		@Override
		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		    char key = e.getKeyChar();
			System.out.printf("Key typed: %c\n", key); 
			
			switch(key)
			{
			case 'w':
				xTrans += 0.1f;
				currentX += 0.1f;
				break;
				
			case 'a':
				yTrans += 0.1f;
				currentY += 0.1f;
				break;
			
			case 's':
				xTrans -= 0.1f;
				currentX -= 0.1f;
				break;
				
			case 'd':
				yTrans -= 0.1f;
				currentY -= 0.1f;
				break;
				
			default:
				
				break;
			}
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
			float XX = (e.getX()-windowWidth*0.5f)*orthoX/windowWidth;
			float YY = -(e.getY()-windowHeight*0.5f)*orthoX/windowHeight;
			
			float distanceChangedX = XX - mouseXF;
			float distanceChangedY = YY - mouseYF;
			
			mouseXF = (e.getX()-windowWidth*0.5f)*orthoX/windowWidth;
			mouseYF = -(e.getY()-windowHeight*0.5f)*orthoX/windowWidth;
			
			// rotation calls for left click

			if (distanceChangedX > 0)
			{
				rotateAngleX += 1.0f;
			}
			
			else if (distanceChangedX < 0)
			{
				rotateAngleX -= 1.0f;
			}
			
			if (distanceChangedY > 0)
			{
				rotateAngleY += 1.0f;
			}
			
			else if (distanceChangedY < 0)
			{
				rotateAngleY -= 1.0f;
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			
			XX = (e.getX()-windowWidth*0.5f)*orthoX/windowWidth;
			YY = -(e.getY()-windowHeight*0.5f)*orthoX/windowHeight;
			
			System.out.printf("Point clicked: (%.3f, %.3f)\n", XX, YY);
			
			mouseX0 = e.getX();
			mouseY0 = e.getY();
			
			mouseXF = XX;
			mouseYF = YY;
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
	  /*  
	public void init(GLDrawable gLDrawable) {
		final GL gl = glDrawable.getGL();
        final GLU glu = glDrawable.getGLU();

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-1.0f, 1.0f, -1.0f, 1.0f); // drawing square
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }*/
	
}