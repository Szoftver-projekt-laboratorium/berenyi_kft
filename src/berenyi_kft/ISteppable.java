package berenyi_kft;

/**
 * Olyan objektumot jelöl, amely a játékban külsõ irányítás 
 * (játékos aktor beavatkozása) nélkül léptethetõ
 * @author berenyi_kft
 *
 */
public interface ISteppable {
	
	/**
	 * A mûvelet, amelyet az objektum az adott lépésben végez.
	 */
	public void step();

}
