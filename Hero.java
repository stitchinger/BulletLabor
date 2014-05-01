//Nur ein Test ;)

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class Hero {
	int posX, velocityX, posY, velocityY, nx2, nx, sprungKraft, testVariable;
	int gravity;
	boolean inAir;
	Image hero_image; //damit das importierte Bild global in der Klasse verwendet werden kann | bzgl z.B. Getter-Methode
	
	public Hero () {
		ImageIcon i = new ImageIcon("hero.png"); //Initialisiert den Image Import für das Hero Bild
		hero_image = i.getImage(); //Importiert das Hero Bild
		posX = 75; //Initialisierungswert auf der x-Achse
		nx2 = 685; //Berechnungshilfe für den Game Loop
		nx = 0; //Berechnungshilfe für den Game Loop
		posY = 172; //Initialisierungswert auf der y-Achse
		sprungKraft = 20;
		inAir = false;
		gravity = 3;
		
	}
	
	public void move() {
		posX = posX + velocityX; //ist die eigentliche Bewegung auf der x-Achse (Schrittbewegung)
		 //ist die eigentliche Bewegung auf der y-Achse (Springen)
		nx2 = nx2 + velocityX; //Berechnungshilfe für den Game Loop
		nx = nx + velocityX; //Berechnungshilfe für den Game Loop
		if(inAir){
			posY = posY + velocityY;
			velocityY = (velocityY + gravity);
		}
		if(posY >= 172){
			inAir = false;
			velocityY = 0;
		}
	}
	
	public int getX() {
		return posX; //gibt x via Getter-Methode aus
	}
	
	public int getY() {
		return posY; //gibt y via Getter-Methode aus
	}
	
	public Image getImage() {
		return hero_image; //stellt das Hero Bild über eine Getter Methode zur Verfügung
	}
	
	public void keyPressed(KeyEvent e) { //bezieht sich auf das KeyEvent auf der Klasse Level --> also wenn die Taste gedrückt wurde
        int key = e.getKeyCode(); //bereitet einen Integer Wert auf eine Zuweisung einer Taste vor
        
		if (key == KeyEvent.VK_LEFT) // VK = Vector Keys || Tasten Event . Ort des Tastenevents _ Spezifizierung des Tastenevents
            velocityX = -1; //wenn die linke Pfeiltaste gedrückt wurde soll der x-Wert auf der x-Achse um 1 nach links verschoben werden
 
        if (key == KeyEvent.VK_RIGHT) // VK = Vector Keys || Tasten Event . Ort des Tastenevents _ Spezifizierung des Tastenevents
            velocityX = 1; //wenn die rechte Pfeiltaste gedrückt wurde soll der x-Wert auf der x-Achse um 1 nach rechts verschoben werden
		
		if (key == KeyEvent.VK_UP) // VK = Vector Keys || Tasten Event . Ort des Tastenevents _ Spezifizierung des Tastenevents
            velocityY = -sprungKraft; //wenn die obere Pfeiltaste gedrückt wurde soll der x-Wert auf der x-Achse um 1 nach oben verschoben werden
			inAir = true;
    }
 
    public void keyReleased(KeyEvent e) { //bezieht sich auf das KeyEvent von der Klasse Level --> also wenn die Taste losgelassen wurde
        int key = e.getKeyCode(); //bereitet einen Integer Wert auf eine Zuweisung einer Taste vor
 
        if (key == KeyEvent.VK_LEFT) // VK = Vector Keys || Tasten Event . Ort des Tastenevents _ Spezifizierung des Tastenevents
            velocityX = 0; //wenn das Drücken der linken Pfeiltaste aufgehoben wurde, dann soll nichts mehr auf der x-Achse verschoben werden
 
        if (key == KeyEvent.VK_RIGHT) // VK = Vector Keys || Tasten Event . Ort des Tastenevents _ Spezifizierung des Tastenevents
            velocityX = 0; //wenn das Drücken der rechten Pfeiltaste aufgehoben wurde, dann soll nichts mehr auf der x-Achse verschoben werden
		
		if (key == KeyEvent.VK_UP) { // VK = Vector Keys || Tasten Event . Ort des Tastenevents _ Spezifizierung des Tastenevents
			//velocityY = 0; // ==> damit der Hero nicht weiter nach oben geht
			//posY = 172; // setzt den ursprünglichen y-Achsenwert fest ==> Charakter geht wieder auf die ursprünglichen y-Achsen Abschnitt
	}
    }
}
