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
	public void drilledOut() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * �sszehasonl�tja mag�t a param�terk�nt kapott nyersanyaggal 
	 * �s egyez�s eset�n igaz �rt�kkel t�r vissza, 
	 * ellenkez� esetben hamissal.
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		// TODO Auto-generated method stub
		return false;
	}

}
