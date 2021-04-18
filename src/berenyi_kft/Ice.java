package berenyi_kft;

/**
 * A vizjeg nyersanyagot reprezentalja
 * @author berenyi_kft
 */
public class Ice extends Resource{

	/**
	 * Hatasara a vizjeg elszublimal: meghivja az removeResource()
	 * metodust a sajat aszteroidajan (asteroid), es
	 * eltavolitja magat a jatekbol (removeFromGame()).
	 */
	@Override
	public void drilledOut(Asteroid a) {
		System.out.println("Ice's drilledOut(a: Asteroid) has been called");
		a.removeResource();
		this.removeFromGame();
	}

	/**
	 * Osszehasonlitja magat a parameterkent kapott nyersanyaggal a tipusa szerint,
	 * es visszater az egyezes logikai ertekevel.
	 * @param r A masik nyersanyag, amelyikkel ezt a nyersanyagot osszehasonlitjuk
	 * @return Pontosan akkor true, ha r is Ice tipusu nyersanyag
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		// System.out.println("Ice's isCompatibleWith(r: Resource) has been called");
		if (this.getClass().equals(r.getClass()))
			return true;
		return false;
	}
	
	/**
	 * Visszater a vizjeg nyersanyagegyseg egy klonjaval.
	 * @return A nyersanyag klonozott masolata
	 */
	@Override
	public Ice clone() {
		Ice icClone = (Ice)super.clone();
		return icClone;
	}
	
	/**
	 * A vizjeg nyersanyagegyseg egy uj azonositoval eltarolodik
	 * a Proto osztaly allObjects nyilvantartasaban.
	 */
	@Override
	public void addToGame() {
		Proto.getAllObjects().addIce(this);
	}
	
	/**
	 * A vizjeg nyersanyagegyseg megsemmisul, eltunik a jatekbol.
	 */
	@Override
	public void removeFromGame() {
		Proto.getAllObjects().removeIce(this);
	}
}
