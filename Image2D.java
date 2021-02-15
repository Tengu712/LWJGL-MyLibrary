package blog;

import static org.lwjgl.opengl.GL11.*;

public class Image2D {

	public int id; // �e�N�X�`����ID
	public Vec2 wh; // �X�v���C�g�̕���
	public Vec2 pos; // �X�v���C�g�̍��W�i��ʍ��W�j
	public Vec3 col; // �X�v���C�g�̐F
	public double rot; // ��](�x)
	public double scl; // �g��
	public double alp; // �����x

	public Image2D(Texture tex, double x, double y) {
		if (tex != null)
			this.id = tex.id;
		else
			this.id = 0;
		this.pos = new Vec2(x, y);
		this.col = new Vec3(1, 1, 1);
		if (tex != null)
			wh = new Vec2(tex.width, tex.height);
		else
			wh = new Vec2(30, 30);
		rot = 0.0;
		scl = 1.0;
		alp = 1.0;
	}

	public void draw() {
		// �e�N�X�`���̌���
		glBindTexture(GL_TEXTURE_2D, id);

		// �ϊ��s��̒ǉ�
		glPushMatrix();

		// ���f���r���[���[�h
		glMatrixMode(GL_MODELVIEW);
		// �s��̐ݒ�
		glLoadIdentity(); // �P�ʍs��
		glTranslated(pos.x, pos.y, 0); // �ړ�
		glRotated(rot, 0, 0, 1); // ��]
		glScaled(scl, scl, 1); // �g�k

		// �v���W�F�N�V�������[�h
		glMatrixMode(GL_PROJECTION);
		// �s��̐ݒ�
		glLoadIdentity(); // �P�ʍs��
		glOrtho(0, Const.WIDTH, 0, Const.HEIGHT, -Const.DEPTH, Const.DEPTH); // ���ˉe���e

		// �r���[�|�[�g�͈̔�
		glViewport(0, 0, Const.WIDTH, Const.HEIGHT);

		// �|���S���̐F
		glColor4d(col.x, col.y, col.z, alp);

		// �|���S���̍쐬�ƃe�N�X�`���̓K��
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

		// �s��̔j��
		glPopMatrix();

		// �e�N�X�`���̉���
		glBindTexture(GL_TEXTURE_2D, 0);
	}
}
