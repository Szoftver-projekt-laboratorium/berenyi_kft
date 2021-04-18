package berenyi_kft;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A jatekot reprezentalo osztaly, amely
 * osszefogja az aszteroidaov objektumait
 * @author berenyi_kft
 */
public class Game {
	/* Segedvaltozo csak a szekeletonhoz,
	 * a jatek vegenek ellenorzesehez.
	 */ 
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
	private Timer timer;
	
	//private Controller controller;
	
	// TODO: (Sajnos) ugy tunik, kell referenciat tarolni a controllerre,
	//  kulonben korulmenyes az endGame tovabbitasa a vezerlo fele.)
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
		// TODO
		// Palya inicializalasa...
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
	 * Torli a meghalo telepest a jatekbol. 
	 * Ha nincs tobb eletben levo telepes,
	 * akkor meghivja az endGame() fuggvenyt.
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
	 * Uj telepest ad a jatek elo telepeseinek listajahoz
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
