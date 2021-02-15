package blog;

import static org.lwjgl.opengl.GL11.*;

public class Image2D {

	public int id; // テクスチャのID
	public Vec2 wh; // スプライトの幅高
	public Vec2 pos; // スプライトの座標（画面座標）
	public Vec3 col; // スプライトの色
	public double rot; // 回転(度)
	public double scl; // 拡大
	public double alp; // 透明度

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
		// テクスチャの結合
		glBindTexture(GL_TEXTURE_2D, id);

		// 変換行列の追加
		glPushMatrix();

		// モデルビューモード
		glMatrixMode(GL_MODELVIEW);
		// 行列の設定
		glLoadIdentity(); // 単位行列化
		glTranslated(pos.x, pos.y, 0); // 移動
		glRotated(rot, 0, 0, 1); // 回転
		glScaled(scl, scl, 1); // 拡縮

		// プロジェクションモード
		glMatrixMode(GL_PROJECTION);
		// 行列の設定
		glLoadIdentity(); // 単位行列化
		glOrtho(0, Const.WIDTH, 0, Const.HEIGHT, -Const.DEPTH, Const.DEPTH); // 正射影投影

		// ビューポートの範囲
		glViewport(0, 0, Const.WIDTH, Const.HEIGHT);

		// ポリゴンの色
		glColor4d(col.x, col.y, col.z, alp);

		// ポリゴンの作成とテクスチャの適応
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

		// 行列の破棄
		glPopMatrix();

		// テクスチャの解除
		glBindTexture(GL_TEXTURE_2D, 0);
	}
}
