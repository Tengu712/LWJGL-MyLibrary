package blog;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;

public class Texture {

	public int id;
	public int width;
	public int height;
	public String name;

	public Texture(String name, String path) {
		this.name = name;
		BufferedImage bi;
		try {
			// イメージファイルの読み込み
			bi = ImageIO.read(new File(path));
			width = bi.getWidth();
			height = bi.getHeight();

			// ピクセルを保存する配列の用意
			int[] pixelsRaw = new int[width * height];
			pixelsRaw = bi.getRGB(0, 0, width, height, null, 0, width);
			ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);

			// 一ピクセルずつ読み込む
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					int p = pixelsRaw[i * width + j];
					pixels.put((byte) ((p >> 16) & 0xFF));
					pixels.put((byte) ((p >> 8) & 0xFF));
					pixels.put((byte) (p & 0xFF));
					pixels.put((byte) ((p >> 24) & 0xFF));
				}
			}
			pixels.flip();

			// テクスチャの作成
			id = glGenTextures(); // IDの取得
			glBindTexture(GL_TEXTURE_2D, id); // IDとテクスチャデータを結合
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels); // 作成

			// テクスチャの設定
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			// テクスチャの解除
			glBindTexture(GL_TEXTURE_2D, 0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// テクスチャの使用
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	// テクスチャの解除
	public void debind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	// テクスチャの破棄
	public void delete() {
		glDeleteTextures(id);
	}

}
