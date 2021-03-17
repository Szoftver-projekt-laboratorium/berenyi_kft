package berenyi_kft;

import java.util.ArrayList;

/**
 * A Napot reprezentáló osztály
 * @author berenyi_kft
 *
 */
public class Sun implements ISteppable {

	/**
	 * a következõ napvihar bekövetkezéséig hátralévõ idõ
	 */
	long timeToSunStorm;
	
	/**
	 * a Nappal közvetlenül szomszédos aszteroidák listája
	 */
	ArrayList<Asteroid> neighboringAsteroids = new ArrayList<Asteroid>();
	
	/**
	 * a játékot reprezentáló osztály
	 */
	Game game;
	
	//-------------------------------------------------------------
	
	/**
	 * Ha a timeToSunStorm attribútum pozitív, akkor eggyel csökkenti 
	 * (várakozik). Ha pedig 0, akkor napvihart indít a sunStorm() függvényt hívva,
	 *  majd a timeToSunStorm-nak beállít egy véletlen egész értéket.
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Lekérdezi a Game-tõl az aszteroidák kollekcióját, 
	 * majd egyesével meghívja mindegyik aszteroida destroySurface() függvényét.
	 */
	public void sunStorm() {
		
	}
	
	/**
	 * Megállapítja, hogy az a aszteroida napközeli-e
	 * @return
	 */
	public boolean isCloseToSun(Asteroid a) {
		if(this.neighboringAsteroids.contains(a)) {
			return true;
		}
		return false;
	}
	
	

	
}
