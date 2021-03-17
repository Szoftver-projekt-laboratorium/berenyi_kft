package berenyi_kft;

import java.util.ArrayList;

/**
 * Egy �rajel jelleg� id�z�t�t reprezent�l, 
 * amely periodikusan l�ptet minden l�ptethet� objektumot a j�t�kban.
 * @author berenyi_kft
 *
 */
public class Timer {

	/**
	 * a j�t�k l�ptethet� objektumainak list�ja
	 */
	ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();
	
	//----------------------------------------------
	
	/**
	 * Hozz�adja s-t a steppables list�hoz
	 * @param s
	 */
	public void addSteppable(ISteppable s) {
		this.steppables.add(s);
	}
	
	/**
	 * Elt�vol�tja s-t a steppables list�b�l.
	 */
	public void removeSteppable(ISteppable s) {
		if(this.steppables.contains(s)) {
			this.steppables.remove(s);
		}
	}
	
	/**
	 * : Minden steppable listabeli objektumot l�ptet �gy, 
	 * hogy megh�vja a step() f�ggv�ny�t.
	 */
	public void tick() {
		this.steppables.forEach((si) -> {si.step();});
	}
	
	
}
