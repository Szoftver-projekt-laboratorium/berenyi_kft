package berenyi_kft;

// TODO: Nevezzük át Uraniumra.
/**
 * Az ur�n nyersanyagot reprezent�lja.
 * @author berenyi_kft
 *
 */
public class Uran extends RadioactiveResource {

	/**
	 * �sszehasonl�tja mag�t a param�terk�nt kapott nyersanyaggal 
	 * �s egyez�s eset�n igaz �rt�kkel t�r vissza, 
	 * ellenkez� esetben hamissal.
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		System.out.println("Uran's isCompatibleWith(r: Resource) has been called");
		if(this.getClass().equals(r.getClass()))
			return true;
		return false;
	}
	
	@Override
	public void drilledOut(Asteroid a) {
		System.out.println("Uran's drilledOut(a: Asteroid) has been called");
		asteroid.explodedBy(this);
	}
	
}
