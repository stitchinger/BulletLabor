package enemies;


import utility.Settings;

public class Slime extends Enemy {
	
	public Slime(float x, float y) {
		super(x, y);
		this.health = 50;
		this.acceleration = 0.2f;
		this.maxWalkSpeed = 3;
		this.direction = "right";
		this.maxHealth = 50;
		this.jumpCount = 0;
		this.jumpHeight = 7;
		this.maxJumpCount = 2;
		
		
		super.setSpriteImage(Settings.slimeEnemySprite);
    }
	
	public void update(){
		super.update();
	}
	
	public void behavior(){
    	super.enemyMove();
    	super.turnOnCollision();
   	}

	
	
}