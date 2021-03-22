package berenyi_kft;

/**
 * Az uran nyersanyagot reprezentalja
 * @author berenyi_kft
 */
public class Uranium extends RadioactiveResource {
	
	/**
	 * Osszehasonlitja magat a parameterkent kapott nyersanyaggal a tipusa szerint,
	 * es visszater az egyezes logikai ertekevel.
	 * @param r A masik nyersanyag, amelyikkel ezt a nyersanyagot osszehasonlitjuk
	 * @return Pontosan akkor true, ha r is Uranium tipusu nyersanyag
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		System.out.println("Uranium's isCompatibleWith(r: Resource) has been called");
		if(this.getClass().equals(r.getClass()))
			return true;
		return false;
	}
}
