package berenyi_kft;

/**
 * A j�t�kban lev� nyersanyagok �soszt�lya.
 * @author berenyi_kft
 *
 */
public abstract class Resource {

	/**
	 * : a telepes, akin�l az adott nyersanyagegys�g t�rol�dik
	 *  (ha �ppen telepesn�l tal�lhat�)
	 */
	Settler settler;
	
	/**
	 * az aszteroida, amelynek magj�ban az adott nyersanyag elhelyezkedik 
	 * (ha �ppen aszteroida magj�ban tal�lhat�)
	 */
	Asteroid asteroid;
	
	//--------------------------------------------------------------
	
	/**
	 * Azt a helyzetet kezeli, amikor a nyersanyag �ppen egy napk�zelben lev�, 
	 * megf�rt aszteroida magj�ban tal�lhat�. A met�dus �ltal�nos esetben nem csin�l semmit, 
	 * de a speci�lis m�k�d�s� lesz�rmazott oszt�lyokban fel�ldefini�lhat�.
	 */
	public abstract void drilledOut();
	
	/**
	 * �sszehasonl�tja mag�t a param�terk�nt kapott nyersanyaggal �s egyez�s eset�n
	 * igaz �rt�kkel t�r vissza, ellenkez� esetben hamissal.
	 * @param r
	 * @return
	 */
	public abstract boolean isCompatibleWith(Resource r);
	
	
}
