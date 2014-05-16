package util;

public class Vector2 {
	private float x;
	private float y;
	
	public Vector2(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public Vector2(int x, int y){
		this.x = x;
		this.x = y;
	}
	
	public float x(){
		return this.x;
	}
	
	public float y(){
		return this.y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void set(float x, float y){
		this.x = x;
		this.y = y;
	}

	public void add(Vector2 v){
		this.x += v.x;
		this.y += v.y;
	}
	
	public void add(float x, float y){
		this.x += x;
		this.y += y;
	}
	
	
	
	public void subtract(Vector2 v){
		this.x -= v.x;
		this.y -= v.y;
	}
	
	public Vector2 normalized(){
		float length = (float) Math.sqrt((this.x * this.x) + (this.y * this.y));
		float nx = (float)(x / length);
        float ny = (float)(y / length);
		
        return new Vector2(nx, ny);
	}
	
	public Vector2 inverted(){
		
		return new Vector2(this.x * -1, this.y * -1);
	}
	
	public String toString(){
		return "Vector2 --> x: " + this.x + ", y: " + this.y; 
	}

	
}

