package berenyi_kft;

/**
 * A jatekban elofordulo nyersanyagok absztrakt ososztalya
 * 
 * @author berenyi_kft
 */
public abstract class Resource implements Cloneable {

	/**
	 * Azt a helyzetet kezeli, amikor a nyersanyag eppen egy napkozelben levo,
	 * megfurt aszteroida magjaban talalhato. A metodus altalanos esetben nem csinal
	 * semmit, de a specialis mukodesu leszarmazott osztalyokban feluldefinialhato.
	 */
	public void drilledOut(Asteroid a) {
		// System.out.println("Resource's drilledOut(a: Asteroid) has been called");
	}

	/**
	 * Osszehasonlitja magat a parameterkent kapott nyersanyaggal a tipusa szerint,
	 * es visszater az egyezes logikai ertekevel.
	 * 
	 * @param r A masik nyersanyag, amelyikkel ezt a nyersanyagot osszehasonlitjuk
	 * @return Pontosan akkor true, ha a r ugyanolyan tipusu nyersanyag, mint ez a
	 *         nyersanyag (this).
	 */
	public abstract boolean isCompatibleWith(Resource r);

	/**
	 * Visszater a nyersanyagegyseg egy klonjaval.
	 * 
	 * @return A nyersanyag klonozott masolata
	 */
	public Resource clone() {
		try {
			Resource rClone = (Resource) super.clone();
			Proto.println(Proto.getId(rClone) + " <- " + Proto.getId(this) + ".clone()");
			return rClone;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * A nyersanyagegyseg egy uj azonositoval eltarolodik a Proto osztaly allObjects
	 * nyilvantartasaban.
	 */
	public abstract void addToGame();

	/**
	 * A nyersanyagegyseg megsemmisul, eltunik a jatekbol.
	 */
	public abstract void removeFromGame();

}
