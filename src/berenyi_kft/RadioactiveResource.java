package berenyi_kft;

/**
 * Radioaktiv nyersanyagtipust reprezentalo absztrakt ososztaly
 * @author berenyi_kft
 */
public abstract class RadioactiveResource extends Resource {

	/**
	 * A radioaktiv nyersanyagegyseg berobban. Meghivja az aszteroidaja
	 * explodedBy(RadioactiveResource rr) metodusat, amelynek hatasara
	 * az aszteroida is felrobban.
	 */
	@Override
	public void drilledOut(Asteroid a) {
		// System.out.println("RadioactiveResource's drilledOut(a: Asteroid) has been called");
		a.explodedBy(this);
	}
}
