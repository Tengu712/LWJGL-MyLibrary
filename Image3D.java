package blog;

import static org.lwjgl.opengl.GL11.*;

public class Image3D {

	public Texture tex;

	public Vec2 wh;
	public Vec3 pos;
	public Vec3 rot;
	public Vec3 col;
	public double scl;
	public double alp;

	public Image3D(Texture tex, double x, double y, double z, double w, double h) {
		this.tex = tex;
		pos = new Vec3(x, y, z);
		wh = new Vec2(w, h);
		rot = new Vec3(0, 0, 0);
		col = new Vec3(1, 1, 1);
		scl = 1.0;
		alp = 1.0;
	}

	public void draw() {
		if (tex != null)
			tex.bind();

		glPushMatrix();

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslated(pos.x, pos.y, pos.z);
		glRotated(rot.x, 1, 0, 0);
		glRotated(rot.y, 0, 1, 0);
		glRotated(rot.z, 0, 0, 1);
		glScaled(scl, scl, scl);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glFrustum(-5, 5, -5, 5, 10, 100); // “§Ž‹“Š‰e

		glViewport(0, 0, Const.WIDTH, Const.HEIGHT);

		glColor4d(col.x, col.y, col.z, alp);

		glBegin(GL_TRIANGLE_STRIP);
		glTexCoord2d(0, 0);
		glVertex2d(-wh.x / 2, wh.y / 2);
		glTexCoord2d(0, 1);
		glVertex2d(-wh.x / 2, -wh.y / 2);
		glTexCoord2d(1, 0);
		glVertex2d(wh.x / 2, wh.y / 2);
		glTexCoord2d(1, 1);
		glVertex2d(wh.x / 2, -wh.y / 2);
		glEnd();

		glPopMatrix();

		if (tex != null)
			tex.debind();
	}
}
