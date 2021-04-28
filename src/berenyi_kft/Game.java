package berenyi_kft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import berenyi_kft_GUI.*;

/**
 * A jatekot reprezentalo osztaly, amely
 * osszefogja az aszteroidaov objektumait
 * @author berenyi_kft
 */
public class Game {
	/* Segedvaltozo csak a szekeletonhoz, a jatek vegenek ellenorzesehez. */
	private boolean endGameFlag = false;

	/**
	 * A jatekban jelenlevo aszteroidak kollekcioja
	 */
	private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	
	/**
	 * Az aszteroidaovbeli nap
	 */
	private Sun sun;
	
	/**
	 * Az adott pillanatban eletben levo telepesek listaja
	 */
	private ArrayList<Settler> settlersAlive = new ArrayList<Settler>();
	
	/**
	 * A jatekban felepitheto dolgok receptjeinek a kollekcioja.
	 * [0...3]
	 */
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	/**
	 * A jatekbeli idozito
	 */
	private Timer timer = null;
	
	private Controller controller = null;
	
	/**
	 * Az aszteroidamezo grafikus megjelenitoje 
	 */
	private GamePanel gamePanel = null;
	
	//--------------------------------------------
	
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public String getDescription() { 
		
		String str="";
		
		String id=Proto.getId(this);
		str+="Game "+id+"\n";
		
		/*
		String controllerId=Proto.getId(controller);
		str+="\tcontroller "+controllerId+"\n";
		*/
		
		String timerId=Proto.getId(timer);
		str+="\ttimer "+timerId+"\n";
		
		// str+="\trecipes robotRecipe gatePairRecipe spaceBaserecipe allResourcesRecipe\n";
		if(!recipes.isEmpty()) {   
			str+="\trecipes";
			for(Recipe recipe : recipes) {
				String recipeId=Proto.getId(recipe);
				str+=" "+recipeId;
			}
			str+="\n";
		}
		else
			str+="\trecipes null\n";
		
		String sunId=Proto.getId(sun);
		str+="\tsun "+sunId+"\n";
		
		if(!asteroids.isEmpty()) {   
			str+="\tasteroids";
			for(Asteroid a : asteroids) {
				String asteroidId=Proto.getId(a);
				str+=" "+asteroidId;
			}
			str+="\n";
		}
		else
			str+="\tasteroids null\n";
		
		if(!settlersAlive.isEmpty()) {   
			str+="\tsettlersAlive";
			for(Settler s : settlersAlive) {
				String settlerId=Proto.getId(s);
				str+=" "+settlerId;
			}
			str+="\n";
		}
		else
			str+="\tsettlersAlive null\n";
		
		return str;	
	}
	
	/**
	 * Visszater a jatek vezerlojevel.
	 * @return A jatekot vezerlo objektum
	 */
	public Controller getController() {
		return controller;
	}
	
	/**
	 * Beallitja a jatek vezerlojet.
	 * @param controller A jatek vezerlo objektuma
	 */
	public void setController(Controller controller) {
		Proto.println(Proto.getId(this) + ".setController("
					+ Proto.getId(controller) + ")");
		this.controller = controller;
	}
	
