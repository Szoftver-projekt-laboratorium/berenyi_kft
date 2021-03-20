package berenyi_kft;

/**
 * RadioactiveResource-t megval�s�t� abstract �soszt�ly
 * @author berenyi_kft
 *
 */
public abstract class RadioactiveResource extends Resource {

	/**
	 * Megh�vja az aszteroid�ja 
	 * explodedBy(RadioactiveResource rr) met�dus�t (robban).
	 */
	@Override
	public void drilledOut(Asteroid a) {
		System.out.println("RadioactiveResource's drilledOut(a: Asteroid) has been called");
		a.explodedBy(this);
	}

	

}
