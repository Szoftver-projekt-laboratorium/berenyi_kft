package berenyi_kft;

import java.util.*;

public class Proto {
	
	private static Map<Object, String> ids = new HashMap<Object, String>();
	
	enum State{
		INIT,
		RUNNING,
		PAUSED,
		WON,
		LOST,
		EXIT
	}
	
	private static int tabs;
	
	private static State state=State.INIT;
	
	public static void println(String line) {
		for(int i=0;i<tabs;i++) {
			System.out.print('\t');
		}
		
		System.out.println(line);
	}
	
	public static void incrTabs() {
		tabs++;
	}
	
	public static void decrTabs() {
		tabs--;
	}
	
	public static void main(String[] args) {
		while(state!=State.EXIT) {
			//TODO 
		}
	}
}
