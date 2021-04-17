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
		System.out.println("Coal's isCompatibleWith(r: Resource) has been called");
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
		Proto.getAllObjects().addCoal(coClone);
		return coClone;
	}
}
