package berenyi_kft;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import berenyi_kft_GUI.AIRobotGraphics;
import berenyi_kft_GUI.AsteroidGraphics;
import berenyi_kft_GUI.GamePanel;
import berenyi_kft_GUI.SettlerGraphics;
import berenyi_kft_GUI.SunGraphics;
import berenyi_kft_GUI.TeleportingGateGraphics;
import berenyi_kft_GUI.UFOGraphics;

/**
 * Proto osztaly a prototipus program vezerlesehez, tesztelesehez
 * 
 * @author berenyi_kft
 */
public class Proto {

	// Publikus, hogy a Tester is lekerdezhesse.
	public static class Objects {
		private Map<Object, String> ids = new HashMap<Object, String>();

		private Controller controller = null;
		private List<Player> players = new ArrayList<Player>();
		private Game game = null;
		private List<Recipe> recipes = new ArrayList<Recipe>(3);
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

		public Objects() {
			for (int i = 0; i < 3; i++)
				recipes.add(null);
		}

		public void clearRecipes() {
			recipes.clear();
		}

		public Controller getController() {
			return controller;
		}

		public Timer getTimer() {
			return timer;
		}

		// TODO: Lehet meg szep generikus metodusokkal egyszerusiteni a kodot?
		/**
		 * Generikus segedfuggveny uj azonosithato objektum bejegyzesehez. A <T> tipusu
		 * object jatekbeli objektum hozzaadodik a megfelelo objects lista vegere,
		 * illetve bekerul az ids Map-be is. A metodus azonositot general az ujonnan
		 * hozzaadott objektumnak, ehhez a <T> tipusra jellemzo typePrefix elotagot
		 * hasznalja.
		 * 
		 * @param <T>        Generikus parameter, a hozzaadando objektum peldanyositott
		 *                   tipusa
		 * @param object     Az azonosithato jatekobjektum, amelyet a prototipus eltarol
		 * @param objects    Az ugyanilyen tipusu nyilvantartott objektumok listaja
		 * @param typePrefix A <T> tipusu objektumok kozos elotagja
		 */
		private <T> void addObject(T object, List<T> objects, String typePrefix) {
			try {

				Integer seqNumber = 0;
				if (objects.size() > 0) {
					Object lastObject = objects.get(objects.size() - 1);
					String lastObjectName = ids.get(lastObject);
					String seqString = lastObjectName.substring(typePrefix.length());
					seqNumber = Integer.parseInt(seqString);
				}

				String newObjectName = typePrefix + Integer.toString(seqNumber + 1);

				// Elvileg a szamozas nem dob kivetelt, ha kikotjuk, hogy novekvoen
				// szerepeljenek az objektumok az inputban (pl. "ur1 ur2 ur4 ...").
				if (objects.contains(object)) {
					throw new IllegalArgumentException(
							"Proto.Objects.addObject(): " + "Unavailable ID for the new object.");
				}
				objects.add(object);
				ids.put(object, newObjectName);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}

		public void setController(Controller controller1) {
			controller = controller1;
			ids.put(controller, "controller");
		}

		public void addPlayer(Player p) {
			addObject(p, players, "p");
		}

		public void setGame(Game game1) {
			game = game1;
			ids.put(game, "game");
		}

		public void addRecipe(Recipe recipe, String id) {
			addObject(recipe, recipes, id);
		}

		public void setRobotRecipe(Recipe robotRecipe) {
			recipes.set(0, robotRecipe);
			ids.put(robotRecipe, "robotRecipe");
		}

		public void setGatePairRecipe(Recipe gatePairRecipe) {
			recipes.set(1, gatePairRecipe);
			ids.put(gatePairRecipe, "gatePairRecipe");
		}

		public void setSpaceBaseRecipe(Recipe spaceBaseRecipe) {
			recipes.set(2, spaceBaseRecipe);
			ids.put(spaceBaseRecipe, "spaceBaseRecipe");
		}

		public void setTimer(Timer timer1) {
			timer = timer1;
			ids.put(timer, "timer");
		}

		public void setSun(Sun sun1) {
			sun = sun1;
			ids.put(sun, "sun");
		}

		public void addAsteroid(Asteroid a) {
			addObject(a, asteroids, "a");
		}

		public void addCoal(Coal co) {
			addObject(co, coals, "co");
		}

		public void addIron(Iron ir) {
			addObject(ir, irons, "ir");
		}

		public void addIce(Ice ic) {
			addObject(ic, ices, "ic");
		}

		public void addUranium(Uranium ur) {
			addObject(ur, uraniums, "ur");
		}

		public void addSettler(Settler s) {
			addObject(s, settlers, "s");
		}

		public void addAIRobot(AIRobot air) {
			addObject(air, robots, "air");
		}

		public void addUFO(UFO ufo) {
			addObject(ufo, ufos, "ufo");
		}

		public void addTeleportingGate(TeleportingGate tg) {
			addObject(tg, gates, "tg");
		}

		/**
		 * Eltavolitja a <T> tipusu object objektumot a program nyilvantartasabol. A
		 * sajat tipusanak megfelelo objektumok listajabol (objects) is torli.
		 * 
		 * @param <T>     Generikus parameter, az eltavolitando objektum peldanyositott
		 *                tipusa
		 * @param object  A prototipus programbol torlendo azonositott objektum
		 * @param objects A <T> tipusu nyilvantartott objektumok listaja
		 */
		private <T> void removeObject(T object, List<T> objects) {
			ids.remove(object);
			objects.remove(object);
		}

		public void removePlayer(Player p) {
			removeObject(p, players);
		}

		public void removeAsteroid(Asteroid a) {
			removeObject(a, asteroids);
		}

		public void removeCoal(Coal co) {
			removeObject(co, coals);
		}

		public void removeIron(Iron ir) {
			removeObject(ir, irons);
		}

		public void removeIce(Ice ic) {
			removeObject(ic, ices);
		}

		public void removeUranium(Uranium ur) {
			removeObject(ur, uraniums);
		}

		public void removeSettler(Settler s) {
			removeObject(s, settlers);
		}

		public void removeAIRobot(AIRobot air) {
			removeObject(air, robots);
		}

		public void removeUFO(UFO ufo) {
			removeObject(ufo, ufos);
		}

		public void removeTeleportingGate(TeleportingGate tg) {
			removeObject(tg, gates);
		}
	}

