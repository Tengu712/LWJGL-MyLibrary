package lwjgl;

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
	
	// Constructor
	public Texture(String name, String path) {
		this.name = name;
		BufferedImage bi;
		try {
			// Read the image file
			bi = ImageIO.read(new File(path));
			width = bi.getWidth();
			height = bi.getHeight();
			
			// Make pixel array
			int[] pixelsRaw = new int[width*height];
			pixelsRaw = bi.getRGB(0, 0, width, height, null, 0, width);
			ByteBuffer pixels = BufferUtils.createByteBuffer(width*height*4);
			
			// Get all pixels
			for(int i=0; i<height; ++i) {
				for(int j=0; j<width; ++j) {
					int p = pixelsRaw[i*width+j];
					pixels.put((byte)((p>>16)&0xFF));
					pixels.put((byte)((p>>8)&0xFF));
					pixels.put((byte)(p &0xFF));
					pixels.put((byte)((p>>24)&0xFF));
				}
			}
			pixels.flip();
			
			// Make the texture
			id = glGenTextures(); // Make it and get the handle
			glBindTexture(GL_TEXTURE_2D, id); // Bind id and texture data
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
			
			// Configure the texture
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			
			// Release the binding
			glBindTexture(GL_TEXTURE_2D, 0);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Bind
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	// Debind
	public void debind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	// Delete the texture
	public void delete() {
		glDeleteTextures(id);
	}

}
