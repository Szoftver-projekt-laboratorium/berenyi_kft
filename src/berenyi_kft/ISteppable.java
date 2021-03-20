package berenyi_kft;

/**
 * Olyan objektumot jel�l, amely a j�t�kban k�ls� ir�ny�t�s 
 * (j�t�kos aktor beavatkoz�sa) n�lk�l l�ptethet�
 * @author berenyi_kft
 *
 */
public interface ISteppable {
	
	/**
	 * A m�velet, amelyet az objektum az adott l�p�sben v�gez.
	 */
	public void step();

}
