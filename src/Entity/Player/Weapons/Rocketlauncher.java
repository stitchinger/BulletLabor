package Entity.Player.Weapons;

import Util.Settings;

public class Rocketlauncher extends Weapon{
	
	public Rocketlauncher (float x, float y) {
		super(x, y);
		
		this.id = "Rocketlauncher";
		this.power = 15;
		this.recoil = 0.05f;
		this.rotation = 0;
		this.ammo = 10;
		this.shotsPerMinute = 150;
		this.spreadRange = 6;
		this.rotationInertia = 0.2f;
		this.positionInertia = 0.49f;
		
		this.setSpriteImage(Settings.rocketlauncherSprite);

	}	
}
	
