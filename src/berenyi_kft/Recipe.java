package berenyi_kft;

import java.util.ArrayList;

/**
 * Epiteshez szukseges receptet reprezentalo osztaly.
 * Feladata a jatekban felepitheto dolgok letrehozasahoz
 * szukseges nyersanyagok listazasa
 * @author berenyi_kft
 */
public class Recipe {
	
	/**
	 * A receptben szereplo nyersanyagok (Resource-ok) listaja.
	 * Egy-egy fajta nyersanyagbol tobb egyseget is tartalmazhat.
	 * A szukseges nyersanyagok megletenek ellenorzese checklist-szeruen
	 * tortenik: epites eseten az epiteni szandekozo telepesnel meglevo,
	 * szukseges nyersanyagok eltavolitodnak a listabol.
	 */
	private ArrayList<Resource> resources = new ArrayList<Resource>();
	
	/**
	 * Masolat az eredeti listarol.
	 * A resources lista visszaallitasahoz szukseges az ellenorzesek utan
	 */
	private ArrayList<Resource> resources_backup = new ArrayList<Resource>();
	
	//-----------------------------------------------------
	
	/**
	 * Visszaadja a receptben található nyersanyagok listáját.
	 * @return
	 */
	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	/**
	 * A parameterkent kapott nyersanyagot hozzaadja a resources kollekciohoz. 
	 * A recept modositasahoz szukseges, ha valtoznanak a feltetelek.
	 * @param r A receptet bovito nyersanyagegyseg.
	 */
	public void addResource(Resource r) {
		System.out.println("Settler's addResource(r: Resource) has been called");
		this.resources.add(r);
		Resource r_clone = r.clone();
		this.resources_backup.add(r_clone);
	}
	
	/**
	 *  A parameterkent kapott r nyersanyagot osszehasonlitja a resources
	 *  kollekcioban levo nyersanyagokkal, az isCompatibleWith(r: Resource) 
	 *  metodussal. Amennyiben az isCompatibleWith(r: Resource) fuggveny 
	 *  igaz ertekkel ter vissza valamelyik kollekciobeli nyersanyagra,
	 *  akkor torli az adott nyersanyagot a resources kollekciobol.
	 * @param r A receptbol eltavolitando tipusu nyersanyagegyseg
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
	 * Visszaallitja az eredeti receptet, azaz a resources gyujtemeny tartalmat.
	 */
	public void reset() {
		resources = (ArrayList<Resource>)resources_backup.clone();
	}
	
	/**
	 * Ha a resources kollekcio ures, igaz ertekkel ter vissza, 
	 * ellenkezo esetben pedig hamissal.
	 * @return Pontosan akkor true, ha a recept ures (kiuresedett)
	 */
	public boolean isEmpty() {
		System.out.println("Recipe's isEmpty() has been called");
		return this.resources.isEmpty();
	}
}
