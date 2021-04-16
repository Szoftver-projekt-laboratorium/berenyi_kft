package berenyi_kft;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import berenyi_kft_test.Tester;

// Az IO metodusokat jobb lenne kiszervezni vagy beszervezni egy masik osztalyba.
// Lehetne akar Proto.IO is. Nem tudom, jobb-e ugy, csak kompaktabb lenne.

/**
 * Proto osztaly a prototipus program vezerlesehez, tesztelesehez
 * @author berenyi_kft
 */
public class Proto {
	
	// Publikus, hogy a Tester is lekerdezhesse
	public static class Objects {
		private Map<Object, String> ids = new HashMap<Object, String>();
		
		private Controller controller = null;
		private List<Player> players = new ArrayList<Player>();
		private Game game = null;
		private List<Recipe> recipes = new ArrayList<Recipe>();
		private Timer timer = null;
		private Sun sun = null;
		private List<Asteroid> asteroids = new ArrayList<Asteroid>();
		private List<Coal> coals = new ArrayList<Coal>();
		private List<Iron> irons = new ArrayList<Iron>();
		private List<Ice> ices = new ArrayList<Ice>();
		private List<Uranium> uraniums = new ArrayList<Uranium>();
		private List<Settler> settlers = new ArrayList<Settler>();
		private List<AIRobot> robots = new ArrayList<AIRobot>();
		private List<UFO> ufos = new ArrayList<UFO>();
		private List<TeleportingGate> gates = new ArrayList<TeleportingGate>();
		
		public Controller getController() {
			return controller;
		}
	}
	
	public static Proto.Objects allObjects = new Proto.Objects();

	private static int tabs;
	
	private static boolean random = true;
	
	private static boolean log = true;
	
	
	public static Proto.Objects getAllObjects() {
		return allObjects;
	}
	
	public static boolean isRandom() {
		return random;
	}
	
	/**
	 * Beallitja, hogy a prototipus program objektumai megvalosithatnak-e
	 * veletlenszeru mukodest (isRandom == true), vagy kotelezoen
	 * determinisztikusan kell, hogy fussanak (isRandom == false).
	 * @param isRandom A randomizalt mukodest engedelyezo/letilto logikai valtozo
	 */
	public static void setRandom(boolean isRandom) {
		random = isRandom;
	}
	
	public static boolean isLogging() {
		return log;
	}
	
	/**
	 * Beallitja, hogy a prototipus program naplozza-e a legfontosabb meghivott 
	 * metodusokat a konzolon (isLogging == true), vagy ne (isLogging = false).
	 * @param isLogging A konzolos metodusnaplozast engedelyezo/letilto logikai valtozo
	 */
	public static void enableLogging(boolean isLogging) {
		log = isLogging;
	}
	
	/**
	 * Megadja az o jatekbeli objektumot azonosito stringet.
	 * @param o A kerdezett objektum referenciaja
	 * @return Az objektum azonosito neve; o==null eseten a "null" szoveg
	 */
	public static String getId(Object o) {
        if (allObjects.ids.containsKey(o)) {
            return allObjects.ids.get(o);
        }
        return "null";
    }
	
