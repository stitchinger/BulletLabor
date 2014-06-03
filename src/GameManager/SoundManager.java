package GameManager;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


public class SoundManager {
	
	public static Sound soundfile;
	public static Sound weaponSound;
	public static Sound jumpSound;
	public static Sound jump2Sound;
	
	public SoundManager() throws SlickException {
		
		weaponSound = new Sound("Sounds/Weapon/fire.wav");
		jumpSound = new Sound("Sounds/Weapon/jump.wav");
		jump2Sound = new Sound("Sounds/Weapon/jump2.wav");
	}

	
	
	public static void play(String sound) {
		
		if (sound.equalsIgnoreCase("weapon")) {  
			soundfile = weaponSound;
		}
		
		if (sound.equalsIgnoreCase("jump")) {
			soundfile = jumpSound;
		}
		
		if (sound.equalsIgnoreCase("jump2")) {
			soundfile = jump2Sound;
		}
		
		//Spiele WAV-Datei ab. (pitch, volume)
		soundfile.play(1.0f,0.3f);
	}
}


