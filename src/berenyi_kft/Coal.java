package berenyi_kft;

/**
 * A sz�n nyersanyagot reprezent�lja.
 * @author berenyi_kft
 *
 */
public class Coal extends Resource {

	@Override
	public void drilledOut() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * /**
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
