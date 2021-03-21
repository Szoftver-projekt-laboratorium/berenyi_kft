package berenyi_kft;

import java.util.ArrayList;

/**
 * Recipe-t reprezent�l� oszt�ly
 * A j�t�kban fel�p�thet� dolgok l�trehoz�s�hoz sz�ks�ges nyersanyagok list�j�t tartalmazza
 * @author berenyi_kft
 *
 */
public class Recipe {

	ArrayList<Resource> resources = new ArrayList<Resource>();
	ArrayList<Resource> resources_backup = new ArrayList<Resource>();
	
	//-----------------------------------------------------
	
	/**
	 * Visszaadja a receptben található nyersanyagok listáját.
	 * @return
	 */
	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	/**
	 * A param�terk�nt kapott nyersanyagot hozz�adja a resources kollekci�hoz. 
	 * A recept m�dos�t�s�hoz sz�ks�ges, ha v�ltozn�nak a felt�telek.
	 * @param r
	 */
	public void addResource(Resource r) {
		System.out.println("Settler's addResource(r: Resource) has been called");
		this.resources.add(r);
		Resource r_clone = r.clone();
		if(r_clone == null) System.out.println("r_clone is null");
		if(r == r_clone) System.out.println("r_clone is similar to r");
		this.resources_backup.add(r_clone);
	}
	
	/**
	 *  A param�terk�nt kapott r nyersanyagot �sszehasonl�tja a resources
	 *   kollekci�ban l�v� nyersanyagokkal, az isCompatibleWith(r: Resource) 
	 *   met�dussal. Amennyiben az isCompatibleWith(r: Resource) f�ggv�ny 
	 *   igaz �rt�kkel t�r vissza valamelyik kollekci�beli nyersanyagra,
	 *   t�rli az adott nyersanyagot a resources kollekci�b�l.
	 * @param r
	 */
	public boolean isNeeded(Resource r) {
		System.out.println("Recipe's isNeeded(r: Resource) has been called");
		for (int i = resources.size()-1; i >= 0; i--) {
			Resource rBill = resources.get(i);
			if (r.isCompatibleWith(rBill)) {
				resources.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Vissza�ll�tja az eredeti receptet, azaz a resources gy�jtem�ny tartalm�t
	 */
	public void reset() {
		resources = (ArrayList<Resource>)resources_backup.clone();
	}
	
	/**
	 * Ha a resources kollekci� �res, igaz �rt�kkel t�r vissza, 
	 * ellenkez� esetben pedig hamissal.
	 * @return
	 */
	public boolean isEmpty() {
		System.out.println("Recipe's isEmpty() has been called");
		return this.resources.isEmpty();
	}
}
