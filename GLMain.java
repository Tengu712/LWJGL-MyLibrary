package blog;

import javax.swing.JOptionPane;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GLMain {

	// �E�B���h�E�n���h��
	private long hWnd;

	// �t���X�N���[��
	public boolean fullScreen;

	// �G���g���[�|�C���g
	public static void main(String[] args) {
		int fs = JOptionPane.showConfirmDialog(null, "�t���X�N���[���ŋN�����܂����H", "�m�F", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		new GLMain().run(fs == JOptionPane.YES_OPTION);
	}

	// ���C���֐�
	public void run(boolean fullScreen) {

		// �t���X�N���[���̐^�U�l��Ⴄ
		this.fullScreen = fullScreen;

		// �F�X������
		initWindow();
		initRender();

		// ���C�����[�v
		while (!glfwWindowShouldClose(hWnd)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // �o�b�t�@�̃N���A
			update();
			glfwSwapBuffers(hWnd); // �o�b�t�@�̃X���b�v
			glfwPollEvents(); // ���͂Ƃ��C�x���g�̎擾
		}

		// �I������
		delete(); // �f�X�g���N�^�I�Ȏ��상�\�b�h
		glfwFreeCallbacks(hWnd); // �E�B���h�E�R�[���o�b�N�̉��
		glfwDestroyWindow(hWnd); // �E�B���h�E�̔j��
		glfwTerminate(); // GLFW�̔j��
		glfwSetErrorCallback(null).free(); // �G���[�R�[���o�b�N�̉��
	}

	// ���C�����[�v��
	private void update() {
	}

	// �E�B���h�E�̏�����
	private void initWindow() {
		// �G���[�R�[���o�b�N�̐ݒ�
		GLFWErrorCallback.createPrint(System.err).set();

		// GLFW�̏�����
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		// �E�B���h�E�̐ݒ�
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		// �E�B���h�E�̍쐬
		hWnd = glfwCreateWindow(Const.WIDTH, Const.HEIGHT, Const.TITLENAME, fullScreen ? glfwGetPrimaryMonitor() : NULL,
				NULL);
		if (hWnd == NULL)
			throw new RuntimeException("Failed to create the window.");

		// �E�B���h�E���^�Q�ɂ���̂��H
		glfwMakeContextCurrent(hWnd);
		// v-sync�̓K��
		glfwSwapInterval(1);

		// �E�B���h�E�̕\��
		glfwShowWindow(hWnd);
	}

	// �`��v���Z�X������
	private void initRender() {
		// �Ȃ�ł�������
		GL.createCapabilities();
		// �w�i�F�ݒ�
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// OpenGL�̏�����
		glEnable(GL_TEXTURE_2D); // �񎟌��e�N�X�`���̗L����
		glEnable(GL_BLEND); // �A���t�@�u�����f�B���O�̗L����
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // �u�����h���[�h�̐ݒ�
	}

	// �f�X�g���N�^
	public void delete() {
	}

}
