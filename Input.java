package blog;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

	// �E�B���h�E�n���h��
	private long hWnd;

	// �z��
	public int[] inp;
	private int[] preInp;

	public Input(long hWnd) {
		this.hWnd = hWnd;
		inp = new int[Const.KEYNUM];
		preInp = new int[Const.KEYNUM];
		clearInp();
		for (int i = 0; i < Const.KEYNUM; ++i) {
			preInp[i] = 0;
		}
	}

	// �z��̃N���A
	public void clearInp() {
		for (int i = 0; i < Const.KEYNUM; ++i) {
			inp[i] = 0;
		}
	}

	public void setKey() {
		// ������
		clearInp();

		// �L�[�擾
		if (glfwGetKey(hWnd, GLFW_KEY_Z) == 1)
			inp[Const.KEY_Z] = preInp[Const.KEY_Z] + 1;
		if (glfwGetKey(hWnd, GLFW_KEY_X) == 1)
			inp[Const.KEY_X] = preInp[Const.KEY_X] + 1;
		if (glfwGetKey(hWnd, GLFW_KEY_C) == 1)
			inp[Const.KEY_C] = preInp[Const.KEY_C] + 1;
		if (glfwGetKey(hWnd, GLFW_KEY_LEFT) == 1)
			inp[Const.KEY_LEFT] = preInp[Const.KEY_LEFT] + 1;
		if (glfwGetKey(hWnd, GLFW_KEY_RIGHT) == 1)
			inp[Const.KEY_RIGHT] = preInp[Const.KEY_RIGHT] + 1;
		if (glfwGetKey(hWnd, GLFW_KEY_UP) == 1)
			inp[Const.KEY_UP] = preInp[Const.KEY_UP] + 1;
		if (glfwGetKey(hWnd, GLFW_KEY_DOWN) == 1)
			inp[Const.KEY_DOWN] = preInp[Const.KEY_DOWN] + 1;
		if (glfwGetKey(hWnd, GLFW_KEY_LEFT_SHIFT) == 1)
			inp[Const.KEY_SHIFT] = preInp[Const.KEY_SHIFT] + 1;
		if (glfwGetKey(hWnd, GLFW_KEY_ESCAPE) == 1)
			inp[Const.KEY_ESC] = preInp[Const.KEY_ESC] + 1;

		// �L�[��ԕۑ�
		for (int i = 0; i < Const.KEYNUM; ++i) {
			preInp[i] = inp[i];
		}
	}
}
