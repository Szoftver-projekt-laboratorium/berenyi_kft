package berenyi_kft;

import java.util.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Proto {
	
	private static Map<Object, String> ids = new HashMap<Object, String>();

	private static int tabs;
	
	
	private static boolean random = true;
	
	private static boolean log = true;
	
	private static Controller controller = null;
	
	
	public static String getId(Object o) {
        if(ids.containsKey(o)) {
            return ids.get(o);
        }
        return "null";
    }

	public static Object getObject(String id) {
		if (id.equals("null"))
			return null;
		
		for (Map.Entry<Object, String> e : ids.entrySet()) {
			if (e.getValue().equals(id))
				return e.getKey();
		}
		return null;
	}
	
	public static void println(String line) {
		for (int i = 0; i < tabs; i++) {
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
	
	public static void load(File fIn) {
		/* 1. beolvassa a fejlecet, ures sorig
		 *    kozben peldanyosit mindenkit es bepakolja oket az ids-be,
		 *    plusz egy ArrayList<Object>-be is, hogy sorban maradjanak (vagy TreeMap)
		 *    
		 * 2. A lista szerint minden objektumot beolvas. A Scannert atveszi.
		 *    (A fejlec ellenorzesre jo lesz, de nem fontos.)
		 *    Az attributumait sorban beolvassa (mindegyiket meg kell adni?).
		 *    Elfogyaszt egy ures sort is, majd visszater.
		 */
	}
	
	public static void save(File fOut) {
		
	}
	
	public static void main(String[] args) {
		// Az elejen itt dontjuk el, hogy a proto jatekot
		// vagy a tesztet futtatjuk.
		
		//while(controller.state != State.EXITED) {
			//TODO 
		//}
	}
	
}
