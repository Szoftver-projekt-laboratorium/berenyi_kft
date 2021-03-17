package berenyi_kft;

/**
 * Teleportkaput reprezentáló osztály
 * @author berenyi_kft
 *
 */
public class TeleportingGate {

	/**
	 * az adott teleportkapu párja, amellyel összeköttetésben áll 
	 */
	TeleportingGate pair;
	
	/**
	 * az aszteroida, amely körül az adott teleportkapu kering
	 */
	Asteroid asteroid;
	
	/**
	 * a telepes, aki tárolja a létrehozott, de még pályára nem állított teleportkaput
	 */
	Settler settler;
	
	//--------------------------------------------------------------
	
	/**
	 * Visszaadja a teleportkapu pair párját.
	 */
	public TeleportingGate getPair() {
		return this.pair;
	}
	
	/**
	 * Beállítja tg-t a teleportkapu párjaként
	 * @param tg
	 */
	public void setPair(TeleportingGate tg) {
		this.pair = tg;
	}
	
	/**
	 * Visszaadja az aszteroidát, amely körül a teleportkapu kering.
	 * Ha a kaput még nem állították pályára, akkor null-lal tér vissza.
	 * @return
	 */
	public Asteroid getAsteroid() {
		return this.asteroid;
	}
	
	/**
	 * Beállítja a teleportkapu aszteroidáját, amely körül keringeni fog.
	 * @param a
	 */
	public void setAsteroid(Asteroid a) {
		this.asteroid = a;
	}
	
	
	/**
	 * Eltávolítja a párját (pair) annak aszteroidájáról/ûrhajójáról attól függõen,
	 * hogy azt már pályára állították-e (remove(TeleportingGate tg)). 
	 * Beállítja a párjának a párját null-ra (megszünteti az összeköttetést), 
	 * végül saját magát is eltávolítja az aszteroidájáról/ûrhajójáról. 
	 * Ha a függvényhívás elején a teleportkapu párja már null, 
	 * akkor csak magát távolítja el: ekkor a másik teleportkapu semmisült meg elõbb, 
	 * és az már megszüntette ennek a kapunak a kapcsolatait is.
	 */
	public void die() {
		
	}
	
	
	
}
