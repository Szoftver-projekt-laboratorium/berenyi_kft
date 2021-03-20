package berenyi_kft;

import java.util.ArrayList;

public class Game {
	
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
	ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	//--------------------------------------------
	
	/**
	 * Inicializ�lja az aszteroid�kat, a Napot, �s elhelyezi a telepeseket 
	 * valamelyik aszteroid�n. Egyes aszteroid�k magj�t �resen hagyja,
	 * m�sokban pedig v�letlenszer�en valamilyen nyersanyagot hoz l�tre.
	 * Be�ll�tja az aszteroid�k szomsz�ds�gi viszonyait is.
	 */
	public void startGame() {
		
	}
	
	/**
	 * Ha van m�g �l� telepes a j�t�kban (settlersAlive nem �res),
	 * akkor a telepesek gy�zelm�t �rja ki a k�perny�re, ellenkez� 
	 * esetben a telepesek veres�g�r�l sz�l� �zenetet jelen�t meg.
	 */
	public void endGame() {
		
	}
	
	/**
	 * T�rli a meghal� s telepest a j�t�kb�l. 
	 * Ha nincs t�bb �letben lev� telepes,
	 *  akkor megh�vja az endGame() f�ggv�nyt.
	 * @param s
	 */
	public void removeSettler(Settler s) {
		
	}
	
	/**
	 * T�rli a megsemmis�l� a aszteroid�t az asteroids kollekci�b�l.
	 * @param a
	 */
	public void removeAsteroid(Asteroid a) {
		
	}
	
	/**
	 * Visszaadja a j�t�k �sszes aszteroid�j�b�l �ll� kollekci�t.
	 * @return
	 */
	public ArrayList<Asteroid> getAsteroids(){
		return this.asteroids;
	}
	
	/**
	 * Visszaadja az AIRobot fel�p�t�s�hez sz�ks�ges 
	 * receptet t�rol� Recipe objektumot
	 * @return
	 */
	// --------------------------------------------------
	public Recipe getAIRobotRecipe() {
		return recipes.get(0);
	}
	
	//???----------------------------------------------
	/**
	 * Visszaadja a teleportkapu-p�r fel�p�t�s�hez sz�ks�ges 
	 * receptet t�rol� Recipe objektumot.
	 * @return
	 */
	// -----------------------------------------------
	public Recipe getGatePairRecipe() {
		return recipes.get(1);
	}
	
	
	/**
	 * Visszaadja az �rb�zis fel�p�t�s�hez sz�ks�ges 
	 * receptet t�rol� Recipe objektumot.
	 * @return
	 */
	public Recipe getSpaceBaseRecipe() {
		return recipes.get(2);
	}
	
	
}
