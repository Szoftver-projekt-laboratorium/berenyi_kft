package berenyi_kft;

/**
 * A sz�n nyersanyagot reprezent�lja.
 * @author berenyi_kft
 *
 */
public class Coal extends Resource {

	
	/**
	 * /**
	 * �sszehasonl�tja mag�t a param�terk�nt kapott nyersanyaggal 
	 * �s egyez�s eset�n igaz �rt�kkel t�r vissza, 
	 * ellenkez� esetben hamissal.
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		System.out.println("Coal's isCompatibleWith(r: Resource) has been called");
		if(this.getClass().equals(r.getClass()))
			return true;
		return false;
	}

}
