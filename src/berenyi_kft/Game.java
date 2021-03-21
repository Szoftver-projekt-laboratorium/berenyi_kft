package berenyi_kft;

import java.util.ArrayList;

public class Game {
	//praktikus okok miatt + attributum 
	boolean game = false;
	
	/**
	 * a j�t�kban jelenl�v� aszteroid�k kollekci�ja
	 */
	ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	
	/**
	 * Az aszteroida�vbeli nap
	 */
	Sun sun;
	
	/**
	 * az adott pillanatban �letben lev� telepesek list�ja
	 */
	ArrayList<Settler> settlersAlive = new ArrayList<Settler>();
	
	/**
	 * A j�t�kban fel�p�thet� dolgok receptjeinek a kollekci�ja.
	 * [0...3]
	 */
	// Ezeknek a megkonstruálása még szép.
	// Ezt talán konstruktor csinálja, ugye, ne a startGame().
	ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	//--------------------------------------------
	
	/**
	 * Inicializ�lja az aszteroid�kat, a Napot, �s elhelyezi a telepeseket 
	 * valamelyik aszteroid�n. Egyes aszteroid�k magj�t �resen hagyja,
	 * m�sokban pedig v�letlenszer�en valamilyen nyersanyagot hoz l�tre.
	 * Be�ll�tja az aszteroid�k szomsz�ds�gi viszonyait is.
	 */
	public void startGame() {
		// TODO
		/* Lehet, hogy most még nem kell konkrétan megvalósítanunk;
		 ott lesznek helyette a tesztesetek saját inicializálgatásai
		 a Skeleton-ban. */
	}
	
	/**
	 * Ha van m�g �l� telepes a j�t�kban (settlersAlive nem �res),
	 * akkor a telepesek gy�zelm�t �rja ki a k�perny�re, ellenkez� 
	 * esetben a telepesek veres�g�r�l sz�l� �zenetet jelen�t meg.
	 */
	public void endGame() {
		System.out.println("Game's endGame() has been called");
		if (settlersAlive.size() > 0) {
			System.out.println("A telepesek nyertek, fel?ep?lt az ?rbazis.");
		} else {
			System.out.println("A telepesek vesztettek, mindegyik?k meghalt.");
		}
	}
	
	/**
	 * T�rli a meghal� s telepest a j�t�kb�l. 
	 * Ha nincs t�bb �letben lev� telepes,
	 *  akkor megh�vja az endGame() f�ggv�nyt.
	 * @param s
	 */
	public void removeSettler(Settler s) {
		System.out.println("Game's removeSettler(s: Settler) has been called");
		settlersAlive.remove(s);
		if (settlersAlive.isEmpty()) {
			endGame();
		}
	}
	
	/**
	 * T�rli a megsemmis�l� a aszteroid�t az asteroids kollekci�b�l.
	 * @param a
	 */
	public void removeAsteroid(Asteroid a) {
		System.out.println("Game's removeAsteroid(a: Asteroid) has been called");
		asteroids.remove(a);
	}
	
	/**
	 * Visszaadja a j�t�k �sszes aszteroid�j�b�l �ll� kollekci�t.
	 * @return
	 */
	public ArrayList<Asteroid> getAsteroids(){
		System.out.println("Game's getAsteroids() has been called");
		return this.asteroids;
	}
	
	/**
	 * Visszaadja az AIRobot fel�p�t�s�hez sz�ks�ges 
	 * receptet t�rol� Recipe objektumot
	 * @return
	 */
	// --------------------------------------------------
	public Recipe getAIRobotRecipe() {
		System.out.println("Game's getAIRobotRecipe() has been called");
		return recipes.get(0);
	}
	
	//----------------------------------------------
	/**
	 * Visszaadja a teleportkapu-p�r fel�p�t�s�hez sz�ks�ges 
	 * receptet t�rol� Recipe objektumot.
	 * @return
	 */
	// -----------------------------------------------
	public Recipe getGatePairRecipe() {
		System.out.println("Game's getGatePairRecipe() has been called");
		return recipes.get(1);
	}
	
	
	/**
	 * Visszaadja az �rb�zis fel�p�t�s�hez sz�ks�ges 
	 * receptet t�rol� Recipe objektumot.
	 * @return
	 */
	public Recipe getSpaceBaseRecipe() {
		System.out.println("Game's getSpaceBaseRecipe() has been called");
		return recipes.get(2);
	}
	
	
	/**
	 * Visszaadja egy receptet, amely az aszteroidaövben található
	 * mindenfajta nyersanyagból egy-egy példányt tartalmaz.
	 * @return
	 */
	/*
	Kell ilyen az inicializáláshoz, vagy kikerülhető?
	public Recipe getAllTypeOfResources() {
		return recipes.get(3);
	}
	*/
	
	public void addSettler(Settler s) {
		System.out.println("Game's addSettler(s: Settler) has been called");
		settlersAlive.add(s);
	}
	
	public void addAsteroid(Asteroid a) {
		System.out.println("Game's addAsteroid(a: Asteroid) has been called");
		asteroids.add(a);
	}
	
	public void setSun(Sun s) {
		System.out.println("Game's setSun(s: Sun) has been called");
		sun=s;
	}
	
	public int getSizeOfSettlersAlive() {
		System.out.println("Game's getSizeOfSettlersAlive() has been called");
		return this.settlersAlive.size();
	}
	
	public int getSizeOfAsteroids() {
		System.out.println("Game's getSizeOfAsteroids() has been called");
		return this.asteroids.size();
	}
	
	public void addReceipt(Recipe r) {
		recipes.add(r);
		
	}
}
