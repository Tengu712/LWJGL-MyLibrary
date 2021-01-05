package lwjgl;

public class Const {
	
	private Const() {}
	
	public static final int KEYNUM = 20;
	public static final int KEY_Z = 0;
	public static final int KEY_X = 1;
	public static final int KEY_C = 2;
	public static final int KEY_LEFT = 3;
	public static final int KEY_RIGHT = 4;
	public static final int KEY_UP = 5;
	public static final int KEY_DOWN = 6;
	public static final int KEY_SHIFT = 7;
	public static final int KEY_ESC = 8;
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 960;
	public static final int DEPTH = 100;
	
	public static final double LX = 250;
	public static final double RX = 1030;
	public static final double UY = 935;
	public static final double DY = 25;
	
	public static final double[] pmat = {
			2.799, 0, 0, 0,
			0, 3.732, 0, 0,
			0, 0, 1.02, 2.02,
			0, 0, -1, 0
	};
}
