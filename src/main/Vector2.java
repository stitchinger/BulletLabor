package main;

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
	
	public float[] getNormalizedVector2(float vecX, float vecY){
    	
    	float hypo = (float) Math.sqrt((vecX * vecX) + (vecY * vecY));
        float[] vector2 = new float[2];
    	vector2[0] = (float)(vecX / hypo);
        vector2[1] = (float)(vecY / hypo);
        return vector2;
    }
	
	public Vector2 getNormalizedVector(Vector2 oriV){
		float vx = oriV.getX();
		float vy = oriV.getY();
		
		float length = (float) Math.sqrt((x * x) + (y * y));
		vx = (float)(vx / length);
        vy = (float)(vy / length);
		
        return new Vector2(vx, vy);
	}
	
	public float getX(){
		return this.x;
	}
	public float getY(){
		return this.y;
	}
}
