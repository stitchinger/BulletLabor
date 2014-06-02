package Entity;

import org.newdawn.slick.Graphics;

import Entity.Player.Weapons.Weapon;
import GameManager.ObjectManager;
import Util.Vector2;

public abstract class AdvancedObject extends PhysicsObject {
	
	protected Weapon weapon;
	
	public AdvancedObject(float x, float y) {
		super(x, y);
	}

	public void update(int delta){
		super.update(delta);
	
	}
	
	public void render(Graphics g){
		super.render(g);
		if(this.weapon != null){
			this.weapon.render(g);
		}
	}
	
	public Vector2 getTargetVector(Vector2 targetPosition){
    	
        float vecX = targetPosition.x() - (this.getX()+this.width/2);
        float vecY= targetPosition.y() - (this.getY()+this.height/2);
        Vector2 vecNorm = new Vector2(vecX, vecY).normalize();
        return vecNorm;
	}
	
	public Weapon getWeapon(){
    	return this.weapon;
	}    
	
	public void setWeapon(Weapon weapon){
	    	if(this.hasWeapon()){
	    		this.dropWeapon();
	    	}
	    	this.weapon = weapon;
	    	ObjectManager.removeObject(weapon);
	    	this.weapon.setOwner(this);
	}
	 
	public void dropWeapon(){
	    	if(this.hasWeapon()){
	    		this.weapon.drop();
	    		ObjectManager.getWorld_objects().add(this.weapon);
	    		this.weapon.velocity.set(this.getVelocity().mult(0.5f));
	    		this.weapon.addForce(this.getSomehowUp().mult(8));
	    		this.weapon = null;
		    }
	  }
	 
	public boolean hasWeapon(){
		 return this.weapon != null;
	}
	    
	public int getAmmo(){
		 int leftAmmo;
		 if(this.hasWeapon()){
			 leftAmmo = this.weapon.getAmmo();
		 }else{
			 leftAmmo = 0;
		 }
		 return leftAmmo;
	 }
	    
	private Vector2 getSomehowUp(){
		 int range = 30;
		 float randomUp = (0-range/2) + (int)(Math.random() * (((0+range/2) - (0-range/2)) + 1));
		 Vector2 upv = new Vector2(randomUp);
		 return upv;
	}
}
