package berenyi_kft;

import java.util.ArrayList;

public class Game {
	
	/**
	 * a játékban jelenlévõ aszteroidák kollekciója
	 */
	ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	
	/**
	 * Az aszteroidaövbeli nap
	 */
	Sun sun;
	
	/**
	 * az adott pillanatban életben levõ telepesek listája
	 */
	ArrayList<Settler> settlersAlive = new ArrayList<Settler>();
	
	/**
	 * A játékban felépíthetõ dolgok receptjeinek a kollekciója.
	 * [0...3]
	 */
	ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	//--------------------------------------------
	
	/**
	 * Inicializálja az aszteroidákat, a Napot, és elhelyezi a telepeseket 
	 * valamelyik aszteroidán. Egyes aszteroidák magját üresen hagyja,
	 * másokban pedig véletlenszerûen valamilyen nyersanyagot hoz létre.
	 * Beállítja az aszteroidák szomszédsági viszonyait is.
	 */
	public void startGame() {
		
	}
	
	/**
	 * Ha van még élõ telepes a játékban (settlersAlive nem üres),
	 * akkor a telepesek gyõzelmét írja ki a képernyõre, ellenkezõ 
	 * esetben a telepesek vereségérõl szóló üzenetet jelenít meg.
	 */
	public void endGame() {
		
	}
	
	/**
	 * Törli a meghaló s telepest a játékból. 
	 * Ha nincs több életben levõ telepes,
	 *  akkor meghívja az endGame() függvényt.
	 * @param s
	 */
	public void removeSettler(Settler s) {
		
	}
	
	/**
	 * Törli a megsemmisülõ a aszteroidát az asteroids kollekcióból.
	 * @param a
	 */
	public void removeAsteroid(Asteroid a) {
		
	}
	
	/**
	 * Visszaadja a játék összes aszteroidájából álló kollekciót.
	 * @return
	 */
	public ArrayList<Asteroid> getAsteroids(){
		return this.asteroids;
	}
	
	//???----------------------------------------------
	/**
	 * Visszaadja a teleportkapu-pár felépítéséhez szükséges 
	 * receptet tároló Recipe objektumot.
	 * @return
	 */
	public Recipe getGatePairRecipe() {
		
	}
	
	/**
	 * Visszaadja az AIRobot felépítéséhez szükséges 
	 * receptet tároló Recipe objektumot
	 * @return
	 */
	public Recipe getAIRobotRecipe() {
		
	}
	
	/**
	 * Visszaadja az ûrbázis felépítéséhez szükséges 
	 * receptet tároló Recipe objektumot.
	 * @return
	 */
	public Recipe getSpaceBaseRecipe() {
		
	}
	
}