	public static Proto.Objects allObjects = new Proto.Objects();

	private static int tabs = 0;

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
	 * veletlenszeru mukodest (isRandom == true), vagy kotelezoen determinisztikusan
	 * kell, hogy fussanak (isRandom == false).
	 * 
	 * @param isRandom A randomizalt mukodest engedelyezo/letilto logikai valtozo
	 */
	public static void setRandom(boolean isRandom) {
		Proto.println(isRandom ? "random enabled" : "random disabled");
		random = isRandom;
	}

	public static boolean isLogging() {
		return log;
	}

	/**
	 * Beallitja, hogy a prototipus program naplozza-e a legfontosabb meghivott
	 * metodusokat a konzolon (isLogging == true), vagy ne (isLogging = false).
	 * 
	 * @param isLogging A konzolos metodusnaplozast engedelyezo/letilto logikai
	 *                  valtozo
	 */
	public static void enableLogging(boolean isLogging) {
		System.out.println(isLogging ? "- logging enabled" : "- logging disabled");
		log = isLogging;
	}

	/**
	 * Megadja az o jatekbeli objektumot azonosito stringet.
	 * 
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
	 * 
	 * @param id A kerdezett objektum azonositoja
	 * @return Referencia az objektumra; ha nem letezik ilyen nevu objektum, akkor
	 *         null a visszateresi ertek
	 */
	public static Object getObject(String id) {
		for (Map.Entry<Object, String> e : allObjects.ids.entrySet()) {
			if (e.getValue().equals(id))
				return e.getKey();
		}
		return null;
	}
	
	/**
	 * Jatekpanel referencia a logolt fuggvenyek kiirasahoz
	 */
	private static GamePanel gamePanel = null;
	
	/**
	 * Beallitja a jatekpanelt.
	 * @param gamePanel - a jatek nezeti panelje
	 */
	public static void setGamePanel(GamePanel gamePanel) {
		Proto.gamePanel = gamePanel;
	}
	
	/**
	 * Naplozza a <code>str</code> szoveget a kimeneten az aktualis
	 * <code>Proto.tabs</code> ertekkel tabulalva.
	 * A kimenet a grafikus kepernyo, a gamePanel messages szovegmezoje.
	 * 
	 * @param line A naplozando sor
	 */
	public static void print(String str) {
		if (Proto.isLogging()) {
			for (int i = 0; i < tabs; i++) {
				System.out.print("   |");
			}
			System.out.print("- ");
			System.out.print(str);
			// gamePanel.writeToMessageBoard(str);
		}
	}
	
