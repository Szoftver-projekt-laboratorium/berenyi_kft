package berenyi_kft;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Egy �rajel jelleg� id�z�t�t reprezent�l, 
 * amely periodikusan l�ptet minden l�ptethet� objektumot a j�t�kban.
 * @author berenyi_kft
 *
 */
public class Timer extends java.util.Timer{
	/**
	 * 
	 */
	int tick = 0;
	
	/**
	 * 
	 */
	long delay, period;
	
	/**
	 * a j�t�k l�ptethet� objektumainak list�ja
	 */
	ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();
	
	/**
	 * 
	 */
	TimerTask timertask = new TimerTask() {
		@Override
		public void run() {
			tick++;
			tick();
		}
	};
	
	//----------------------------------------------
	
	public Timer(long delay, long period) {
		this.delay = delay;
		this.period = period;
	}
	
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
