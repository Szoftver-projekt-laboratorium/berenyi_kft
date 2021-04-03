package berenyi_kft;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Egy orajel jellegu idozitot reprezental, 
 * amely periodikusan leptet minden leptetheto objektumot a jatekban.
 * @author berenyi_kft
 */
public class Timer extends java.util.Timer {
	/**
	 * Az eltelt orajelek aktualis szama
	 */
	private int tick = 0;
	
	/**
	 * Az idozito kesleltetesi parameterei
	 */
	private long delay, period;
	
	/**
	 * A jatek leptetheto objektumainak listaja
	 */
	private ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();
	
	/**
	 * Idozitett taszk, amelyben a leptetesek megvalosulnak.
	 * Minden lepesben lepteti a jatek osszes leptetheto objektumat
	 * (steppables), es egyuttal szamlalja a jatekban eltelt idot.
	 */
	private TimerTask timertask = new TimerTask() {
		@Override
		public void run() {
			tick++;
			tick();
		}
	};
	
	//----------------------------------------------
	
	/**
	 * Konstruktor, amelyben beallithato az idozito kesleltetese.
	 * @param delay Az idozito kezdeti kesleltetese
	 * @param period Az idozito periodikus kesleltetese
	 */
	public Timer(long delay, long period) {
		this.delay = delay;
		this.period = period;
	}
	
	/**
	 * Hozzaadja a leptetheto objektumot a steppables listahoz
	 * @param s Az uj leptetheto dolog
	 */
	public void addSteppable(ISteppable s) {
		System.out.println("Timer's addSteppable(s: ISteppable) has been called");
		this.steppables.add(s);
	}
	
	/**
	 * Eltavolitja s-t a steppables listabol.
	 * @param s Az eltavolitando leptetheto dolog
	 */
	public void removeSteppable(ISteppable s) {
		System.out.println("Timer's removeSteppable(s: ISteppable has been called)");
		if(this.steppables.contains(s)) {
			this.steppables.remove(s);
		}
	}
	
	/**
	 * Minden steppable listabeli objektumot leptet ugy, 
	 * hogy meghivja a step() fuggvenyet.
	 */
	public void tick() {
		System.out.println("Timer's tick() has been called");
		this.steppables.forEach((si) -> {si.step();});
	}
	
	/**
	 * Visszaadja a játékban levő léptethető dolgokat.
	 * @return A játék léptethető objektumainak listája
	 */
	public ArrayList<ISteppable> getSteppables() {
		System.out.println("Timer's getSteppables() has been called");
		return steppables;
	}
}
