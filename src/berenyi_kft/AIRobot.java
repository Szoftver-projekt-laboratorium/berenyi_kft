package berenyi_kft;

import java.util.ArrayList;

/**
 * A karakterek egyik fajt�ja a robot, 
 * amely minden id�egys�gben f�rni vagy szomsz�dos aszteroid�ra mozogni tud.
 * @author berenyi_kft
 *
 */
public class AIRobot extends Character implements ISteppable{

	/**
	 * A robot megk�rdezi az aszteroid�t�l, amelyen �ll, 
	 * hogy mekkora a k�penyvastags�ga. 
	 * Ha az aszteroida nincs teljesen �tf�rva, 
	 * akkor a robot drill() m�veletet v�gez,
	 * k�l�nben valamelyik szomsz�dos aszteroid�ra l�p (move(int d)).
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * auto implementation
	 */
	@Override
	public ArrayList<Resource> getCollectedResources() {
		return null;
	}
	
	/**
	 * A robot megsemmis�l: 
	 * megh�vja a Timer removeSteppable(Steppable s) met�dus�t.
	 */
	public void die() {
		
	}
	
	/**
	 * A robot egy v�letlenszer� szomsz�dos aszteroid�n landol: 
	 * sorsol egy d sz�mot, 
	 * majd megh�vja a move (int d) met�dust.
	 */
	public void reactToExplosion() {
		
	}

	

}
