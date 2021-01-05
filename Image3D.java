package lwjgl;

import static org.lwjgl.opengl.GL11.*;

public class Image3D {
	
	public Texture tex;
	
	public Vec2 wh;
	public Vec3 pos;
	public Vec3 rot;
	public Vec3 col;
	public double scl;
	public double alp;
	
	// Constructor
	public Image3D(Texture tex, double x, double y, double z, double w, double h) {
		this.tex = tex;
		pos = new Vec3(x,y,z);
		wh = new Vec2(w,h);
		rot = new Vec3(0,0,0);
		col = new Vec3(1,1,1);
		scl = 1.0;
		alp = 1.0;
	}
	
	// Draw the image
	public void draw() {
		// Use texture
		if(tex != null) tex.bind();
		
		// Push matrix
		glPushMatrix();
		
		// Start model view matrix
		glMatrixMode(GL_MODELVIEW);
		// Make matrix
		glLoadIdentity();
		glTranslated(pos.x,pos.y,pos.z);
		glRotated(rot.x,1,0,0);
		glRotated(rot.y,0,1,0);
		glRotated(rot.z,0,0,1);
		glScaled(scl,scl,scl);
		
		// Start projection matrix
		glMatrixMode(GL_PROJECTION);
		// Make matrix
		glLoadIdentity();
		glLoadMatrixd(Const.pmat);
		
		// Print into screen
	    glViewport(0, 0, Const.WIDTH, Const.HEIGHT);
	    
	    // Set color
	    glColor4d(col.x,col.y,col.z,alp);
	    
		// Make vertex and render texture
		glBegin(GL_TRIANGLE_STRIP);
		glTexCoord2d(0, 0);
		glVertex2d(-wh.x/2, wh.y/2);
		glTexCoord2d(0, 1);
		glVertex2d(-wh.x/2, -wh.y/2);
		glTexCoord2d(1, 0);
		glVertex2d(wh.x/2, wh.y/2);
		glTexCoord2d(1, 1);
		glVertex2d(wh.x/2, -wh.y/2);
		glEnd();
	    
		// Pop matrix
		glPopMatrix();
	    
		// Release texture
		if(tex != null) tex.debind();
	}
}
