package berenyi_kft;

/**
 * A szén nyersanyagot reprezentálja.
 * @author berenyi_kft
 *
 */
public class Coal extends Resource {

	@Override
	public void drilledOut() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * /**
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
