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
			// �C���[�W�t�@�C���̓ǂݍ���
			bi = ImageIO.read(new File(path));
			width = bi.getWidth();
			height = bi.getHeight();

			// �s�N�Z����ۑ�����z��̗p��
			int[] pixelsRaw = new int[width * height];
			pixelsRaw = bi.getRGB(0, 0, width, height, null, 0, width);
			ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);

			// ��s�N�Z�����ǂݍ���
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

			// �e�N�X�`���̍쐬
			id = glGenTextures(); // ID�̎擾
			glBindTexture(GL_TEXTURE_2D, id); // ID�ƃe�N�X�`���f�[�^������
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels); // �쐬

			// �e�N�X�`���̐ݒ�
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			// �e�N�X�`���̉���
			glBindTexture(GL_TEXTURE_2D, 0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �e�N�X�`���̎g�p
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	// �e�N�X�`���̉���
	public void debind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	// �e�N�X�`���̔j��
	public void delete() {
		glDeleteTextures(id);
	}

}