	/**
	 * Naplozza a <code>line</code> sort a kimeneten az aktualis
	 * <code>Proto.tabs</code> ertekkel tabulalva, majd uj sort kezd.
	 * A kimenet a grafikus kepernyo, a gamePanel messages szovegmezoje.
	 * 
	 * @param line A naplozando sor
	 */
	public static void println(String line) {
		if (Proto.isLogging()) {
			for (int i = 0; i < tabs; i++) {
				System.out.print("   |");
			}
			System.out.print("- ");
			System.out.println(line);
			// gamePanel.writeToMessageBoard(line);
		}
	}

	/**
	 * Eggyel noveli a Proto.tabs statikus valtozo erteket, vagyis a logolas
	 * aktualis behuzasanak merteket.
	 */
	public static void incrTabs() {
		tabs++;
	}

	/**
	 * Eggyel csokkenti a Proto.tabs statikus valtozo erteket, vagyis a logolas
	 * aktualis behuzasanak merteket, felteve, hogy pozitiv volt.
	 */
	public static void decrTabs() {
		if (tabs > 0)
			tabs--;
	}

	/**
	 * Segedfuggveny a beolvasott objektumok peldanyositasahoz.
	 * 
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
			Timer timer = new Timer(5000, 5000); // lassabb lett
			allObjects.ids.put(timer, id);
			allObjects.timer = timer;
			break;

		case "Sun":
			Sun sun = new Sun();
			allObjects.ids.put(sun, id);
			allObjects.sun = sun;
			
			// TODO megfelelo pozicionalas kell
			SunGraphics sung = new SunGraphics(sun, new Dimension(800, 600));
			gamePanel.addToMapPanel(sung);
			gamePanel.addDrawable(sung);
			break;

		case "Asteroid":
			Asteroid a = new Asteroid();
			allObjects.ids.put(a, id);
			allObjects.asteroids.add(a);
			
			// TODO megfelelo panelmeret-atadas
			AsteroidGraphics ag
				= new AsteroidGraphics(a, new Dimension(900, 600), gamePanel);
			gamePanel.addToMapPanel(ag);
			gamePanel.addDrawable(ag);
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
			
			SettlerGraphics sg = new SettlerGraphics(s);
			gamePanel.addToMapPanel(sg);
			gamePanel.addDrawable(sg);
			break;

		case "AIRobot":
			AIRobot air = new AIRobot();
			allObjects.ids.put(air, id);
			allObjects.robots.add(air);
			
			AIRobotGraphics airg = new AIRobotGraphics(air);
			gamePanel.addToMapPanel(airg);
			gamePanel.addDrawable(airg);
			break;

		case "UFO":
			UFO ufo = new UFO();
			allObjects.ids.put(ufo, id);
			allObjects.ufos.add(ufo);
			
			UFOGraphics ufog = new UFOGraphics(ufo);
			gamePanel.addToMapPanel(ufog);
			gamePanel.addDrawable(ufog);
			break;

		case "TeleportingGate":
			TeleportingGate tg = new TeleportingGate();
			allObjects.ids.put(tg, id);
			allObjects.gates.add(tg);
			
			TeleportingGateGraphics tgg = new TeleportingGateGraphics(tg);
			gamePanel.addToMapPanel(tgg);
			gamePanel.addDrawable(tgg);
			break;

		default:
			throw new IllegalArgumentException("Invalid class name: " + typename + ".");
		}
	}

	/**
	 * Segedfuggveny az objektumok attributumai beallitasahoz.
	 * 
	 * @param o        Az objektum, amelynek attributumait be kell tolteni
	 * @param typename Az objektum tipusa szoveges azonositoval
	 * @param sc       Az objektum attributumai beolvasasat vegzo Scanner
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
			allObjects.sun.setGame(allObjects.game);
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
	 * 
	 * @param filename A beolvasando jatekkonfiguracios fajl neve
	 */
	public static void load(String filename) throws IOException {
		allObjects = new Proto.Objects();
		allObjects.clearRecipes();
		Scanner sc = new Scanner(new File(filename));

		String line = sc.nextLine();
		while (!line.equals("") & sc.hasNextLine()) {
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
	 * Segedfuggveny az objektumok deklaracioinak kiirasahoz (a konfiguracios fajl
	 * elso reszehez).
	 * 
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

		if (!allObjects.coals.isEmpty()) {
			ps.print("Coal");
			for (Coal co : allObjects.coals) {
				ps.print(" " + getId(co));
			}
			ps.println();
		}

		if (!allObjects.irons.isEmpty()) {
			ps.print("Iron");
			for (Iron ir : allObjects.irons) {
				ps.print(" " + getId(ir));
			}
			ps.println();
		}

		if (!allObjects.ices.isEmpty()) {
			ps.print("Ice");
			for (Ice ic : allObjects.ices) {
				ps.print(" " + getId(ic));
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
	 * Segedfuggveny az objektumok definicioinak kiirasahoz (a konfiguracios fajl
	 * masodik resze, az attributumok ertekeivel).
	 * 
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
	 * Kozos segedfuggveny a save-hez es a showAll-hoz: a ps PrintStream-re
	 * nyomtatja az aktualis jatekkonfiguraciot.
	 * 
	 * @param ps A PrintStream, ahova a mentes tortenik
	 */
	private static void saveToStream(PrintStream ps) {
		saveObjectIds(ps);
		ps.println();
		saveObjectAttributes(ps);
	}

	/**
	 * A filename nevu szoveges fajlba irja az aktualis jatekkonfiguraciot a
	 * konfiguracios fajl formatuma szerint.
	 * 
	 * @param filename A kimeneti fajl neve, ahova a jatekallapot mentodik
	 * @throws FileNotFoundException Akkor dobodik, ha a filename fajl nem hozhato
	 *                               letre vagy nem nyithato meg.
	 */
	public static void save(String filename) throws FileNotFoundException {
		PrintStream ps = new PrintStream(filename);
		saveToStream(ps);
		ps.close();
	}

	/*
	 * public static void showOne(String id) {
	 * System.out.println(allObjects.getObject(id).getDescription()); }
	 */

	/**
	 * A standard kimenetre irja az aktualis jatekkonfiguraciot a konfiguracios fajl
	 * formatuma szerint.
	 */
	public static void showAll() {
		saveToStream(System.out);
	}

	/**
	 * A prototipus program belepesi pontja. A tesztelesi uzemmod es a jatek uzemmod
	 * kozotti valasztast teszi lehetove a konzolon. Teszteles valasztasa eseten
	 * atiranyitja a Tester.main()-be a vezerlest. Jatek uzemmodban pedig ez a
	 * metodus vegzi a parancsok feldolgozasat.
	 * 
	 * @param args A program parancssori argumentumai
	 */
	/*public static void main(String[] args) {
		Controller controller = null;	// objektum-segedvaltozok
		Timer timer = null;
		
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Welcome in berenyi_kft's Proto program!");
			System.out.print("Please select whether you wish to test (Y) " + "or play (n) the prototype game. [Y/n]: ");
			String choice = sc.nextLine();
			if (choice.substring(0, 1).toLowerCase().equals("y")) {
				Tester.testerMain(args);
			} else {
				System.out.println("Now you can play the game on the console as a game.\n"
						+ "-------------------------------------------------------------------------------"
						+ "---------------------------------\n" + "List of the applicable system control commands:\n"
						+ "\trandom <is_random>\tSets randomized/deterministic behaviour.\n"
						+ "\tlog <is_logging>\tEnables/disables method logging on the console.\n"
						+ "\tinit\t\t\tAsks for players, initializes a whole new game, and starts it.\n"
						+ "\tstart\t\t\tStarts or resumes the game, waits for players' commands again.\n"
						+ "\tstop\t\t\tPauses the game so that no object can act (until a new start).\n"
						+ "\tshow\t\t\tPrints the current game configuration on the screen.\n"
						+ "\tsave <file>\t\tSaves the current configuration into the specified file.\n"
						+ "\tload <file>\t\tLoads a configuration from the specified file without start.\n"
						+ "\texit\t\t\tExits the prototype program, no further commands are processed.\n"
						+ "----------------------------------------------------++--------------------------"
						+ "--------------------------------\n" + "List of the applicable player control commands:\n"
						+ "\tpass\t\t\tYour player does not act, intentionally.\n"
						+ "\tmove <direction>:\tPlayer moves to its asteroids's direction-th neighbor.\n"
						+ "\t\t\t\tYou may also use the teleporting gates to move.\n"
						+ "\tdrill\t\t\tPlayer drills a rock layer down on his/her asteroid.\n"
						+ "\tmine\t\t\tPlayer mines the resource on his/her current place.\n"
						+ "\trestore <resource>\tPlayer restores a resource type of <resource> in the asteroid.\n"
						+ "\tcreate_robot\t\tYou create and release a new AI robot for "
						+ "a coal, an iron and a uranium.\n"
						+ "\tcreate_gate_pair\tYou create a new teleporting gate pair and store them for two\n"
						+ "\t\t\t\tirons, an ice and a uranium, providing that you have space for them at yours.\n"
						+ "\trelease_gate <teleport_gate>\tPlayer releases the chosen teleporting gate\n"
						+ "\t\t\t\t\tof id <teleport_gate> on the orbit of his/her current place.\n"
						+ "--------------------------------------------------------------------------------"
						+ "--------------------------------\n" + "\n");
				
				boolean running = false; // jatekallapot-valtozok
				boolean end = false;
				boolean exit = false;				
				while (!exit & sc.hasNextLine()) {
					String line = sc.nextLine();
					String[] tokens = line.split("\\s+");
					String cmd = tokens[0];

					controller = allObjects.getController();
					if (controller != null) {
						timer = controller.getGame().getTimer();
						running = (controller.getState() == State.RUNNING);
					}

					// Jatekutasitasok vizsgalata
					PlayerCommand playerCmd = PlayerCommand.fromString(cmd);
					if (running & (playerCmd != PlayerCommand.INVALID)) {
						Player pAct = controller.getActPlayer();
						pAct.actOnSettler(playerCmd, tokens);
						controller.nextPlayer();
					}
					else {
						// Rendszerutasitasok vizsgalata allapotgep-szeruen
						switch (cmd) {
						case "exit":
							if (!running) {
								end = true;
								exit = true;
								if (timer != null)
									timer.cancel();
								if (controller != null)
									controller.setState(State.EXITED);
								Proto.println("The prototype program has terminated.");
								// System.exit(0);
							}
							break;

						case "random":
							if (!running) {
								if (tokens.length == 1) {
									Proto.println("is_random = " + (random ? "true" : "false"));
								}
								else if (tokens.length >= 2) {
									if (tokens[1].equals("true"))
										setRandom(true);
									else if (tokens[1].equals("false"))
										setRandom(false);
									else
										throw new IllegalArgumentException(
												"Invalid argument for <is_random>: " + tokens[1] + ".");
								}
							}
							break;

						case "log":
							if (!running) {
								if (tokens.length == 1) {
									Proto.println("is_logging = " + (log ? "enabled" : "disabled"));
								}
								else if (tokens.length >= 2) {
									if (tokens[1].equals("true"))
										enableLogging(true);
									else if (tokens[1].equals("false"))
										enableLogging(false);
									else
										throw new IllegalArgumentException(
												"Invalid argument for <is_logging>: " + tokens[1] + ".");
								}
							}
							break;

						case "init":
							if (!running) {
								end = false;
								allObjects = new Proto.Objects();
								controller = new Controller();
								Proto.getAllObjects().setController(controller);
								controller.startGame(sc);
								
								// Idozites inditasa
								running = true;
								controller.setState(State.RUNNING);
								timer = controller.getGame().getTimer();
								timer.start();
							}
							break;

						case "load":
							if (!running) {
								end = false;
								if (tokens.length >= 2) {
									if (timer != null) {
										timer.cancel();
									}
									load(tokens[1]);
								}
							}
							break;

						case "start":
							if (!running & !end) {
								if (timer != null) {
									timer.start();
								}
								if (controller != null) {
									controller.setState(State.RUNNING);
								}
								running = true;
							}
							break;

						case "stop": // Szunetelteti a futo jatekot.
							if (running) {
								if (timer != null) {
									timer.stop();
								}
								if (controller != null) {
									controller.setState(State.PAUSED);
								}
								running = false;
							}
							break;

						case "save":
							if (!running) {
								if (tokens.length >= 2) {
									save(tokens[1]);
								}
							}
							break;

						case "show":
							if (!running) {
								if (tokens.length == 1) {
									showAll();
								} 
									 // else if (tokens.length >= 2) { showOne(tokens[1]); }
									 
							}
							break;

						default:
							System.out.println("Invalid operation, please type in a valid command.");
							break;
						}
					}

					if (controller != null) {
						if (controller.getState() == State.WON | controller.getState() == State.LOST) {
							running = false;
							end = true;
							if (timer != null) {
								timer.cancel();
								timer = null;
							}
							controller.setState(State.INIT);
							// break;
						}
					}
				}
				sc.close();
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("The file you specified was not found by the prototype program.");
			e.printStackTrace();
			// System.exit(1);
		}
		catch (Exception e) {
			System.out.println("The prototype program has been ended by this exception:");
			e.printStackTrace();
			// System.exit(1);
		}
		finally {
			if (timer != null) {
				timer.cancel();
				timer = null;
			}
		}
	}*/

}
