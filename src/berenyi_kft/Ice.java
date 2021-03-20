package berenyi_kft;

/**
 * 
 * @author berenyi_kft
 *
 */
public class Ice extends Resource{

	/**
	 * Hat�s�ra a v�zj�g elszublim�l: 
	 * megh�vja az asteroid.removeResource()
	 * met�dust a saj�t aszteroid�j�n.
	 */
	@Override
	public void drilledOut(Asteroid a) {
		System.out.println("Ice's drilledOut(a: Asteroid) has been called");
		a.removeResource();
	}

	/**
	 * �sszehasonl�tja mag�t a param�terk�nt kapott nyersanyaggal 
	 * �s egyez�s eset�n igaz �rt�kkel t�r vissza, 
	 * ellenkez� esetben hamissal.
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		System.out.println("Ice's isCompatibleWith(r: Resource) has been called");
		if(this.getClass().equals(r.getClass()))
			return true;
		return false;
	}

}
