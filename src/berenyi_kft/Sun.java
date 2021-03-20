package berenyi_kft;

import java.util.ArrayList;

/**
 * A Napot reprezent�l� oszt�ly
 * @author berenyi_kft
 *
 */
public class Sun implements ISteppable {

	/**
	 * a k�vetkez� napvihar bek�vetkez�s�ig h�tral�v� id�
	 */
	long timeToSunStorm;
	
	/**
	 * a Nappal k�zvetlen�l szomsz�dos aszteroid�k list�ja
	 */
	ArrayList<Asteroid> neighboringAsteroids = new ArrayList<Asteroid>();
	
	/**
	 * a j�t�kot reprezent�l� oszt�ly
	 */
	Game game;
	
	//-------------------------------------------------------------
	
	/**
	 * Ha a timeToSunStorm attrib�tum pozit�v, akkor eggyel cs�kkenti 
	 * (v�rakozik). Ha pedig 0, akkor napvihart ind�t a sunStorm() f�ggv�nyt h�vva,
	 *  majd a timeToSunStorm-nak be�ll�t egy v�letlen eg�sz �rt�ket.
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Lek�rdezi a Game-t�l az aszteroid�k kollekci�j�t, 
	 * majd egyes�vel megh�vja mindegyik aszteroida destroySurface() f�ggv�ny�t.
	 */
	public void sunStorm() {
		
	}
	
	/**
	 * Meg�llap�tja, hogy az a aszteroida napk�zeli-e
	 * @return
	 */
	public boolean isCloseToSun(Asteroid a) {
		if(this.neighboringAsteroids.contains(a)) {
			return true;
		}
		return false;
	}
	
	public void settimeToSunStorm(long time){
		timeToSunStorm=time;
	}
	
	public void addNeighbor(Asteroid a) {
		this.neighboringAsteroids.add(a);
	}

	
}
