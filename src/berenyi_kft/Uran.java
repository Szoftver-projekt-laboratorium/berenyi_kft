package berenyi_kft;

/**
 * Az urán nyersanyagot reprezentálja.
 * @author berenyi_kft
 *
 */
public class Uran extends RadioactiveResource {

	/**
	 * Összehasonlítja magát a paraméterként kapott nyersanyaggal 
	 * és egyezés esetén igaz értékkel tér vissza, 
	 * ellenkezõ esetben hamissal.
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		System.out.println("Uran's isCompatibleWith(r: Resource) has been called");
		if(this.getClass().equals(r.getClass()))
			return true;
		return false;
	}

	
}
