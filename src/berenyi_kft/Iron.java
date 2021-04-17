package berenyi_kft;

/**
 * A vas nyersanyagot reprezentalja
 * @author berenyi_kft
 */
public class Iron extends Resource{

	/**
	 * Osszehasonlitja magat a parameterkent kapott nyersanyaggal a tipusa szerint,
	 * es visszater az egyezes logikai ertekevel.
	 * @param r A masik nyersanyag, amelyikkel ezt a nyersanyagot osszehasonlitjuk
	 * @return Pontosan akkor true, ha r is Iron tipusu nyersanyag
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		// System.out.println("Iron's isCompatibleWith(r: Resource) has been called");
		if (this.getClass().equals(r.getClass()))
			return true;
		return false;
	}
	
	/**
	 * Visszater a vas nyersanyagegyseg egy klonjaval.
	 * @return A nyersanyag klonozott masolata
	 */
	@Override
	public Iron clone() {
		Iron irClone = (Iron)super.clone();
		Proto.getAllObjects().addIron(irClone);
		return irClone;
	}
	
	/**
	 * A vas nyersanyagegyseg megsemmisul, eltunik a jatekbol.
	 */
	@Override
	public void removeFromGame() {
		Proto.getAllObjects().removeIron(this);
	}
}
