package utility;



public class Vector2 {
  
  private float x;
  private float y;
  public static Vector2 NORTH = new Vector2(0,-1);
  public static Vector2 SOUTH = new Vector2(0,1);
  public static Vector2 EAST = new Vector2(1,0);
  public static Vector2 WEST = new Vector2(-1,0);
  
  public Vector2() {
    this.x = 0;
    this.y = 0;
  }
  
  public String toString() {
    return String.format("[%f, %f]", x, y);
  }
  public Vector2(Vector2 vector){
	  this.x = vector.x();
	  this.y = vector.y();
  }
  
  public Vector2(float x, float y) {
    this.x = x;
    this.y = y;
  }
  
  public Vector2(float degree){
	  
	  float radians = (float) (degree * (Math.PI / 180));
	  this.x = (float)Math.sin(radians);
      this.y = -(float)Math.cos(radians);
	 
  }

  public Vector2 setX(float x) {
    this.x = x;
    return this;
  }

  public Vector2 setY(float y) {
    this.y = y;
    return this;
  }

  public Vector2 add(float x, float y) {
    this.x += x;
    this.y += y;
    return this;
  }

  public Vector2 set(Vector2 other) {
    this.x = other.x;
    this.y = other.y;
    return this;
  }

  public Vector2 set(float x, float y) {
    this.x = x;
    this.y = y;
    return this;
  }

  public Vector2 add(Vector2 other) {
    this.x += other.x;
    this.y += other.y;
    return this;
  }
  
  public Vector2 sub(int x, int y) {
    this.x -= x;
    this.y -= y;
    return this;
  }

  public Vector2 add(Vector2 other, float multiplier) {
    this.x += other.x * multiplier;
    this.y += other.y * multiplier;
    return this;
  }

  public float x() {
    return this.x;
  }

  public float y() {
    return this.y;
  }

  public Vector2 limitToMaxAndMin(Vector2 limit) {
    limitToMax(limit, 1);
    limitToMin(limit, -1);
    return this;
  }
  
  public Vector2 limitToMax(Vector2 limit, float multiplier) {
    this.x = Math.min(x, multiplier * limit.x());
    this.y = Math.min(y, multiplier * limit.y());
    return this;
  }
  
  public Vector2 limitToMin(Vector2 limit, float multiplier) {
    this.x = Math.max(x, multiplier * limit.x());
    this.y = Math.max(y, multiplier * limit.y());
    return this;
  }

  public Vector2 mult(float multiplier) {
    this.x *= multiplier;
    this.y *= multiplier;
    return this;
  }
  public Vector2 mult(Vector2 multiplier) {
	    this.x *= multiplier.x();
	    this.y *= multiplier.y();
	    return this;
	  }

  public Vector2 sub(Vector2 other) {
    this.x -= other.x;
    this.y -= other.y;
    return this;
  }

  public Vector2 normalize() {
    return this.mult(1/length());
  }
  
  public float lengthSquared() {
    return (x * x) + (y * y);
  }
  
  public float length() {
    return (float) Math.sqrt((x * x) + (y * y));
  }
  
  public Vector2 addFromAngle(float angle, float multiplier) {
    this.x += multiplier * Math.cos(angle);
    this.y += multiplier * Math.sin(angle);
    return this;
  }

  public float toAngle() {
    return (float) Math.atan2(y, x);
  }
  
  public float toDegee(){
	  return (float) Math.toDegrees(Math.atan2(this.x(), -this.y()));

  }

  public boolean is(float x, float y) {
    return this.x == x && this.y == y;
  }

}
