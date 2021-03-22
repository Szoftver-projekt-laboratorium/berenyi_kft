package berenyi_kft;

import java.util.ArrayList;

/**
 * A Napot reprezentalo osztaly
 * @author berenyi_kft
 */
public class Sun implements ISteppable {

	/**
	 * A kovetkezo napvihar bekovetkezeseig hatralevo ido
	 */
	private long timeToSunStorm;
	
	/**
	 * A Nappal kozvetlenul szomszedos aszteroidak listaja
	 */
	private ArrayList<Asteroid> neighboringAsteroids = new ArrayList<Asteroid>();
	
	/**
	 * A jatekot reprezentalo osztaly
	 */
	private Game game;
	
	//-------------------------------------------------------------
	
	/**
	 * Beallitja a jatek osztalyt.
	 * @param game A jatekot reprezentalo osztaly
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Ha a timeToSunStorm attributum pozitiv, akkor eggyel csokkenti 
	 * (varakozik). Ha pedig 0, akkor napvihart indit a sunStorm() fuggvenyt
	 * hivva, majd a timeToSunStorm-nak beallit egy veletlen pozitiv egesz erteket.
	 */
	@Override
	public void step() {
		System.out.println("Sun's step() has been called");
		// A teszt kedveert legyen minden egyes step soran napvihar.
		sunStorm();
	}
	
	/**
	 * Lekerdezi a Game-tol az aszteroidak kollekciojat, majd egyesevel
	 * meghivja mindegyik aszteroida destroySurface() fuggvenyet.
	 */
	public void sunStorm() {
		System.out.println("Sun's sunStorm() has been called");
		for(Asteroid a : game.getAsteroids()) {
			a.destroySurface();
		}
	}
	
	/**
	 * Megallapitja, hogy az a aszteroida napkozeli-e, vagyis hogy
	 * legfeljebb masodszomszedos-e a Nappal.
	 * @return Pontosan akkor true, ha az aszteroida napkozelben van
	 */
	public boolean isCloseToSun(Asteroid a) {
		System.out.println("Sun's isCloseToSun(a: Asteroid) has been called");
		for (Asteroid a1 : neighboringAsteroids) {
			if (a == a1) {
				return true;
			}
			for (Asteroid a2 : a.getNeighbors()) {
				if (a == a2)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Beallitja a kovetkezo napvihar idopontjat.
	 * @param time Az ido a legkozelebbi napviharig
	 */
	public void setTimeToSunStorm(long time){
		System.out.println("Sun's settimeToStorm(time: long) has been called");
		timeToSunStorm = time;
	}
	
	/**
	 * Uj aszteroidat ad a Nappal szomszedos aszteroidak kollekciojahoz.
	 * @param a A Nappal szomszedos uj aszteroida
	 */
	public void addNeighbor(Asteroid a) {
		System.out.println("Sun's addNeighbor(a: Asteroid) has been called");
		this.neighboringAsteroids.add(a);
	}
}
