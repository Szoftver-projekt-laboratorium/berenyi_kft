package berenyi_kft;

import java.util.ArrayList;

/**
 * A karakterek egyik fajt�ja a telepes
 * @author berenyi_kft
 *
 */
public class Settler extends Character{

	/**
	 * A telepes �ltal t�rolt nyersanyagok list�ja
	 * [0...10]
	 */
	ArrayList<Resource> collectedResources = new ArrayList<Resource>();
	
	/**
	 * a telepes �ltal t�rolt teleportkapuk kollekci�ja
	 * [0...2]
	 */
	ArrayList<TeleportingGate> gatesCreated = new ArrayList<TeleportingGate>();
	
	/**
	 * a j�t�kot reprezent�l� oszt�ly
	 */
	Game game;
	
	//---------------------------------
	
	/**
	 * Visszat�r a karakter �ltal t�rolt nyersanyagok 
	 * list�j�val, alap�rtelmezetten egy �res list�val
	 * @return
	 */
	@Override
	public ArrayList<Resource> getCollectedResources(){
		return this.collectedResources;
	}
	
	/**
	 * A telepes elt�rolja a kib�ny�szott r nyersanyagot a resources kollekci�j�ban.
	 * @param r
	 */
	public void accept(Resource r) {
		
	}
	
	/**
	 * A telepes elt�vol�tja az r nyersanyagot a resources kollekci�j�b�l.
	 * @param r
	 */
	public void remove(Resource r) {
		
	}
	
	/**
	 * A telepes kib�ny�ssza az adott megf�rt aszteroida magj�ban tal�lhat� 
	 * nyersanyagot. Ehhez megh�vja az aszteroida minedBy() met�dus�t. 
	 * Ha a b�ny�szat kezdet�n az �rhaj�ban m�r nincs hely �jabb nyersanyagnak, 
	 * akkor a telepes nem tud b�ny�szni, a f�ggv�nynek nincs hat�sa.
	 */
	public void mine() {
		
	}
	
	/**
	 * Megh�vja a place aszteroida Accept(Resource r) met�dus�t. 
	 * Ha az aszteroida �res, akkor az aszteroida elt�rolja az r nyersanyagot,
	 * �s elt�vol�tja azt a Settler nyersanyagai k�z�l. 
	 * Ha az aszteroida magj�ban m�r volt nyersanyag, akkor nem t�rt�nik semmi.
	 * @param r
	 */
	public void restore(Resource r) {
		
	}
	
	/**
	 * A telepes lek�rdezi a Game-t�l az AI robot meg�p�t�s�hez sz�ks�ges 
	 * receptet a Recipe getAIRobotRecipe() met�dus�nak megh�v�s�val, 
	 * majd pedig �sszehasonl�tja a saj�t nyersanyagait a receptben l�v�kkel. 
	 * Amennyiben rendelkezik a sz�ks�ges nyersanyagokkal, l�trehoz egy AIRobot p�ld�nyt, 
	 * ezut�n pedig hozz�adja az aktu�lis aszteroid�hoz az accept(c: Character)
	 *  met�dus megh�v�s�val. V�g�l megh�vja a Recipe reset() f�ggv�ny�t, 
	 * amelyben ez�ltal vissza�ll�tja a recept list�j�nak a tartalm�t.
	 */
	public void createAIRobot() {
		
	}
	
	/**
	 * A telepes lek�rdezi a Game-t�l a teleportkapu-p�r meg�p�t�s�hez 
	 * sz�ks�ges receptet a Recipe getGatePairRecipe() met�dus�nak megh�v�s�val, 
	 * majd pedig �sszehasonl�tja a saj�t nyersanyagait a receptben l�v�kkel. 
	 * Amennyiben rendelkezik a sz�ks�ges nyersanyagokkal �s nincs n�la elk�sz�tett 
	 * teleportkapu, akkor l�trehozza a TeleportingGate k�t p�ld�ny�t �s p�rba �ll�tja
	 *  �ket a setPair(tg: TeleportingGate) f�ggv�nyek megh�v�s�val, 
	 *  majd az �rhaj�j�n a gatesCreated kollekci�ban elt�rolja �ket. 
	 *  V�g�l megh�vja a Recipe reset() f�ggv�ny�t, 
	 * amelyben ez�ltal vissza�ll�tja a recept list�j�nak a tartalm�t.
	 */
	public void createGatePair() {
		
	}
	
	/**
	 * A telepes a gatesCreated kollekci�b�l kiv�laszt egy teleportkaput, 
	 * majd az aktu�lis aszteroid�ja k�r�li p�ly�ra �ll�tja az
	 *  Asteroid accept(TeleportingGate tg) f�ggv�ny�vel. 
	 *  Ha a telepesn�l nincs elk�sz�lt teleportkapu 
	 * (gatesCreated �res), akkor nem t�rt�nik semmi.
	 */
	public void releaseGate() {
		
	}
	
	/**
	 * A telepes elt�vol�tja a tg teleportkaput a gatesCreated kollekci�j�b�l.
	 * @param tg
	 */
	public void remove(TeleportingGate tg) {
		
	}
	
	/**
	 * A telepes meghal: megsemmis�ti a n�la lev� teleportkapukat 
	 * azok die() f�ggv�nyeivel, elt�vol�tja mag�t az aszteroid�j�r�l, 
	 * v�g�l megh�vja a game removeSettler(Settler s) met�dus�t.
	 */
	public void die() {
		
	}
}
