package berenyi_kft;

import java.util.Random;

/**
 * A karakterek egyik fajt�ja a robot, 
 * amely minden id�egys�gben f�rni vagy szomsz�dos aszteroid�ra mozogni tud.
 * @author berenyi_kft
 *
 */
public class AIRobot extends Character implements ISteppable {
	// A játék időzítője, amely a robotot is lépteti.
	// TODO: konstruktorban át kell vennie, hogy a halálakor meghívhassa?
	// private Timer timer;
	
	/**
	 * Új AIRobot jön létre. 
	 * A függvény hozzáadja a timer steppable objektumainak listájához.
	 */
	public AIRobot() {
		// timer.addSteppable(this);
	}
	
	/**
	 * A robot megk�rdezi az aszteroid�t�l, amelyen �ll, 
	 * hogy mekkora a k�penyvastags�ga. 
	 * Ha az aszteroida nincs teljesen �tf�rva, 
	 * akkor a robot drill() m�veletet v�gez,
	 * k�l�nben valamelyik szomsz�dos aszteroid�ra l�p (move(int d)).
	 */
	public void step() {
		if (place.getRockLayerThickness() >= 1) {
			drill();
		} else {
			Random random = new Random();
			move(random.nextInt());
		}
	}
	
	/**
	 * A robot megsemmis�l: 
	 * megh�vja a Timer removeSteppable(ISteppable s) met�dus�t.
	 */
	public void die() {
		// A timerre lesz referenciája a robotoknak?
		// timer.removeSteppable(this);
	}
	
	/**
	 * A robot egy v�letlenszer� szomsz�dos aszteroid�n landol: 
	 * sorsol egy d sz�mot, 
	 * majd megh�vja a move (int d) met�dust.
	 */
	
	@Override
	public void reactToExplosion() {
		System.out.println("AIRobot's reactToExplosion() has been called");
		Random random = new Random();
		move(random.nextInt(place.getSizeOfNeighbors()));
	}

}