	/**
	 * Visszaadja az id azonositoju jatekbeli objekumot.
	 * @param id A kerdezett objektum azonositoja
	 * @return Referencia az objektumra; ha nem letezik ilyen nevu
	 * 		   objektum, akkor null a visszateresi ertek
	 */
	public static Object getObject(String id) {
		for (Map.Entry<Object, String> e : allObjects.ids.entrySet()) {
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
	
	// Ez a nextLine()-ok alternativaja lehet. Annyival tud tobbet,
	// hogy magunknak kezelhetjuk a kiveteleket, ha nem sikerul a beolvasas.
	/*public static String getLine(Scanner sc) throws Exception {
		if (sc.hasNextLine()) {
			return sc.nextLine();
		}
		else {
			throw new Exception("Proto.getLine() - Unsuccessful line parsing.");
		}
	}*/
	
	/**
	 * Segedfuggveny a beolvasott objektumok peldanyositasahoz.
	 * @param typename A letrehozando objektum tipusa szoveges azonositoval
	 * @return A letrehozott uj objektum
	 */
	private static void createObject(String typename, String id) {
		if (id == null)
			return;
		
		switch (typename) {
			case "Controller":
				Controller controller = new Controller();
				allObjects.ids.put(controller, id);
				allObjects.controller = controller;
				break;
			
			case "Player":
				Player p = new Player();
				allObjects.ids.put(p, id);
				allObjects.players.add(p);
				break;
			
			case "Game":
				Game game = new Game();
				allObjects.ids.put(game, id);
				allObjects.game = game;
				break;
			
			case "Recipe":
				Recipe recipe = new Recipe();
				allObjects.ids.put(recipe, id);
				allObjects.recipes.add(recipe);
				break;
			
			case "Timer":
				Timer timer = new Timer(0, 0);
				allObjects.ids.put(timer, id);
				allObjects.timer = timer;
				break;
				
			case "Sun":
				Sun sun = new Sun();
				allObjects.ids.put(sun, id);
				allObjects.sun = sun;
				break;
			
			case "Asteroid":
				Asteroid a = new Asteroid();
				allObjects.ids.put(a, id);
				allObjects.asteroids.add(a);
				break;
			
			case "Coal":
				Coal co = new Coal();
				allObjects.ids.put(co, id);
				allObjects.coals.add(co);
				break;
			
			case "Iron":
				Iron ir = new Iron();
				allObjects.ids.put(ir, id);
				allObjects.irons.add(ir);
				break;
			
			case "Ice":
				Ice ic = new Ice();
				allObjects.ids.put(ic, id);
				allObjects.ices.add(ic);
				break;
			
			case "Uranium":
				Uranium ur = new Uranium();
				allObjects.ids.put(ur, id);
				allObjects.uraniums.add(ur);
				break;
			
			case "Settler":
				Settler s = new Settler();
				allObjects.ids.put(s, id);
				allObjects.settlers.add(s);
				break;
			
			case "AIRobot":
				AIRobot air = new AIRobot(null);
				allObjects.ids.put(air, id);
				allObjects.robots.add(air);
				break;
			
			case "UFO":
				UFO ufo = new UFO(null);
				allObjects.ids.put(ufo, id);
				allObjects.ufos.add(ufo);
				break;
			
			case "TeleportingGate":
				TeleportingGate tg = new TeleportingGate();
				allObjects.ids.put(tg, id);
				allObjects.gates.add(tg);
				break;
			
			default:
				throw new IllegalArgumentException(
					"Invalid class name: " + typename + ".");
		}
	}
	
	/**
	 * Segedfuggveny az objektumok attributumai beallitasahoz.
	 * @param o Az objektum, amelynek attributumait be kell tolteni
	 * @param typename Az objektum tipusa szoveges azonositoval
	 * @param sc Az objektum attributumai beolvasasat vegzo Scanner
	 */
	private static void loadObjectAttributes(Scanner sc) {
		if (allObjects.controller != null) {
			allObjects.controller.load(sc);
		}
		
		for (Player p : allObjects.players) {
			p.load(sc);
		}
		
		if (allObjects.game != null) {
			allObjects.game.load(sc);
		}
		
		for (Recipe recipe : allObjects.recipes) {
			recipe.load(sc);
		}
		
		if (allObjects.timer != null) {
			allObjects.timer.load(sc);
		}
		
		if (allObjects.sun != null) {
			allObjects.sun.load(sc);
		}
		
		for (Asteroid a : allObjects.asteroids) {
			a.load(sc);
		}
		
		for (Uranium ur : allObjects.uraniums) {
			ur.load(sc);
		}

		for (Settler s : allObjects.settlers) {
			s.load(sc);
		}
		
		for (AIRobot air : allObjects.robots) {
			air.load(sc);
		}
		
		for (UFO ufo : allObjects.ufos) {
			ufo.load(sc);
		}
		
		for (TeleportingGate tg : allObjects.gates) {
			tg.load(sc);
		}
	}
	
	/**
	 * Betolti a megadott nevu konfiguracios fajl tartalmat.
	 * @param filename A beolvasando jatekkonfiguracios fajl neve
	 */
	public static void load(String filename) throws IOException {
		allObjects = new Proto.Objects();
		
		Scanner sc = new Scanner(new File(filename));
		String line = sc.nextLine();
		while (!line.equals("")) {
			String[] tokens = line.split("\\s+");
			String typename = tokens[0];
			
			for (int i = 1; i < tokens.length; i++) {
				createObject(typename, tokens[i]);
			}
			line = sc.nextLine();
		}
		
		loadObjectAttributes(sc);
		sc.close();
	}
	
	/**
	 * Segedfuggveny az objektumok deklaracioinak kiirasahoz
	 * (a konfiguracios fajl elso reszehez).
	 * @param ps A kimenet, ahova az iras tortenik
	 */
	private static void saveObjectIds(PrintStream ps) {
		if (allObjects.controller != null) {
			ps.println("Controller " + getId(allObjects.controller));
		}
		
		if (!allObjects.players.isEmpty()) {
			ps.print("Player");
			for (Player p : allObjects.players) {
				ps.print(" " + getId(p));
			}
			ps.println();
		}
		
		if (allObjects.game != null) {
			ps.println("Game " + getId(allObjects.game));
		}
		
		if (!allObjects.recipes.isEmpty()) {
			ps.print("Recipe");
			for (Recipe recipe : allObjects.recipes) {
				ps.print(" " + getId(recipe));
			}
			ps.println();
		}
		
		if (allObjects.timer != null) {
			ps.println("Timer " + getId(allObjects.timer));
		}
		
		if (allObjects.sun != null) {
			ps.println("Sun " + getId(allObjects.sun));
		}
		
		if (!allObjects.asteroids.isEmpty()) {
			ps.print("Asteroid");
			for (Asteroid a : allObjects.asteroids) {
				ps.print(" " + getId(a));
			}
			ps.println();
		}
		
		if (!allObjects.uraniums.isEmpty()) {
			ps.print("Uranium");
			for (Uranium ur : allObjects.uraniums) {
				ps.print(" " + getId(ur));
			}
			ps.println();
		}

		if (!allObjects.settlers.isEmpty()) {
			ps.print("Settler");
			for (Settler s : allObjects.settlers) {
				ps.print(" " + getId(s));
			}
			ps.println();
		}
		
		if (!allObjects.robots.isEmpty()) {
			ps.print("AIRobot");
			for (AIRobot air : allObjects.robots) {
				ps.print(" " + getId(air));
			}
			ps.println();
		}
		
		if (!allObjects.ufos.isEmpty()) {
			ps.print("UFO");
			for (UFO ufo : allObjects.ufos) {
				ps.print(" " + getId(ufo));
			}
			ps.println();
		}
		
		if (!allObjects.gates.isEmpty()) {
			ps.print("TeleportingGate");
			for (TeleportingGate tg : allObjects.gates) {
				ps.print(" " + getId(tg));
			}
			ps.println();
		}
	}
	
	/**
	 * Segedfuggveny az objektumok definicioinak kiirasahoz
	 * (a konfiguracios fajl masodik resze, az attributumok ertekeivel).
	 * @param ps A kimenet, ahova az iras tortenik
	 */
	private static void saveObjectAttributes(PrintStream ps) {
		if (allObjects.controller != null) {
			ps.println(allObjects.controller.getDescription());
		}
		
		for (Player p : allObjects.players) {
			ps.println(p.getDescription());
		}
		
		if (allObjects.game != null) {
			ps.println(allObjects.game.getDescription());
		}
		
		for (Recipe recipe : allObjects.recipes) {
			ps.println(recipe.getDescription());
		}
		
		if (allObjects.timer != null) {
			ps.println(allObjects.timer.getDescription());
		}
		
		if (allObjects.sun != null) {
			ps.println(allObjects.sun.getDescription());
		}
		
		for (Asteroid a : allObjects.asteroids) {
			ps.println(a.getDescription());
		}
		
		for (Uranium ur : allObjects.uraniums) {
			ps.println(ur.getDescription());
		}

		for (Settler s : allObjects.settlers) {
			ps.println(s.getDescription());
		}
		
		for (AIRobot air : allObjects.robots) {
			ps.println(air.getDescription());
		}
		
		for (UFO ufo : allObjects.ufos) {
			ps.println(ufo.getDescription());
		}
		
		for (TeleportingGate tg : allObjects.gates) {
			ps.println(tg.getDescription());
		}
	}
	
	/**
	 * Kozos segedfuggveny a save-hez es a showAll-hoz:
	 * a ps PrintStream-re nyomtatja az aktualis jatekkonfiguraciot.
	 * @param ps A PrintStream, ahova a mentes tortenik
	 */
	private static void saveToStream(PrintStream ps) {
		saveObjectIds(ps);
		ps.println();
		saveObjectAttributes(ps);
	}
	
	/**
	 * A filename nevu szoveges fajlba irja az aktualis jatekkonfiguraciot
	 * a konfiguracios fajl formatuma szerint.
	 * @param filename A kimeneti fajl neve, ahova a jatekallapot mentodik
	 * @throws FileNotFoundException Akkor dobodik, ha a filename fajl
	 * 		   nem hozhato letre vagy nem nyithato meg.
	 */
	public static void save(String filename) throws FileNotFoundException {
		PrintStream ps = new PrintStream(filename);
		saveToStream(ps);
		ps.close();
	}
	
	// Ezzel a fuggvennyel kezdjunk valamit?
	/* public static void showOne(String id) {
	 *	System.out.println(allObjects.getObject(id).getDescription());
	}*/
	
	/**
	 * A standard kimenetre irja az aktualis jatekkonfiguraciot
	 * a konfiguracios fajl formatuma szerint.
	 */
	public static void showAll() {
		saveToStream(System.out);
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
			System.out.print("Please select whether you wish to test (Y) "
					+ " or play (n) the prototype game. [Y/n]: ");
			String choice = sc.nextLine();
			if (choice.substring(0, 1).toLowerCase().equals("y")) {
				Tester.testerMain(args);
			}
			else {
				System.out.println("Now you can play the game.");
				// TODO Rövid help/leiras, vagy azonnal init es induljon?
				
				boolean exit = false;
				// TODO: Minden nextLine() ele kell hasNextLine() a kodban?
				// Esetleg egy getLine(), ami osszerakna a kettot?
				while (!exit & sc.hasNextLine()) {
					String line = sc.nextLine();
					
					String[] tokens = line.split("\\s+");
					String cmd = tokens[0];
					PlayerCommand playerCmd = PlayerCommand.fromString(cmd);
					if (playerCmd != PlayerCommand.INVALID) {
						Player pAct = allObjects.controller.getActPlayer();
						pAct.actOnSettler(playerCmd, tokens);
					}
					else {
						Controller controller = allObjects.controller; // segedvaltozo
						switch (cmd) {
							case "exit":
								exit = true;
								System.out.println("The prototype program has terminated.");
								//System.exit(0);
								break;
						
							case "random":
								if (tokens.length >= 2) {
									if (tokens[1].equals("true"))
										random = true;
									else if (tokens[1].equals("false"))
										random = false;
									else
										throw new IllegalArgumentException(
											"Invalid argument for <is_random>: "
													+ tokens[1] + ".");
								} else
									throw new IllegalArgumentException(
										"Argument for command 'random' is missing.");
								break;
								
							case "log":
								if (tokens.length >= 2) {
									if (tokens[1].equals("true"))
										log = true;
									else if (tokens[1].equals("false"))
										log = false;
									else
										throw new IllegalArgumentException(
											"Invalid argument for <is_logging>: "
													+ tokens[1] + ".");
								} else
									throw new IllegalArgumentException(
										"Argument for command 'log' is missing.");
								break;
							
							case "init":
								allObjects.controller.startGame();
								break;
							
							// A 0. az csak teszt tesztfajl, 1-tol 38-ig mennek majd
							// az igazi tesztek.
							// Pelda: load src/test_data/test_inputs/test_0.in
							case "load":
								if (tokens.length >= 2) {
									load(tokens[1]);
								}
								break;
							
							//TODO: hibauzenetek, ahol kellenek
							case "start":
								if (controller != null)
									controller.setState(State.RUNNING);
								break;
							
							case "stop":
								if (controller != null)
									controller.setState(State.PAUSED);
								break;
							
							case "save":
								if (tokens.length >= 2) {
									save(tokens[1]);
								}
								break;	
							
							case "show":
								if (tokens.length == 1) {
									showAll();
								} /*else if (tokens.length >= 2) {
									showOne(tokens[1]);
								}*/
								break;
	
							default:
								System.out.println("Invalid operation, please type in "
										+ "a valid command.");
								break;
						}
					}
				}
				sc.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("The prototype program has been ended by an exception.");
			// System.exit(1);
		}
	}
	
}