	/**
	 * Inicializalja az aszteroidakat, a Napot, es elhelyezi a telepeseket 
	 * valamelyik aszteroidan. Egyes aszteroidak magjat uresen hagyja,
	 * masokban pedig veletlenszeruen valamilyen nyersanyagot hoz letre.
	 * Beallitja az aszteroidak szomszedsagi viszonyait is.
	 */
	// TODO Grafikus objektumok létreozása, gamePanel-hez hozzáadása
	public void startGame() {
		Proto.println(Proto.getId(this) + ".startGame()");
		Proto.incrTabs();
		
		Random random = new Random();
		
		/* A Controller es a Playerek mar be vannak allitva,
		 * a Playerek telepeshez vannak rendelve, es a telepesek
		 * is ott vannak a playersAlive-ban.
		 */
		
		/* Jatekobjektumok letrehozasa, nyilvantartasa,
		 * attributumaik beallitasa */
		// Nyersanyag-segedvaltozok az inicializalashoz
		Coal co1 = new Coal(), co2 = null;
		Iron ir1 = new Iron(), ir2 = null;
		Ice ic1 = new Ice(), ic2 = null;
		Uranium ur1 = new Uranium(), ur2 = null;
		
		// robotRecipe
		Recipe robotRecipe = new Recipe();
		Proto.getAllObjects().setRobotRecipe(robotRecipe);
		recipes.add(robotRecipe);
		co2 = co1.clone();
		co2.addToGame();
		robotRecipe.addResource(co2);
		ir2 = ir1.clone();
		ir1.addToGame();
		robotRecipe.addResource(ir2);
		ur2 = ur1.clone();
		ur2.addToGame();
		robotRecipe.addResource(ur2);
		
		// gatePairRecipe
		Recipe gatePairRecipe = new Recipe();
		Proto.getAllObjects().setGatePairRecipe(gatePairRecipe);
		recipes.add(gatePairRecipe);
		ir2 = ir1.clone();
		ir2.addToGame();
		gatePairRecipe.addResource(ir2);
		ir2 = ir1.clone();
		ir2.addToGame();
		gatePairRecipe.addResource(ir2);
		ic2 = ic1.clone();
		ic2.addToGame();
		gatePairRecipe.addResource(ic2);
		ur2 = ur1.clone();
		ur2.addToGame();
		gatePairRecipe.addResource(ur2);
		
		// spaceBaseRecipe
		Recipe spaceBaseRecipe = new Recipe();
		Proto.getAllObjects().setSpaceBaseRecipe(spaceBaseRecipe);
		recipes.add(spaceBaseRecipe);
		for (int i = 0; i < 3; i++) {
			co2 = co1.clone();
			co2.addToGame();
			spaceBaseRecipe.addResource(co2);
			ir2 = ir1.clone();
			ir2.addToGame();
			spaceBaseRecipe.addResource(ir2);
			ic2 = ic1.clone();
			ic2.addToGame();
			spaceBaseRecipe.addResource(ic2);
			ur2 = ur1.clone();
			ur2.addToGame();
			spaceBaseRecipe.addResource(ur2);
		}
		
		// nyersanyagszamok
		int nCoals = 3 + random.nextInt(1);
		int nIrons = 3 + random.nextInt(3);
		int nIces = 3 + random.nextInt(1);
		int nUraniums = 3 + random.nextInt(2);
		// aszteroidaszamok
		int nEmpty = random.nextInt(2);
		int nAsteroids = nCoals + nIrons + nIces + nUraniums + nEmpty;
		
		// timer
		timer = new Timer(5000, 3000);
		Proto.getAllObjects().setTimer(timer);
		
		// sun
		sun = new Sun();
		Proto.getAllObjects().setSun(sun);
		timer.addSteppable(sun);
		
		// allResourcesList - aszteroidak inicializalasahoz
		List<Resource> allResourcesList = new ArrayList<Resource>();
		for (int i = 0; i < nCoals; i++) {
			co2 = co1.clone();
			co2.addToGame();
			allResourcesList.add(co2);
		}
		for (int i = 0; i < nIrons; i++) {
			ir2 = ir1.clone();
			ir2.addToGame();
			allResourcesList.add(ir2);
		}
		for (int i = 0; i < nIces; i++) {
			ic2 = ic1.clone();
			ic2.addToGame();
			allResourcesList.add(ic2);
		}
		for (int i = 0; i < nUraniums; i++) {
			ur2 = ur1.clone();
			ur2.addToGame();
			allResourcesList.add(ur2);
		}
		
		// asteroids
		for (int i = 0; i < nAsteroids; i++) {
			Asteroid a = new Asteroid();
			Proto.getAllObjects().addAsteroid(a);
			
			AsteroidGraphics ag = new AsteroidGraphics(a);
			gamePanel.addToMapPanel(ag);
			gamePanel.addDrawable(ag);
			
			a.setRockLayerThickness(random.nextInt(5));
			a.setGame(this);
			a.setSun(sun);
			if (i < nAsteroids - nEmpty) {
				Resource r = allResourcesList.get(i);
				a.addResource(r);
			}
			asteroids.add(a);
		}
		
		// nyersanyagok elkeverese
		Collections.shuffle(asteroids);
		// napkozeli aszteroidak (veletlenszeruen)
		int nCloseToSun = 1 + random.nextInt(1);
		for (int i = 0; i < nCloseToSun; i++)
			sun.addNeighbor(asteroids.get(i));
		
		// aszteroida szomszedsagok beallitasa
		// elso kor: osszefuggo fagraf epitese (topologikus sorrendben)
		for (int i = 1; i < nAsteroids; i++) {
			Asteroid a2 = asteroids.get(i);
			int precedentIdx = (i == 1 ? 0 : random.nextInt(i - 1));
			Asteroid a1 = asteroids.get(precedentIdx);
			a1.accept(a2);
			a2.accept(a1);
		}
		// masodik kor: tovabbi veletlen elek az aszteroidak grafjaban
		if (nAsteroids > 1) {
			int nAdditionalNeighbors = (nAsteroids * nAsteroids / 10);
			for (int i = 0; i < nAdditionalNeighbors; i++) {
				Asteroid a1 = asteroids.get(random.nextInt(nAsteroids - 1));
				Asteroid a2 = asteroids.get(random.nextInt(nAsteroids - 1));
				while (a1 == a2 || a1.getNeighbors().contains(a2)) {
					a1 = asteroids.get(random.nextInt(nAsteroids - 1));
					a2 = asteroids.get(random.nextInt(nAsteroids - 1));
				}
				a1.accept(a2);
				a2.accept(a1);
			}
		}
		
		// settlers
		Asteroid mainAsteroid = asteroids.get(random.nextInt(nAsteroids));
		for (Settler s : settlersAlive) {
			s.setGame(this);
			s.setTimer(timer);
			mainAsteroid.addCharacter(s);
			s.setPlace(mainAsteroid);
		}
		
		// ufos
		for (int i = 0; i < 2; i++) {
			UFO ufo = new UFO();
			Proto.getAllObjects().addUFO(ufo);
			ufo.setTimer(timer);
			timer.addSteppable(ufo);
			
			Asteroid a = asteroids.get(random.nextInt(nAsteroids));
			a.accept(ufo);
			ufo.setPlace(a);
		}
		Proto.decrTabs();
	}
	
