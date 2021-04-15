package berenyi_kft;

/**
 * A jatekban elofordulo nyersanyagok absztrakt ososztalya
 * @author berenyi_kft
 */
public abstract class Resource implements Cloneable {
	
	/**
	 * Az aszteroida, amelynek magjaban az adott nyersanyagegyseg elhelyezkedik.
	 * Ha a nyersanyag nem egy aszteroida magjaban talalhato, akkor erteke null.
	 */
	// protected Asteroid asteroid;
	
	protected Proto proto;
	
	//--------------------------------------------------------------
	
	/**
	 * Azt a helyzetet kezeli, amikor a nyersanyag e�ppen egy napkozelben levo, 
	 * megfurt aszteroida magjaban talalhato. A metodus altalanos esetben nem csinal semmit, 
	 * de a specialis mukodesu leszarmazott osztalyokban feluldefinialhato.
	 */
	public void drilledOut(Asteroid a) {
		 System.out.println("Resource's drilledOut(a: Asteroid) has been called");
	}
	
	/**
	 * Beallitja az aszteroidat, amelynek magjaban a nyersanyagegyseg aktualisan
	 * megtalalhato, (peldaul a restore(Resource r) muvelet soran).
	 * Az aszteroidaja null-ra is allithato, (peldaul a nyersanyag kibanyaszasa eseten).
	 * @param a Az aszteroida/null, amelyre a nyersanyagot tartalmazo aszteroidat beallitjuk
	 */
	/*public void setAsteroid(Asteroid a) {
		System.out.println("Resource's setAsteroid(a: Asteroid) has been called");
		asteroid = a;
	}*/
	
	/**
	 * Osszehasonlitja magat a parameterkent kapott nyersanyaggal a tipusa szerint,
	 * es visszater az egyezes logikai ertekevel.
	 * @param r A masik nyersanyag, amelyikkel ezt a nyersanyagot osszehasonlitjuk
	 * @return Pontosan akkor true, ha a r ugyanolyan tipusu nyersanyag, mint
	 * 	       ez a nyersanyag (this).
	 */
	public abstract boolean isCompatibleWith(Resource r);
	
	/**
	 * Visszater a nyersanyagegyseg egy klonjaval.
	 * @return A nyersanyag klonozott masolata
	 */
	public Resource clone() {
		try {
			return (Resource)super.clone();	
		} catch (CloneNotSupportedException e) {
			 return null;
		}	
	}
}
