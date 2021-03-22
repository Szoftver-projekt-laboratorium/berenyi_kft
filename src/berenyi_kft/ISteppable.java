package berenyi_kft;

/**
 * Olyan objektumot jelol, amely a jatekban kulso iranyitas 
 * (jatekos aktor beavatkozasa) nelkul leptetheto
 * @author berenyi_kft
 */
public interface ISteppable {
	
	/**
	 * A mu�velet, amelyet az objektum az adott lepesben vegez.
	 */
	public void step();

}
