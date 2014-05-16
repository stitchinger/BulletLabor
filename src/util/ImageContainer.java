package util;

public enum ImageContainer {
	player("Images/Player/player.png"),
	enemy("Images/Enemy/enemy.png"),
	bullet("Images/Bullet/bullet.png"),
	powerup("Images/Powerups/powerup.png");
	
	private String path;
	
	private ImageContainer(String path){
		this.path = path;
	}
}
