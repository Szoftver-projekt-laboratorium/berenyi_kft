package berenyi_kft;

import java.util.ArrayList;

/**
 * Recipe-t reprezentáló osztály
 * A játékban felépíthetõ dolgok létrehozásához szükséges nyersanyagok listáját tartalmazza
 * @author berenyi_kft
 *
 */
public class Recipe {

	ArrayList<Resource> resources = new ArrayList<Resource>();
	
	//-----------------------------------------------------
	
	/**
	 * A paraméterként kapott nyersanyagot hozzáadja a resources kollekcióhoz. 
	 * A recept módosításához szükséges, ha változnának a feltételek.
	 * @param r
	 */
	public void addResource(Resource r) {
		this.resources.add(r);
	}
	
	/**
	 *  A paraméterként kapott r nyersanyagot összehasonlítja a resources
	 *   kollekcióban lévõ nyersanyagokkal, az isCompatibleWith(r: Resource) 
	 *   metódussal. Amennyiben az isCompatibleWith(r: Resource) függvény 
	 *   igaz értékkel tér vissza valamelyik kollekcióbeli nyersanyagra,
	 *   törli az adott nyersanyagot a resources kollekcióból.
	 * @param r
	 */
	public void isNeeded(Resource r) {
		
	}
	
	/**
	 * Visszaállítja az eredeti receptet, azaz a resources gyûjtemény tartalmát
	 */
	public void reset() {
		
	}
	
	/**
	 * Ha a resources kollekció üres, igaz értékkel tér vissza, 
	 * ellenkezõ esetben pedig hamissal.
	 * @return
	 */
	public boolean isEmpty() {
		return this.resources.isEmpty();
	}
}
