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
	 * A param�terk�nt kapott nyersanyagot hozz�adja a resources kollekci�hoz. 
	 * A recept m�dos�t�s�hoz sz�ks�ges, ha v�ltozn�nak a felt�telek.
	 * @param r
	 */
	public void addResource(Resource r) {
		this.resources.add(r);
		this.resources_backup.add(r);
	}
	
	/**
	 *  A param�terk�nt kapott r nyersanyagot �sszehasonl�tja a resources
	 *   kollekci�ban l�v� nyersanyagokkal, az isCompatibleWith(r: Resource) 
	 *   met�dussal. Amennyiben az isCompatibleWith(r: Resource) f�ggv�ny 
	 *   igaz �rt�kkel t�r vissza valamelyik kollekci�beli nyersanyagra,
	 *   t�rli az adott nyersanyagot a resources kollekci�b�l.
	 * @param r
	 */
	// Lehetne a neve removeIfNeeded, nem booleant sejtető név kellene.
	public void isNeeded(Resource r) {
		for (Resource rBill : resources) {
			if (r.isCompatibleWith(rBill)) {
				resources.remove(rBill);
				break;
			}
		}
	}
	
	/**
	 * Vissza�ll�tja az eredeti receptet, azaz a resources gy�jtem�ny tartalm�t
	 */
	public void reset() {
		resources = resources_backup;
	}
	
	/**
	 * Ha a resources kollekci� �res, igaz �rt�kkel t�r vissza, 
	 * ellenkez� esetben pedig hamissal.
	 * @return
	 */
	public boolean isEmpty() {
		return this.resources.isEmpty();
	}
}
