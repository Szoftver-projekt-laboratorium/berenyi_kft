package berenyi_kft;

/**
 * A szen nyersanyagot reprezentalja
 * @author berenyi_kft
 */
public class Coal extends Resource {
	
	/**
	 * Osszehasonlitja magat a parameterkent kapott nyersanyaggal a tipusa szerint,
	 * es visszater az egyezes logikai ertekevel.
	 * @param r A masik nyersanyag, amelyikkel ezt a nyersanyagot osszehasonlitjuk
	 * @return Pontosan akkor true, ha r is Coal tipusu nyersanyag
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		// System.out.println("Coal's isCompatibleWith(r: Resource) has been called");
		if (this.getClass().equals(r.getClass()))
			return true;
		return false;
	}
	
	/**
	 * Visszater a szen nyersanyagegyseg egy klonjaval.
	 * @return A nyersanyag klonozott masolata
	 */
	@Override
	public Coal clone() {
		Coal coClone = (Coal)super.clone();
		return coClone;
	}
	
	/**
	 * A szen nyersanyagegyseg egy uj azonositoval eltarolodik
	 * a Proto osztaly allObjects nyilvantartasaban.
	 */
	@Override
	public void addToGame() {
		Proto.getAllObjects().addCoal(this);
	}
	
	/**
	 * A szen nyersanyagegyseg megsemmisul, eltunik a jatekbol.
	 */
	@Override
	public void removeFromGame() {
		Proto.getAllObjects().removeCoal(this);
	}
}