	/**
	 * Ha van meg elo telepes a jatekban (settlersAlive nem ures),
	 * akkor a telepesek gyozelmet irja ki a kepernyore, ellenkezo 
	 * esetben a telepesek veresegerol szolo uzenetet jelenit meg.
	 */
	public void endGame() {
		Proto.println(Proto.getId(this) + ".endGame()");
		Proto.incrTabs();
		Controller controller = Proto.getAllObjects().getController();
		if (settlersAlive.size() > 0)
			controller.endGame(State.WON);
		else
			controller.endGame(State.LOST);
		Proto.decrTabs();
	}
	
	/**
	 * Torli a meghalo telepest a jatekbol. Ha nincs tobb eletben levo
	 * telepes, akkor meghivja az endGame() fuggvenyt.
	 * @param s A meghalo telepes
	 */
	public void removeSettler(Settler s) {
		Proto.println(Proto.getId(this) + ".removeSettler("
					+ Proto.getId(s) + ")");
		Proto.incrTabs();
		settlersAlive.remove(s);
		Proto.getAllObjects().getController().removePlayer(s);
		Proto.getAllObjects().removeSettler(s);
		if (settlersAlive.isEmpty()) {
			endGame();
		}
		Proto.decrTabs();
	}
	
	/**
	 * Torli a megsemmisulo a aszteroidat az asteroids kollekciobol.
	 * @param a A megsemmisulo aszteroida
	 */
	public void removeAsteroid(Asteroid a) {
		Proto.println(Proto.getId(this) + ".removeAsteroid("
					+ Proto.getId(a) + ")");
		asteroids.remove(a);
	}
	
	/**
	 * Visszaadja a jatek osszes aszteroidajabol allo kollekciot.
	 * @return Az aszteroidak listaja
	 */
	public ArrayList<Asteroid> getAsteroids() {
		return this.asteroids;
	}
	
	/**
	 * Uj receptet ad a jatekban levő receptek listajahoz.
	 * @param recipe Az uj recept
	 */
	public void addRecipe(Recipe recipe) {
		Proto.println(Proto.getId(this) + ".addRecipe("
					+ Proto.getId(recipe) + ")");
		recipes.add(recipe);		
	}
	
