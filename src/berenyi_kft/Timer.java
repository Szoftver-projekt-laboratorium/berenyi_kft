package berenyi_kft;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;

/**
 * Egy orajel jellegu idozitot reprezental, amely periodikusan
 * leptet minden leptetheto objektumot a jatekban
 * @author berenyi_kft
 */
public class Timer extends java.util.Timer {
	/**
	 * Az eltelt orajelek aktualis szama
	 */
	private int ticks = 0;
	
	/**
	 * Azt jeloli, hogy az idozitot akarmikor elinditottek-e mar.
	 */
	private boolean started = false;
	
	/**
	 * Azt jeloli, hogy az idozito leptetheti-e a leptetheto objektumokat,
	 * illetve novelheti az eltelt idot jelolo ticks valtozot
	 */
	private boolean enabled = false;
	
	/**
	 * Az idozito kezdeti kesleltetesi ideje,
	 * illetve periodusideje ezredmasodpercben
	 */
	private long delay = 3000;
	private long period = 1000;
	
	/**
	 * A jatek leptetheto objektumainak listaja
	 */
	private ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();
	
	//--------------------------------------------------------------------
	
	/**
	 * Referencia arra az idozitett taszkra, amelyben a leptetesek megvalosulnak.
	 * Ha a jatek futo allapotban van, akkor minden lepesben lepteti a jatek
	 * osszes leptetheto objektumat (steppables), egyuttal szamlalja a tick-eket.
	 */
	private TimerTask timertask = new TimerTask() {
		@Override
		public void run() {
			if(enabled) {
				System.out.println(ticks);
				ticks++;
				tick();
			}
		}
	};
	
	//----------------------------------------------
	
	public String getDescription() { 
		
		String str="";
		
		String id=Proto.getId(this);
		str+="Timer "+id+"\n";
		
		String ticksStr=Integer.toString(ticks);
		str+="\tticks "+ticksStr+"\n";
		
		String delayStr=Long.toString(delay);
		str+="\tdelay "+delayStr+"\n";
		
		String periodStr=Long.toString(period);
		str+="\tperiod "+periodStr+"\n";
		
		if(!steppables.isEmpty()) {   
			str+="\tsteppables";
			for(ISteppable is : steppables) {
				String steppableId=Proto.getId(is);
				str+=" "+steppableId;
			}
			str+="\n";
		}
		else
			str+="\tsteppables null\n";
		
		return str;	
	}
	
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
	 * Elinditja/tovabbinditja az idozitot.
	 * A ticks valtozo az aktualis erteketol szamlal tovabb.
	 */
	public void start() {
		if (!started) {
			this.schedule(timertask, delay, period);
			started = true;
		}
		enabled = true;
	}
	
	/**
	 * Szunetelteti az idozito futasat.
	 * Nem modosit a ticks valtozo erteken.
	 */
	public void stop() {
		enabled = false;
	}
	
	/**
	 * Visszater a jatekban eltelt masodpercek szamaval.
	 * @return Az idozito altal szamlalt tick-ek szama
	 */
	public int getTicks() {
		return ticks;
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
		
		int i=0;
		while(i<=steppables.size()-1) {
			ISteppable is=steppables.get(i);
			if(is!=null)
				is.step();
			i=steppables.indexOf(is)+1;
		}
	}
	
	/**
	 * Visszaadja a játékban levő léptethető dolgokat.
	 * @return A játék léptethető objektumainak listája
	 */
	public ArrayList<ISteppable> getSteppables() {
		System.out.println("Timer's getSteppables() has been called");
		return steppables;
	}
	
	/**
	 * Beolvassa a jatek attributumait az sc Scanner aktualis poziciojatol.
	 * @param sc A beolvasast vegzo Scanner
	 */
	public void load(Scanner sc) {
		String line = sc.nextLine();
		while (!line.equals("") & sc.hasNextLine()) {
			line = sc.nextLine();
			line = line.stripLeading();
			String[] tokens = line.split("\\s+");
			
			switch (tokens[0]) {
				case "ticks":
					ticks = Integer.parseInt(tokens[1]);
					break;
					
				case "delay":
					delay= Long.parseLong(tokens[1]);
					break;
					
				case "period":
					period = Long.parseLong(tokens[1]);
					break;
					
				case "steppables":
					for (int i = 1; i < tokens.length; i++) {
						ISteppable p = (ISteppable)Proto.getObject(tokens[i]);
						if (p != null)
							steppables.add(p);
					}
					break;
					
				default:
					break;
			}
		}
	}
}
