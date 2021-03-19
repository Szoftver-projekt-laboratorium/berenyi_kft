package berenyi_kft;

/**
 * Iron-t megval�s�t� oszt�ly
 * A vas nyersanyagot reprezent�lja.
 * @author berenyi_kft
 *
 */
public class Iron extends Resource{


	/**
	 * �sszehasonl�tja mag�t a param�terk�nt kapott nyersanyaggal 
	 * �s egyez�s eset�n igaz �rt�kkel t�r vissza, 
	 * ellenkez� esetben hamissal.
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		System.out.println("Iron's isCompatibleWith(r: Resource) has been called");
		if(this.getClass().equals(r.getClass()))
			return true;
		return false;
	}

	
}
