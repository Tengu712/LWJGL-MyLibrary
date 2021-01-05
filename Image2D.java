package lwjgl;

import static org.lwjgl.opengl.GL11.*;

public class Image2D {

	public int id;
	public Vec2 wh;
	public Vec2 pos;
	public Vec3 col;
	public double rot;
	public double scl;
	public double alp;
	
	// Constructor
	public Image2D(int id, double x, double y, double w, double h) {
		this.id = id;
		this.pos = new Vec2(x,y);
		this.col = new Vec3(1,1,1);
		wh = new Vec2(w,h);
		rot = 0.0;
		scl = 1.0;
		alp = 1.0;
	}
	
	// Constructor
	public Image2D(Texture tex, double x, double y) {
		if(tex!=null) this.id = tex.id;
		else this.id = 0;
		this.pos = new Vec2(x,y);
		this.col = new Vec3(1,1,1);
		if(tex!=null) wh = new Vec2(tex.width,tex.height);
		else wh = new Vec2(30,30);
		rot = 0.0;
		scl = 1.0;
		alp = 1.0;
	}
	
	// Draw the image
	public void draw() {
		// Use texture
		glBindTexture(GL_TEXTURE_2D, id);
		
		// Push matrix
		glPushMatrix();
		
		// Start model view matrix
		glMatrixMode(GL_MODELVIEW);
		// Make matrix
		glLoadIdentity();
		glTranslated(pos.x,pos.y,alp);
		glRotated(rot,0,0,1);
		glScaled(scl,scl,1);		
		
		// Start projection matrix
		glMatrixMode(GL_PROJECTION);
		// Make matrix
		glLoadIdentity();
		glOrtho(0,Const.WIDTH,0,Const.HEIGHT,-Const.DEPTH,Const.DEPTH);
		
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
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	// Change texture
	public void changeTex(int id) {
		this.id = id;
	}
	
	// Change texture
	public void changeTex(Texture tex) {
		if(tex!=null) this.id = tex.id;
		else this.id = 0;
		if(tex!=null) wh = new Vec2(tex.width,tex.height);
		else wh = new Vec2(30,30);
	}
}
