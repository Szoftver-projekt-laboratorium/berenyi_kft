package berenyi_kft;

/**
 * A játékban levõ nyersanyagok õsosztálya.
 * @author berenyi_kft
 *
 */
public abstract class Resource {

	/**
	 * : a telepes, akinél az adott nyersanyagegység tárolódik
	 *  (ha éppen telepesnél található)
	 */
	Settler settler;
	
	/**
	 * az aszteroida, amelynek magjában az adott nyersanyag elhelyezkedik 
	 * (ha éppen aszteroida magjában található)
	 */
	Asteroid asteroid;
	
	//--------------------------------------------------------------
	
	/**
	 * Azt a helyzetet kezeli, amikor a nyersanyag éppen egy napközelben levõ, 
	 * megfúrt aszteroida magjában található. A metódus általános esetben nem csinál semmit, 
	 * de a speciális mûködésû leszármazott osztályokban felüldefiniálható.
	 */
	public abstract void drilledOut();
	
	/**
	 * Összehasonlítja magát a paraméterként kapott nyersanyaggal és egyezés esetén
	 * igaz értékkel tér vissza, ellenkezõ esetben hamissal.
	 * @param r
	 * @return
	 */
	public abstract boolean isCompatibleWith(Resource r);
	
	
}
