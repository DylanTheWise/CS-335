package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;




public class JoglEventListener implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	
	
	float backrgb[] = new float[4]; 

	int windowWidth, windowHeight;
	float orthoX=40;

	int mouseX0, mouseY0;	
	float picked_x = 0.0f, picked_y = 0.0f;
	
	float focalLength = 15.0f;
	
	//angle of rotation
	float rotateAngle = 0.0f; // 

	//diffuse light color variables
	float dlr = 0.0f;
	float dlg = 0.0f;
	float dlb = 0.0f;
	float dlw = 1.0f;

	//ambient light color variables
	float alr = 1.0f;
	float alg = 1.0f;
	float alb = 1.0f;

	//light position variables
	float lx_0 = 0.0f;
	float ly_0 = 0.0f;
	float lz_0 = 0.0f; // light0 is at z = 10.0f
	float lw_0 = 1.0f;
	
	
	/*** Define material property  ***/ 
	
	float redDiffuseMaterial []    = { 1.0f, 0.0f, 0.0f }; //set the material to red
	float whiteSpecularMaterial [] = { 1.0f, 1.0f, 1.0f }; //set the material to white
	float greenEmissiveMaterial [] = { 0.0f, 1.0f, 0.0f }; //set the material to green
	float whiteSpecularLight []    = { 1.0f, 1.0f, 1.0f }; //set the light specular to white
	
	float yellowSunColor [] = {1.0f, 1.0f, 0.0f};
	
	float blueEarthColor [] = { 30.0f/255.0f, 144.0f/255.0f, 255.0f/255.0f };
	float blueEarthColorDark [] = { 0.0f, 114.0f/255.0f, 225.0f/255.0f };
	
	float whiteMoonColor [] = {254.0f/255.0f, 252.0f/255.0f, 215.0f/255.0f};
	float whiteMoonColorDark [] = {204.0f/255.0f, 202.0f/255.0f, 165.0f/255.0f};
	
	float blackShadowColor [] = {0.05f, 0.05f, 0.05f};
	
	int globalRotation = 0;
	float rotateMoon = 0.0f;
	float earthXCoord = 0.0f;
	float earthYCoord = 0.0f;
	float inclinationAngle = 0.0f;
	
	
	float blankMaterial[]     = { 0.0f, 0.0f, 0.0f }; //set the material to black
	float grayMaterial[]     = { 0.7f, 0.7f, 0.7f }; //set the material to gray
	float mShininess[]        = { 10 }; //set the shininess of the material

	
	
	boolean diffuse_flag  = false;
	boolean specular_flag = false;
	
	boolean smooth_flag = true;

    private GLU glu = new GLU();
	
    private GLUT glut = new GLUT();
	
	public void drawSun(final GL2 gl){
		//float ambientLight[] = {alr, alg, alb}; // ambient light property
		float mSunShininess[]        = { 1 };
		
		// set the material property
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, yellowSunColor, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, yellowSunColor, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, mSunShininess, 0);	
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, yellowSunColor, 0);
			
		glut.glutSolidSphere(1, 360, 360);
	}
	
	public void drawEarth(final GL2 gl){	
		gl.glPushMatrix();
		//System.out.printf("%f\n",globalRotation);
		earthXCoord = (float) (5.0f * Math.cos(globalRotation));
		earthYCoord = (float) (Math.sin(12.0f));
		//System.out.printf("%f\n",earthXCoord);
		
		gl.glRotatef(12.0f, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(globalRotation, 0.0f, 1.0f, 0.0f);
		gl.glTranslatef(5.0f, 0.0f, 0.0f);
		
		// set the material property
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, blackShadowColor, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, blueEarthColor, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, mShininess, 0);	
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, blueEarthColor, 0);
		gl.glColorMaterial(gl.GL_FRONT, gl.GL_DIFFUSE);
		gl.glEnable(gl.GL_COLOR_MATERIAL);
		
		glut.glutSolidSphere(1.0f/3.0f, 360, 360);
		
		gl.glPopMatrix();
		
	}
	
	public void drawMoon(final GL2 gl){
		gl.glPushMatrix();
		
		gl.glRotatef(12.0f, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(globalRotation, 0.0f, 1.0f, 0.0f);
		gl.glTranslatef(5.0f, 0.0f, 0.0f);
		
		if (earthXCoord < 0.0f)
		{
			earthXCoord = earthXCoord * -1.0f;
		}
		
		gl.glRotatef(12.0f, earthXCoord, 0.0f, 0.0f);
		gl.glRotatef (globalRotation, 0.0f, earthXCoord, 0.0f);
		gl.glTranslatef(1.0f, 0.0f, 0.0f);
		
		// set the material property
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, blackShadowColor, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, whiteMoonColor, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, mShininess, 0);	
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, whiteMoonColor, 0);
		gl.glColorMaterial(gl.GL_FRONT, gl.GL_DIFFUSE);
		gl.glEnable(gl.GL_COLOR_MATERIAL);
		
		glut.glutSolidSphere(1.0f/9.0f, 360, 360);
		
		gl.glPopMatrix();
		
	}
	
	    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) {
		 
	    }

	    /** Called by the drawable immediately after the OpenGL context is
	     * initialized for the first time. Can be used to perform one-time OpenGL
	     * initialization such as setup of lights and display lists.
	     * @param gLDrawable The GLAutoDrawable object.
	     */
	    public void init(GLAutoDrawable gLDrawable) {
	        GL2 gl = gLDrawable.getGL().getGL2();
	        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
	        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
	        gl.glEnable(GL.GL_DEPTH_TEST);              // Enables Depth Testing
	        gl.glDepthFunc(GL.GL_LEQUAL);               // The Type Of Depth Testing To Do
	        // Really Nice Perspective Calculations
	        //gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
	        
	        //gl.glEnable(GL2.GL_LIGHTING); // enable lighting
	        
	        //gl.glEnable(GL2.GL_LIGHT0); // enable light0
	       // gl.glEnable(GL2.GL_LIGHT1); 
	        gl.glMatrixMode(GL2.GL_MODELVIEW);
	        gl.glLoadIdentity();
	        
	        
	        
	    }


	    
	    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
	    	windowWidth = width;
	    	windowHeight = height;
	        final GL2 gl = gLDrawable.getGL().getGL2();

	        if (height <= 0) // avoid a divide by zero error!
	            height = 1;
	        final float h = (float) width / (float) height;
	        gl.glViewport(0, 0, width, height);
	        gl.glMatrixMode(GL2.GL_PROJECTION);
	        gl.glLoadIdentity();
	       // gl.glOrtho(-orthoX*0.5, orthoX*0.5, -orthoX*0.5*height/width, orthoX*0.5*height/width, -100, 100);
	        glu.gluPerspective(45.0f, h, 1, 100000.0);

	    }
	    
	    

		@Override
		public void display(GLAutoDrawable gLDrawable) {
			// TODO Auto-generated method stub
			final GL2 gl = gLDrawable.getGL().getGL2();

			gl.glClearColor(0, 0, 0, 1);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
			
	    	gl.glMatrixMode(GL2.GL_MODELVIEW);
	    	gl.glLoadIdentity();
			
			globalRotation += 5;
			
			if (globalRotation >= 360)
			{
				globalRotation = 0;
			}
			
			
			glu.gluLookAt(0.0, 0.0, focalLength, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0); // eye point, x, y, z, looking at x, y, z, Up direction 
	    	
			gl.glRotatef(rotateAngle, 0.0f, 1.0f, 0.0f);
			
			
			drawSun(gl);

	    	drawEarth(gl);
	    	
	    	drawMoon(gl);
	    	
	    	float ambientLight[] = {0.2f, 0.2f, 0.2f, 1.0f}; // ambient light property
			float diffuseLight[] = {1.0f, 1.0f, 1.0f, 1.0f};
			float ligthtPosition_0[] = {0, 0, 0, 1}; // light position
			
			gl.glEnable(GL2.GL_LIGHTING); // enable lighting
		    gl.glEnable(GL2.GL_LIGHT0); // enable light0
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, ambientLight, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, diffuseLight, 0); // set light0 as diffuse light with related property
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, ligthtPosition_0, 0); // set light0 position
	    	
	    	gl.glFlush();
	    	
		}

		@Override
		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		    char key= e.getKeyChar();
			System.out.printf("Key typed: %c\n", key); 
			
			switch(key)
			{
			case 'r':
				rotateAngle += 5.0f;
				if(rotateAngle >= 360.0f)
					rotateAngle -= 360.0f;
				break;
			case 'R':
				rotateAngle -= 5.0f;
				if(rotateAngle <= 0)
					rotateAngle += 360;
				break;
				

			case 'g':
			case 'G':
				focalLength += 5;
				
				break;
				
			case 'h':
			case 'H':
				focalLength -= 5;
				
				break;
				
			default:
				
				break;
			
			
			
			}
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			
			float XX = (e.getX()-windowWidth*0.5f)*orthoX/windowWidth;
			float YY = -(e.getY()-windowHeight*0.5f)*orthoX/windowHeight;
			
			
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Your window get focus."); 
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			/*
			 * Coordinates printout
			 */
			picked_x = (e.getX()-windowWidth*0.5f)*orthoX/windowWidth;
			picked_y = -(e.getY()-windowHeight*0.5f)*orthoX/windowHeight;
			
			System.out.printf("Point clicked: (%.3f, %.3f)\n", picked_x, picked_y);
			
			mouseX0 = e.getX();
			mouseY0 = e.getY();
			
			if(e.getButton()==MouseEvent.BUTTON1) {	// Left button
				
				
			}
			else if(e.getButton()==MouseEvent.BUTTON3) {	// Right button
				
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) { // cursor enter the window
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) { // cursor exit the window
			// TODO Auto-generated method stub
			
		}


	
}