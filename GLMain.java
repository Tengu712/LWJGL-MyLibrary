package lwjgl;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;


public class GLMain {
	
	// Window handle
	private long hWnd;
	
	// Main function
	public void run() {
		
		// Initialize
		initWindow();
		initRender();
		initGame();
		
		// Main loop
		while (!glfwWindowShouldClose(hWnd)) {
			// Clear the buffer
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			// ! Game update
			update();

			// Swap the buffer
			glfwSwapBuffers(hWnd);

			// Poll for window events
			glfwPollEvents();
		}
		
		// Ending
		glfwFreeCallbacks(hWnd); // Free the window callback
		glfwDestroyWindow(hWnd); // Destroy the window
		glfwTerminate(); // Terminate GLFW
		glfwSetErrorCallback(null).free(); // Free the error callback
		
	}
	
	// Initialize the window
	private void initWindow() {
		// Set the error callback
		GLFWErrorCallback.createPrint(System.err).set();
		
		// Initialize GLFW
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		
		// Configure GLFW
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		// Load window configure
		boolean fullscreen = false;
		
		// Create Window
		hWnd = glfwCreateWindow(Const.WIDTH, Const.HEIGHT, "Test Game", fullscreen ? glfwGetPrimaryMonitor() : NULL, NULL);
		if(hWnd==NULL)
			throw new RuntimeException("Failed to create the window.");
		
		// Make the window "Context Current," the target render?
		glfwMakeContextCurrent(hWnd);
		// Enable v-sync
		glfwSwapInterval(1);

		// Show the window
		glfwShowWindow(hWnd);
		
	}
	
	// Initialize drawing process
	private void initRender() {
		GL.createCapabilities();
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		// Initialize openGL
		glEnable(GL_TEXTURE_2D); // Enable to use texture
		glEnable(GL_CULL_FACE); // Enable to cull front or back 
		glCullFace(GL_BACK); // Not draw back of polygon
		glEnable(GL_BLEND);	// Enable alpha blending
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // Configure blend mode	   
	}
	
	// ! Initialize Game
	public void initGame() {}
	
	// ! Game update
	public void update() {}

	// Entry Point
	public static void main(String[] args) {
		new GLMain().run();
	}
}
