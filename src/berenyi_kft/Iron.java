package berenyi_kft;

/**
 * Iron-t megvalósító osztály
 * A vas nyersanyagot reprezentálja.
 * @author berenyi_kft
 *
 */
public class Iron extends Resource{


	/**
	 * Összehasonlítja magát a paraméterként kapott nyersanyaggal 
	 * és egyezés esetén igaz értékkel tér vissza, 
	 * ellenkezõ esetben hamissal.
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		System.out.println("Iron's isCompatibleWith(r: Resource) has been called");
		if(this.getClass().equals(r.getClass()))
			return true;
		return false;
	}

	
}
