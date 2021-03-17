package berenyi_kft;

import java.util.ArrayList;

/**
 * Egy órajel jellegû idõzítõt reprezentál, 
 * amely periodikusan léptet minden léptethetõ objektumot a játékban.
 * @author berenyi_kft
 *
 */
public class Timer {

	/**
	 * a játék léptethetõ objektumainak listája
	 */
	ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();
	
	//----------------------------------------------
	
	/**
	 * Hozzáadja s-t a steppables listához
	 * @param s
	 */
	public void addSteppable(ISteppable s) {
		this.steppables.add(s);
	}
	
	/**
	 * Eltávolítja s-t a steppables listából.
	 */
	public void removeSteppable(ISteppable s) {
		if(this.steppables.contains(s)) {
			this.steppables.remove(s);
		}
	}
	
	/**
	 * : Minden steppable listabeli objektumot léptet úgy, 
	 * hogy meghívja a step() függvényét.
	 */
	public void tick() {
		this.steppables.forEach((si) -> {si.step();});
	}
	
	
}