	/**
	 * Visszaadja az AIRobot felepitesehez szukseges 
	 * receptet tarolo Recipe objektumot
	 * @return Az AIRobot epitesehez szukseges nyersanyagok receptje
	 */
	public Recipe getAIRobotRecipe() {
		Proto.println(Proto.getId(this) + ".getAIRobotRecipe()");
		return recipes.get(0);
	}
	
	/**
	 * Visszaadja a teleportkapu-par felepitesehez szukseges 
	 * receptet tarolo Recipe objektumot.
	 * @return A teleportkapu-par epitesehez szukseges nyersanyagok receptje
	 */
	public Recipe getGatePairRecipe() {
		Proto.println(Proto.getId(this) + ".getGatePairRecipe()");
		return recipes.get(1);
	}
	
	/**
	 * Visszaadja az urbazis felepitesehez szukseges 
	 * receptet tarolo Recipe objektumot.
	 * @return Az urbazis megepitesehez szukseges nyersanyagok receptje
	 */
	public Recipe getSpaceBaseRecipe() {
		Proto.println(Proto.getId(this) + ".getSpaceBaseRecipe()");
		return recipes.get(2);
	}
	
	/**
	 * Visszaadja a jatek idozitojet.
	 * @return A jatek idozitoje
	 */
	public Timer getTimer() {
		return timer;
	}
	
	/**
	 * Beallitja a jatek idozitojet.
	 * @param timer A beallitando idozito
	 */
	public void setTimer(Timer timer) {
		Proto.println(Proto.getId(this) + ".setTimer("
				+ Proto.getId(timer) + ")");
		this.timer = timer;
	}
	
	/**
	 * Uj telepest ad a jatek elo telepeseinek listajahoz.
	 * @param s Az uj telepes
	 */
	public void addSettler(Settler s) {
		Proto.println(Proto.getId(this) + ".addSettler("
				+ Proto.getId(s) + ")");
		settlersAlive.add(s);
	}
	
	/**
	 * Uj aszteroidat ad a jatekhoz.
	 * @param a Az uj aszteroida
	 */
	public void addAsteroid(Asteroid a) {
		Proto.println(Proto.getId(this) + ".addAsteroid("
				+ Proto.getId(a) + ")");
		asteroids.add(a);
	}
	
	/**
	 * Beallitja az aszteroidaovben levo Napot.
	 * @return s A jatekban beallitando Nap
	 */
	public void setSun(Sun s) {
		Proto.println(Proto.getId(this) + ".setSun("
				+ Proto.getId(s) + ")");
		sun = s;
	}
	
	/**
	 * Megadja a jatekban levo telepesek szamat.
	 * @return Az elo telepesek szama
	 */
	// Csak a szkeletonhoz volt szukseges.
	public int getSizeOfSettlersAlive() {
		return this.settlersAlive.size();
	}
	
	/**
	 * Visszater a jatekban levo telepesek kollekciojaval.
	 * @return Az elo telepesek listaja
	 */
	public ArrayList<Settler> getSettlers() {
		Proto.println(Proto.getId(this) + ".getSettlers()");
		return settlersAlive;
	}
	
	// Csak a szkeleton tesztelesehez hasznalt fuggveny.
	public boolean isEndGameFlag() {
		return endGameFlag;
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
				/* case "controller":
					controller = (Controller)Proto.getObject(tokens[1]);
					break; */
				
				case "timer":
					timer = (Timer)Proto.getObject(tokens[1]);
					break;
					
				case "sun":
					sun = (Sun)Proto.getObject(tokens[1]);
					break;
					
				case "asteroids":
					for (int i = 1; i < tokens.length; i++) {
						Asteroid a = (Asteroid)Proto.getObject(tokens[i]);
						if (a != null)
							asteroids.add(a);
					}
					break;
					
				case "settlersAlive":
					for (int i = 1; i < tokens.length; i++) {
						Settler s = (Settler)Proto.getObject(tokens[i]);
						if (s != null)
							settlersAlive.add(s);
					}
					break;
					
				case "recipes":
					for (int i = 1; i < tokens.length; i++) {
						Recipe r = (Recipe)Proto.getObject(tokens[i]);
						if (r != null)
							recipes.add(r);
					}
					break;
					
				default:
					break;
			}
		}
	}
}
