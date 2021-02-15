package blog;

import javax.swing.JOptionPane;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GLMain {

	// ウィンドウハンドル
	private long hWnd;

	// フルスクリーン
	public boolean fullScreen;

	// エントリーポイント
	public static void main(String[] args) {
		int fs = JOptionPane.showConfirmDialog(null, "フルスクリーンで起動しますか？", "確認", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		new GLMain().run(fs == JOptionPane.YES_OPTION);
	}

	// メイン関数
	public void run(boolean fullScreen) {

		// フルスクリーンの真偽値を貰う
		this.fullScreen = fullScreen;

		// 色々初期化
		initWindow();
		initRender();

		// メインループ
		while (!glfwWindowShouldClose(hWnd)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // バッファのクリア
			update();
			glfwSwapBuffers(hWnd); // バッファのスワップ
			glfwPollEvents(); // 入力とかイベントの取得
		}

		// 終了処理
		delete(); // デストラクタ的な自作メソッド
		glfwFreeCallbacks(hWnd); // ウィンドウコールバックの解放
		glfwDestroyWindow(hWnd); // ウィンドウの破棄
		glfwTerminate(); // GLFWの破棄
		glfwSetErrorCallback(null).free(); // エラーコールバックの解放
	}

	// メインループ内
	private void update() {
	}

	// ウィンドウの初期化
	private void initWindow() {
		// エラーコールバックの設定
		GLFWErrorCallback.createPrint(System.err).set();

		// GLFWの初期化
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		// ウィンドウの設定
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		// ウィンドウの作成
		hWnd = glfwCreateWindow(Const.WIDTH, Const.HEIGHT, Const.TITLENAME, fullScreen ? glfwGetPrimaryMonitor() : NULL,
				NULL);
		if (hWnd == NULL)
			throw new RuntimeException("Failed to create the window.");

		// ウィンドウをタゲにするのか？
		glfwMakeContextCurrent(hWnd);
		// v-syncの適応
		glfwSwapInterval(1);

		// ウィンドウの表示
		glfwShowWindow(hWnd);
	}

	// 描画プロセス初期化
	private void initRender() {
		// なんですかこれ
		GL.createCapabilities();
		// 背景色設定
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// OpenGLの初期化
		glEnable(GL_TEXTURE_2D); // 二次元テクスチャの有効化
		glEnable(GL_BLEND); // アルファブレンディングの有効化
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // ブレンドモードの設定
	}

	// デストラクタ
	public void delete() {
	}

}
