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
	public void drilledOut(Asteroid a) {
		a.removeResource();
		System.out.println("Ice's drilledOut(a: Asteroid) has been called");
	}

	/**
	 * Összehasonlítja magát a paraméterként kapott nyersanyaggal 
	 * és egyezés esetén igaz értékkel tér vissza, 
	 * ellenkezõ esetben hamissal.
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		System.out.println("Ice's isCompatibleWith(r: Resource) has been called");
		if(this.getClass().equals(r.getClass()))
			return true;
		return false;
	}

}
