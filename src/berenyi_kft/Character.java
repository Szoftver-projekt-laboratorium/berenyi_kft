package berenyi_kft;

import java.util.ArrayList;

/**
 * Ez az osztaly absztrakt ososztalykent szolgal a jatek karaktereihez
 * @author berenyi_kft
 */
public abstract class Character {
	
	/**
	 * Az aszterioda, amelyen az adott karakter aktualisan tartozkodik
	 */
	protected Asteroid place;
	
	/**
	 * A karakter altal tarolt nyersanyagok listaja
	 */
	protected ArrayList<Resource> collectedResources = new ArrayList<Resource>();
	
	//----------------------------------------------------------------
	
	/**
	 * Visszater a karakter altal tarolt nyersanyagok 
	 * listajaval, alapertelmezetten egy ures listaval
	 * @return
	 */
	public ArrayList<Resource> getCollectedResources() {
		System.out.println("Character's getCollectedResources has been called");
		return collectedResources;
	}
	
	/**
	 * Atmozog az aktualis aszteroidarol annak egy szomszedjara. 
	 * Eloszor lekerdezi az aktualis aszteroida d-edik szomszedjat 
	 * (a getNeighbor(int d) fuggvennyel). Ezutan eltavolitja magat a 
	 * jelenlegi aszteroidarol (remove(Character c)), 
	 * majd a visszakapott szomszed aszteroidara lep (accept(Character c)).
	 * @param d A szomszed aszteroida sorszamat jelolo szam
	 */
	public void move(int d) {
		System.out.println("Character's move(d: int) has been called");
		Asteroid neighbor = place.getNeighbor(d);
		place.remove(this);
		neighbor.accept(this);
		this.place = neighbor;
	}
	
	/**
	 * Furassal csokkenti az aktualis aszteroida kopenyvastagsagat: 
	 * meghivja az aszteroida drilled() metodusat
	 */
	public void drill() {
		System.out.println("Character's drill() has been called");
		place.drilled();
	}
	
	/**
	 * A karakter halalat, jelenti: eltavolitja a karaktert 
	 * az aszteroidajarol (place.remove(Character c)). 
	 * A leszarmazottakban feluldefinialhato.
	 */
	public void die() {
		System.out.println("Character's die() has been called");
		place.remove(this);
	}
	
	/**
	 * A metodus, amit a karakternek akkor kell vegrehajtania, 
	 * ha az aszteroida, amelyen tartozkodik, felrobban. 
	 * Alapertelmezetten a karakter die() fuggvenyet hivja. 
	 * A leszarmazottak feluldefinialhatjak.
	 */
	public void reactToExplosion() {
		System.out.println("Character's reactToExplosion() has been called");
		die();
	}
	
	/**
	 * Visszater az aszteroidaval, amelyen a karakter aktualisan tartozkodik.
	 * @return
	 */
	public Asteroid getPlace() {
		System.out.println("Character's getPlace() has been called");
		return place;
	}

	/**
	 * Beallitja a karakter tartozkodasi helyet.
	 * @param a Az aszteroida, amelyre a karaktert raallitjuk
	 */
	public void setPlace(Asteroid a) {
		System.out.println("Character's setPlace(a: Asteroid) has been called");
		place = a;
	}
}
