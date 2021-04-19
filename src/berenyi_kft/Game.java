package berenyi_kft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

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
	
	// private Controller controller;
	
	//--------------------------------------------
	
	
	public String getDescription() { 
		
		String str="";
		
		String id=Proto.getId(this);
		str+="Game "+id+"\n";
		
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
	 * Inicializalja az aszteroidakat, a Napot, es elhelyezi a telepeseket 
	 * valamelyik aszteroidan. Egyes aszteroidak magjat uresen hagyja,
	 * masokban pedig veletlenszeruen valamilyen nyersanyagot hoz letre.
	 * Beallitja az aszteroidak szomszedsagi viszonyait is.
	 */
	public void startGame() {
		Random random = new Random();
		
		/* A Controller es a Playerek mar be vannak allitva,
		 * a Playerek telepeshez vannak rendelve, es a telepesek
		 * is ott vannak a playersAlive-ban.
		 */
		
		/* Jatekobjektumok letrehozasa, nyilvantartasa,
		 * attributumaik beallitasa */
		// robotRecipe
		Recipe robotRecipe = new Recipe();
		robotRecipe.addResource(new Coal());
		robotRecipe.addResource(new Iron());
		robotRecipe.addResource(new Uranium());
		recipes.add(robotRecipe);
		Proto.getAllObjects().setRobotRecipe(robotRecipe);
		for (Resource r : robotRecipe.getResources())
			r.addToGame();
		
		// gatePairRecipe
		Recipe gatePairRecipe = new Recipe();
		gatePairRecipe.addResource(new Iron());
		gatePairRecipe.addResource(new Iron());
		gatePairRecipe.addResource(new Ice());
		gatePairRecipe.addResource(new Uranium());
		recipes.add(gatePairRecipe);
		Proto.getAllObjects().setGatePairRecipe(gatePairRecipe);
		for (Resource r : gatePairRecipe.getResources())
			r.addToGame();
		
		// spaceBaseRecipe
		Recipe spaceBaseRecipe = new Recipe();
		for (int i = 0; i < 3; i++) {
			spaceBaseRecipe.addResource(new Coal());
			spaceBaseRecipe.addResource(new Iron());
			spaceBaseRecipe.addResource(new Ice());
			spaceBaseRecipe.addResource(new Uranium());
		}
		recipes.add(spaceBaseRecipe);
		Proto.getAllObjects().setSpaceBaseRecipe(spaceBaseRecipe);
		for (Resource r : spaceBaseRecipe.getResources())
			r.addToGame();
		
		// nyersanyagszamok
		int nCoals = 3 + random.nextInt(1);
		int nIrons = 3 + random.nextInt(3);
		int nIces = 3 + random.nextInt(1);
		int nUraniums = 3 + random.nextInt(2);
		// aszteroidaszamok
		int nEmpty = random.nextInt(2);
		int nAsteroids = nCoals + nIrons + nIces + nUraniums + nEmpty;
		
		// allResourcesRecipe - aszteroidak inicializalasahoz
		Recipe allResourcesRecipe = new Recipe();
		for (int i = 0; i < nCoals; i++)
			allResourcesRecipe.addResource(new Coal());
		for (int i = 0; i < nIrons; i++)
			allResourcesRecipe.addResource(new Iron());
		for (int i = 0; i < nIces; i++)
			allResourcesRecipe.addResource(new Ice());
		for (int i = 0; i < nUraniums; i++)
			allResourcesRecipe.addResource(new Uranium());
		
		// timer	
		timer = new Timer(5000, 3000);
		Proto.getAllObjects().setTimer(timer);

		// sun
		sun = new Sun();
		timer.addSteppable(sun);
		Proto.getAllObjects().setSun(sun);
		
		// asteroids
		for (int i = 0; i < nAsteroids; i++) {
			Asteroid a = new Asteroid();
			a.setRockLayerThickness(random.nextInt(5));
			a.setGame(this);
			a.setSun(sun);
			if (i < nAsteroids - nEmpty) {
				Resource r = allResourcesRecipe.getResources().get(i);
				r.addToGame();
				a.addResource(r);
			}
			asteroids.add(a);
			Proto.getAllObjects().addAsteroid(a);
		}
		
		// nyersanyagok elkeverese
		Collections.shuffle(asteroids);
		
		// napkozeli aszteroidak veletlenszeruen
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
			UFO ufo = new UFO(timer);
			Asteroid a = asteroids.get(random.nextInt(nAsteroids));
			a.accept(ufo);
			ufo.setPlace(a);
			Proto.getAllObjects().addUFO(ufo);
		}
	}
	
	/**
	 * Ha van meg elo telepes a jatekban (settlersAlive nem ures),
	 * akkor a telepesek gyozelmet irja ki a kepernyore, ellenkezo 
	 * esetben a telepesek veresegerol szolo uzenetet jelenit meg.
	 */
	public void endGame() {
		System.out.println("Game's endGame() has been called");
		Controller controller = Proto.getAllObjects().getController();
		if (settlersAlive.size() > 0) {
			System.out.println("A telepesek nyertek, felepult az urbazis!");
			controller.endGame(State.WON);
		} else {
			System.out.println("A telepesek vesztettek, mindegyikuk meghalt.");
			controller.endGame(State.LOST);
		}	
	}
	
	/**
	 * Torli a meghalo telepest a jatekbol. Ha nincs tobb eletben levo
	 * telepes, akkor meghivja az endGame() fuggvenyt.
	 * @param s A meghalo telepes
	 */
	public void removeSettler(Settler s) {
		System.out.println("Game's removeSettler(s: Settler) has been called");
		settlersAlive.remove(s);
		Proto.getAllObjects().getController().removePlayer(s);
		Proto.getAllObjects().removeSettler(s);
		if (settlersAlive.isEmpty()) {
			endGame();
		}
	}
	
	/**
	 * Torli a megsemmisulo a aszteroidat az asteroids kollekciobol.
	 * @param a A megsemmisulo aszteroida
	 */
	public void removeAsteroid(Asteroid a) {
		System.out.println("Game's removeAsteroid(a: Asteroid) has been called");
		asteroids.remove(a);
	}
	
	/**
	 * Visszaadja a jatek osszes aszteroidajabol allo kollekciot.
	 * @return Az aszteroidak listaja
	 */
	public ArrayList<Asteroid> getAsteroids() {
		System.out.println("Game's getAsteroids() has been called");
		return this.asteroids;
	}
	
	/**
	 * Uj receptet ad a jatekban levő receptek listajahoz.
	 * @param recipe Az uj recept
	 */
	public void addRecipe(Recipe recipe) {
		System.out.println("Game's addRecipe(r: Recipe) has been called");
		recipes.add(recipe);		
	}
	
	/**
	 * Visszaadja az AIRobot felepitesehez szukseges 
	 * receptet tarolo Recipe objektumot
	 * @return Az AIRobot epitesehez szukseges nyersanyagok receptje
	 */
	public Recipe getAIRobotRecipe() {
		System.out.println("Game's getAIRobotRecipe() has been called");
		return recipes.get(0);
	}
	
	/**
	 * Visszaadja a teleportkapu-par felepitesehez szukseges 
	 * receptet tarolo Recipe objektumot.
	 * @return A teleportkapu-par epitesehez szukseges nyersanyagok receptje
	 */
	public Recipe getGatePairRecipe() {
		System.out.println("Game's getGatePairRecipe() has been called");
		return recipes.get(1);
	}
	
	/**
	 * Visszaadja az urbazis felepitesehez szukseges 
	 * receptet tarolo Recipe objektumot.
	 * @return Az urbazis megepitesehez szukseges nyersanyagok receptje
	 */
	public Recipe getSpaceBaseRecipe() {
		System.out.println("Game's getSpceBaseRecipe() has been called");
		return recipes.get(2);
	}
	
	/**
	 * Visszaadja a jatek idozitojet.
	 * @return A jatek idozitoje
	 */
	public Timer getTimer() {
		System.out.println("Game's getTimer() has been called");
		return timer;
	}
	
	/**
	 * Beallitja a jatek idozitojet.
	 * @param timer A beallitando idozito
	 */
	public void setTimer(Timer timer) {
		System.out.println("Game's setTimer(Timer timer) has been called");
		this.timer = timer;
	}
	
	/**
	 * Uj telepest ad a jatek elo telepeseinek listajahoz.
	 * @param s Az uj telepes
	 */
	public void addSettler(Settler s) {
		System.out.println("Game's addSettler(s: Settler) has been called");
		settlersAlive.add(s);
	}
	
	/**
	 * Uj aszteroidat ad a jatekhoz.
	 * @param a Az uj aszteroida
	 */
	public void addAsteroid(Asteroid a) {
		System.out.println("Game's addAsteroid(a: Asteroid) has been called");
		asteroids.add(a);
	}
	
	/**
	 * Beallitja az aszteroidaovben levo Napot.
	 * @return s A jatekban beallitando Nap
	 */
	public void setSun(Sun s) {
		System.out.println("Game's setSun(s: Sun) has been called");
		sun = s;
	}
	
	/**
	 * Megadja a jatekban levo telepesek szamat.
	 * @return Az elo telepesek szama
	 */
	public int getSizeOfSettlersAlive() {
		System.out.println("Game's getSizeOfSettlersAlive() has been called");
		return this.settlersAlive.size();
	}
	
	/**
	 * Visszater a jatekban levo telepesek kollekciojaval.
	 * @return Az elo telepesek listaja
	 */
	public ArrayList<Settler> getSettlers() {
		System.out.println("Game's getSettlers() has been called");
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
