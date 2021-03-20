package berenyi_kft;

import java.util.ArrayList;

/**
 * Ez az oszt�ly absztrakt �soszt�lyk�nt szolg�l a j�t�k karaktereihez
 * @author berenyi_kft
 *
 */
public abstract class Character {
	
	/**
	 * az aszterioda, amelyen az adott karakter aktu�lisan tart�zkodik
	 */
	Asteroid place;
	
	
	/**
	 * A karakter �ltal t�rolt nyersanyagok list�ja
	 */
	ArrayList<Resource> collectedResources = new ArrayList<Resource>();
	
	
	//----------------------------------------------------------------
	
	/**
	 * Visszat�r a karakter �ltal t�rolt nyersanyagok 
	 * list�j�val, alap�rtelmezetten egy �res list�val
	 * @return
	 */
	public ArrayList<Resource> getCollectedResources() {
		return collectedResources;
	}
	
	/**
	 * �tmozog az aktu�lis aszteroid�r�l annak egy szomsz�dj�ra. 
	 * El�sz�r lek�rdezi az aktu�lis aszteroida d-edik szomsz�dj�t 
	 * (a getNeighbor(int d) f�ggv�nnyel). Ezut�n elt�vol�tja mag�t a 
	 * jelenlegi aszteroid�r�l (remove(Character c)), 
	 * majd a visszakapott szomsz�d aszteroid�ra l�p (accept(Character c))
	 * @param d
	 */
	public void move(int d) {
		Asteroid neighbor = place.getNeighbor(d);
		place.remove(this);
		neighbor.accept(this);
	}
	
	/**
	 * Cs�kkenti az aktu�lis aszteroida k�penyvastags�g�t: 
	 * megh�vja az aszteroida drilled() met�dus�t
	 */
	public void drill() {
		place.drilled();
	}
	
	/**
	 * A karakter hal�l�t, jelenti: elt�vol�tja a karaktert 
	 * az aszteroid�j�r�l (place.remove(Character c)). 
	 * A lesz�rmazottakban fel�ldefini�lhat�.
	 */
	public void die() {
		place.remove(this);
	}
	
	/**
	 * A met�dus, amit a karakternek akkor kell v�grehajtania, 
	 * ha az aszteroida, amelyen tart�zkodik, felrobban. 
	 * Alap�rtelmezetten a karakter die() f�ggv�ny�t h�vja. 
	 * A lesz�rmazottak fel�ldefini�lhatj�k.
	 */
	public void reactToExplosion() {
		die();
	}
	
	
}
