package Entity.Player.Weapons;

import Util.Settings;

public class Rocket extends Bullet {

	public Rocket(float x, float y) {
		super(x, y);
		this.img = Settings.rocketSprite;
		this.bouncyness = 0.1f;
		this.setSpriteImage(img);
	}

}
