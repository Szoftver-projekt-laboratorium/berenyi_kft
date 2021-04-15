package berenyi_kft;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import berenyi_kft_test.Tester;

/**
 * Proto osztaly a prototipus program vezerlesehez, tesztelesehez
 * @author berenyi_kft
 */
public class Proto {
	
	private static Map<Object, String> ids = new HashMap<Object, String>();

	private static int tabs;
	
	private static boolean random = true;
	
	private static boolean log = true;
	
	private static Controller controller = null;
	
	/**
	 * Beallitja, hogy a prototipus program objektumai megvalosithatnak-e
	 * veletlenszeru mukodest (isRandom == true), vagy kotelezoen
	 * determinisztikusan kell, hogy fussanak (isRandom == false).
	 * @param isRandom A randomizalt mukodest engedelyezo/letilto logikai valtozo
	 */
	public static void setRandom(boolean isRandom) {
		random = isRandom;
	}
	
	/**
	 * Beallitja, hogy a prototipus program naplozza-e a legfontosabb meghivott 
	 * metodusokat a konzolon (isLogging == true), vagy ne (isLogging = false).
	 * @param isLogging A konzolos metodusnaplozast engedelyezo/letilto logikai valtozo
	 */
	public static void enableLogging(boolean isLogging) {
		log = isLogging;
	}
	
	public static String getId(Object o) {
        if (ids.containsKey(o)) {
            return ids.get(o);
        }
        return "null";
    }

	public static Object getObject(String id) {
		// TODO: Megengedjunk null id-ju objektumot? Inkabb ne.
		// De amugy sem olvasnank be oket.
		// if (id.equals("null"))
		//	  return null;
		
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
	
	/**
	 * Segedfuggveny a beolvasott objektumok peldanyositasahoz.
	 * @param typename A letrehozando objektum tipusa szoveges azonositoval
	 * @return A letrehozott uj objektum
	 */
	private static Object createObject(String typename) {
		switch (typename) {
			case "Controller": return new Controller();
			case "Player": return new Player();
			case "Game": return new Game();
			case "Recipe": return new Recipe();
			case "Timer": return new Timer(0, 0);
			case "Sun": return new Sun();
			case "Asteroid": return new Asteroid();
			case "Coal": return new Coal();
			case "Iron": return new Iron();
			case "Ice": return new Ice();
			case "Uranium": return new Uranium();
			case "Settler": return new Settler();
			case "AIRobot": return new AIRobot(null);
			case "UFO": return new UFO();
			case "TeleportingGate": return new TeleportingGate();
			
			// TODO: Ha invalid a tipus, akkor itt dobjunk kivetelt?
			// Egyelore null-lal ter vissza.
			default:
			//	throw new NullPointerException("Invalid class name: " + typename);
				return null;
		}
	}
	
	/**
	 * Segedfuggveny az objektumok attributumai beallitasahoz.
	 * @param o Az objektum, amelynek attributumait be kell tolteni
	 * @param typename Az objektum tipusa szoveges azonositoval
	 * @param sc Az objektum attributumai beolvasasat vegzo Scanner
	 */
	private static void loadObjectAttributes(Object o, String typename, Scanner sc) {
		switch (typename) {
			case "Controller": Controller controller = (Controller)o; controller.load(sc); break;
			case "Player": Player p = (Player)o; /*p.load(sc);*/ break;
			case "Game": Game game = (Game)o; /*game.load(sc);*/ break;
			case "Recipe": Recipe r = (Recipe)o; /*r.load(sc);*/ break;
			case "Timer": Timer timer = (Timer)o; /*timer.load(sc);*/ break;
			case "Sun": Sun sun = (Sun)o; sun.load(sc); break;
			case "Asteroid": Asteroid a = (Asteroid)o; a.load(sc); break;
			case "Coal": Coal co = (Coal)o; /*co.load(sc);*/ break;
			case "Iron": Iron ir = (Iron)o; /*ir.load(sc);*/ break;
			case "Ice": Ice ic = (Ice)o; /*ic.load(sc);*/ break;
			case "Uranium": Uranium ur = (Uranium)o; /*ur.load(sc);*/ break;
			case "Settler": Settler s = (Settler)o; /*s.load(sc);*/ break;
			case "AIRobot": AIRobot air = (AIRobot)o; /*air.load(sc);*/ break;
			case "UFO": UFO ufo = (UFO)o; /*ufo.load(sc);*/ break;
			case "TeleportingGate": TeleportingGate tg = (TeleportingGate)o; /*tg.load(sc);*/ break;
			default: break;
		}
	}
	
	/**
	 * Betolti a megadott nevu konfiguracios fajl tartalmat,
	 * es az ids Map-ben nyilvantartja a beolvasott objektumokat.
	 * @param filename A beolvasando jatekkonfiguracios fajl neve
	 */
	public static void load(String filename) throws IOException {
		/* 0. Kitakaritja a meglevo ids Map-et.
		 * 
		 * 1. Beolvassa a fejlecet, ures sorig
		 *    kozben peldanyosit mindenkit es bepakolja oket az ids-be,
		 *    plusz egy ArrayList<Object>-be is, hogy sorban maradjanak (vagy TreeMap).
		 *    
		 * 2. A lista szerint minden objektumot beolvas. A Scannert atveszi.
		 *    (A fejlec ellenorzesre jo lesz, de nem fontos.)
		 *    Az attributumait sorban beolvassa (mindegyiket meg kell adni?).
		 *    Elfogyaszt egy ures sort is, majd visszater.
		 */
		ids.clear();
		Map<Object, String> objectTypes = new HashMap<Object, String>();
		
		Scanner sc = new Scanner(filename);
		String line = sc.nextLine();
		while (!line.equals("")) {
			String[] tokens = line.split("\\s");
			String typename = tokens[0];
			
			for (int i = 1; i < tokens.length; i++) {
				Object o = createObject(typename);
				// TODO: Ha invalid a tipus, akkor itt dobjunk kivetelt?
				// if (o == null)
				//    throw new NullPointerException("Invalid class name: " + typename);
				// (Lehet IllegalArgumentException is akar.)
				if (o != null) {
					ids.put(o, tokens[i]);
					objectTypes.put(o, typename);
				}
			}
			line = sc.nextLine();
		}
		
		for (Map.Entry<Object, String> e : objectTypes.entrySet()) {
			Object o = e.getKey();
			String typename = e.getValue();
			loadObjectAttributes(o, typename, sc);
		}
		sc.close();
	}
	
	// TODO: Hogyan rendezzuk sorba az osztalyokat?
	public static void save(String filename) throws FileNotFoundException {
		PrintStream ps = new PrintStream(filename);
		
		
		ps.close();
	}
	
	
	
	/**
	 * A prototipus program belepesi pontja.
	 * A tesztelesi uzemmod es a jatek uzemmod kozotti valasztast teszi
	 * lehetove a konzolon. Teszteles valasztasa eseten atiranyitja a Tester.main()-be
	 * a vezerlest. Jatek uzemmodban pedig ez a metodus vegzi a parancsok feldolgozasat.
	 * @param args A program parancssori argumentumai
	 */
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Welcome in berenyi_kft's Proto program!");
			System.out.println("Please select whether you wish to test (Y) or play (n) the prototype game. [Y/n]: ");
			String choice = sc.next();
			if (choice.substring(0, 1).toLowerCase().equals("y")) {
				Tester.testerMain(args);
			}
			else {
				System.out.println("Now you can play the game.");
				// TODO
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
