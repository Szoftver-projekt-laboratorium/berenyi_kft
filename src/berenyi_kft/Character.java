package berenyi_kft;

import java.util.ArrayList;

/**
 * Ez az osztály absztrakt õsosztályként szolgál a játék karaktereihez
 * @author berenyi_kft
 *
 */
public abstract class Character {
	
	/**
	 * az aszterioda, amelyen az adott karakter aktuálisan tartózkodik
	 */
	Asteroid place;
	
	
	/**
	 * A karakter által tárolt nyersanyagok listája
	 */
	//ArrayList<Resource> collectedResources = new ArrayList<Resource>();
	
	
	//----------------------------------------------------------------
	
	/**
	 * Visszatér a karakter által tárolt nyersanyagok 
	 * listájával, alapértelmezetten egy üres listával
	 * @return
	 */
	public abstract ArrayList<Resource> getCollectedResources();
	
	/**
	 * Átmozog az aktuális aszteroidáról annak egy szomszédjára. 
	 * Elõször lekérdezi az aktuális aszteroida d-edik szomszédját 
	 * (a getNeighbor(int d) függvénnyel). Ezután eltávolítja magát a 
	 * jelenlegi aszteroidáról (remove(Character c)), 
	 * majd a visszakapott szomszéd aszteroidára lép (accept(Character c))
	 * @param d
	 */
	public void move(int d) {
		
	}
	
	/**
	 * Csökkenti az aktuális aszteroida köpenyvastagságát: 
	 * meghívja az aszteroida drilled() metódusát
	 */
	public void drill() {
		
	}
	
	/**
	 * A karakter halálát, jelenti: eltávolítja a karaktert 
	 * az aszteroidájáról (place.remove(Character c)). 
	 * A leszármazottakban felüldefiniálható.
	 */
	public void die() {
		
	}
	
	/**
	 * A metódus, amit a karakternek akkor kell végrehajtania, 
	 * ha az aszteroida, amelyen tartózkodik, felrobban. 
	 * Alapértelmezetten a karakter die() függvényét hívja. 
	 * A leszármazottak felüldefiniálhatják.
	 */
	public void reactToExplosion() {
		
	}
	
	
}
