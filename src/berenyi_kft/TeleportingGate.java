package berenyi_kft;

/**
 * Teleportkaput reprezent�l� oszt�ly
 * @author berenyi_kft
 *
 */
public class TeleportingGate {

	/**
	 * az adott teleportkapu p�rja, amellyel �sszek�ttet�sben �ll 
	 */
	TeleportingGate pair;
	
	/**
	 * az aszteroida, amely k�r�l az adott teleportkapu kering
	 */
	Asteroid asteroid;
	
	/**
	 * a telepes, aki t�rolja a l�trehozott, de m�g p�ly�ra nem �ll�tott teleportkaput
	 */
	Settler settler;
	
	//--------------------------------------------------------------
	
	/**
	 * Visszaadja a teleportkapu pair p�rj�t.
	 */
	public TeleportingGate getPair() {
		return this.pair;
	}
	
	/**
	 * Be�ll�tja tg-t a teleportkapu p�rjak�nt
	 * @param tg
	 */
	public void setPair(TeleportingGate tg) {
		this.pair = tg;
	}
	
	/**
	 * Visszaadja az aszteroid�t, amely k�r�l a teleportkapu kering.
	 * Ha a kaput m�g nem �ll�tott�k p�ly�ra, akkor null-lal t�r vissza.
	 * @return
	 */
	public Asteroid getAsteroid() {
		return this.asteroid;
	}
	
	/**
	 * Be�ll�tja a teleportkapu aszteroid�j�t, amely k�r�l keringeni fog.
	 * @param a
	 */
	public void setAsteroid(Asteroid a) {
		this.asteroid = a;
	}
	
	
	/**
	 * Elt�vol�tja a p�rj�t (pair) annak aszteroid�j�r�l/�rhaj�j�r�l att�l f�gg�en,
	 * hogy azt m�r p�ly�ra �ll�tott�k-e (remove(TeleportingGate tg)). 
	 * Be�ll�tja a p�rj�nak a p�rj�t null-ra (megsz�nteti az �sszek�ttet�st), 
	 * v�g�l saj�t mag�t is elt�vol�tja az aszteroid�j�r�l/�rhaj�j�r�l. 
	 * Ha a f�ggv�nyh�v�s elej�n a teleportkapu p�rja m�r null, 
	 * akkor csak mag�t t�vol�tja el: ekkor a m�sik teleportkapu semmis�lt meg el�bb, 
	 * �s az m�r megsz�ntette ennek a kapunak a kapcsolatait is.
	 */
	public void die() {
		
	}
	
	
	
}
