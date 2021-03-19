package berenyi_kft;

/**
 * RadioactiveResource-t megvalósító abstract õsosztály
 * @author berenyi_kft
 *
 */
public abstract class RadioactiveResource extends Resource {

	/**
	 * Meghívja az aszteroidája 
	 * explodedBy(RadioactiveResource rr) metódusát (robban).
	 */
	@Override
	public void drilledOut(Asteroid a) {
		a.explodedBy(this);
		 System.out.println("RadioactiveResource's drilledOut(a: Asteroid) has been called");
	}

	

}
