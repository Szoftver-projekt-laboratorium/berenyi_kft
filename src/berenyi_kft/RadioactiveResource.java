package berenyi_kft;

/**
 * Radioaktiv nyersanyagtipust reprezentalo absztrakt ososztaly
 * @author berenyi_kft
 */
public abstract class RadioactiveResource extends Resource {

	/**
	 * Meghivja az aszteroidaja explodedBy(RadioactiveResource rr) metodusat
	 * (aminek hatasara az aszteroida fel fog robbanni).
	 */
	@Override
	public void drilledOut(Asteroid a) {
		System.out.println("RadioactiveResource's drilledOut(a: Asteroid) has been called");
		a.explodedBy(this);
	}
}
