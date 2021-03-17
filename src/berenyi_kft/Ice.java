package berenyi_kft;

/**
 * 
 * @author berenyi_kft
 *
 */
public class Ice extends Resource{

	/**
	 * Hatására a vízjég elszublimál: 
	 * meghívja az asteroid.removeResource()
	 * metódust a saját aszteroidáján.
	 */
	@Override
	public void drilledOut() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Összehasonlítja magát a paraméterként kapott nyersanyaggal 
	 * és egyezés esetén igaz értékkel tér vissza, 
	 * ellenkezõ esetben hamissal.
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		// TODO Auto-generated method stub
		return false;
	}

}
