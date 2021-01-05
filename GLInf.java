package lwjgl;

import java.util.ArrayList;

public class GLInf {
	
	// Private
	private ArrayList<Texture> texs;
	
	// Constructor
	public GLInf() {
		texs = new ArrayList<Texture>();
	}
	
	// Load Texture
	public void loadTex(String name, String path) {
		texs.add(new Texture(name, path));
	}
	
	// Get the index of the texture 
	public int getTexId(String name) {
		if(texs.size()<1) return 0;
		int ans = 0;
		for(int i=0; i<texs.size(); ++i) {
			if(texs.get(i).name.equals(name)) ans = texs.get(i).id;
		}
		return ans;
	}
	
	// Get the index of the texture 
	public Texture getTex(String name) {
		if(texs.size()<1) return null;
		Texture ans = texs.get(0);
		for(int i=0; i<texs.size(); ++i) {
			if(texs.get(i).name.equals(name)) ans = texs.get(i);
		}
		return ans;
	}
	
	// Ending event
	public void delete() {
		for(int i=0; i<texs.size(); ++i) {
			texs.get(i).delete();
		}
	}
	
	// ! Update
	public void update() {}
	
	// ! Draw
	public void draw() {}
}
