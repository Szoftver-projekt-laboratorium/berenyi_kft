package berenyi_kft;

import java.util.ArrayList;

/**
 * Ez az osztaly absztrakt ososztalykent szolgal a jatek karaktereihez
 * 
 * @author berenyi_kft
 */
public abstract class Character {

	/**
	 * Az aszterioda, amelyen az adott karakter aktualisan tartozkodik
	 */
	protected Asteroid place = null;

	/**
	 * A jatek idozitoje
	 */
	protected Timer timer = null;

	// ----------------------------------------------------------------

	/**
	 * Beallitja a timer idozitot a karakterben.
	 * 
	 * @param timer Az idozito a jatekban
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	};

	/**
	 * Az a fuggveny, amit a karakter akkor hajt vegre, amikor egy aszteroidara lep,
	 * es az aszteroida fogadja. Alapertelmezetten az aszteroida
	 * acceptRegularCharacter(Character c) metodusat hivja. A karakter
	 * leszarmazottak feluldefinialhatjak, a Settler ilyen.
	 * 
	 * @param a Az aszteroida, amelyre a karakter lep
	 */
	public void acceptedBy(Asteroid a) {
		a.acceptRegularCharacter(this);
	}

	/**
	 * Visszater a karakter altal tarolt nyersanyagok listajaval, alapertelmezetten
	 * egy ures listaval
	 * 
	 * @return
	 */
	public ArrayList<Resource> getCollectedResources() {
		return new ArrayList<Resource>();
	}

	/**
	 * Atmozog az aktualis aszteroidarol annak egy szomszedjara. Eloszor lekerdezi
	 * az aktualis aszteroida d-edik szomszedjat (a getNeighbor(int d) fuggvennyel).
	 * Ezutan eltavolitja magat a jelenlegi aszteroidarol (remove(Character c)),
	 * majd a visszakapott szomszed aszteroidara lep (accept(Character c)).
	 * 
	 * @param d A szomszed aszteroida sorszamat jelolo szam
	 */
	public void move(int d) {
		Proto.println(Proto.getId(this) + ".move(" + d + ")");
		Proto.incrTabs();
		Asteroid neighbor = place.getNeighbor(d);
		if (neighbor != null) {
			place.remove(this);
			neighbor.accept(this);
			this.place = neighbor;
		}
		Proto.decrTabs();
	}

	/**
	 * A karakter halalat jelenti: eltavolitja a karaktert az aszteroidajarol
	 * (place.remove(Character c)). A leszarmazottakban feluldefinialhato.
	 */
	public void die() {
		place.remove(this);
	}

	/**
	 * A metodus, amit a karakternek akkor kell vegrehajtania, ha az aszteroida,
	 * amelyen tartozkodik, felrobban. Alapertelmezetten a karakter die() fuggvenyet
	 * hivja. A leszarmazottak feluldefinialhatjak.
	 */
	public void reactToExplosion() {
		Proto.println(Proto.getId(this) + ".reactToExplosion()");
		Proto.incrTabs();
		die();
		Proto.decrTabs();
	}

	/**
	 * Visszater az aszteroidaval, amelyen a karakter aktualisan tartozkodik.
	 * 
	 * @return
	 */
	public Asteroid getPlace() {
		return place;
	}

	/**
	 * Beallitja a karakter tartozkodasi helyet.
	 * 
	 * @param a Az aszteroida, amelyre a karaktert raallitjuk
	 */
	public void setPlace(Asteroid a) {
		place = a;
	}
}
