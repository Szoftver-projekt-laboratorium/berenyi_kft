package berenyi_kft;

import java.util.ArrayList;

/**
 * A karakterek egyik fajtája a telepes
 * @author berenyi_kft
 *
 */
public class Settler extends Character{

	/**
	 * A telepes által tárolt nyersanyagok listája
	 * [0...10]
	 */
	ArrayList<Resource> collectedResources = new ArrayList<Resource>();
	
	/**
	 * a telepes által tárolt teleportkapuk kollekciója
	 * [0...2]
	 */
	ArrayList<TeleportingGate> gatesCreated = new ArrayList<TeleportingGate>();
	
	/**
	 * a játékot reprezentáló osztály
	 */
	Game game;
	
	//---------------------------------
	
	/**
	 * Visszatér a karakter által tárolt nyersanyagok 
	 * listájával, alapértelmezetten egy üres listával
	 * @return
	 */
	@Override
	public ArrayList<Resource> getCollectedResources(){
		return this.collectedResources;
	}
	
	/**
	 * A telepes eltárolja a kibányászott r nyersanyagot a resources kollekciójában.
	 * @param r
	 */
	public void accept(Resource r) {
		
	}
	
	/**
	 * A telepes eltávolítja az r nyersanyagot a resources kollekciójából.
	 * @param r
	 */
	public void remove(Resource r) {
		
	}
	
	/**
	 * A telepes kibányássza az adott megfúrt aszteroida magjában található 
	 * nyersanyagot. Ehhez meghívja az aszteroida minedBy() metódusát. 
	 * Ha a bányászat kezdetén az ûrhajóban már nincs hely újabb nyersanyagnak, 
	 * akkor a telepes nem tud bányászni, a függvénynek nincs hatása.
	 */
	public void mine() {
		
	}
	
	/**
	 * Meghívja a place aszteroida Accept(Resource r) metódusát. 
	 * Ha az aszteroida üres, akkor az aszteroida eltárolja az r nyersanyagot,
	 * és eltávolítja azt a Settler nyersanyagai közül. 
	 * Ha az aszteroida magjában már volt nyersanyag, akkor nem történik semmi.
	 * @param r
	 */
	public void restore(Resource r) {
		
	}
	
	/**
	 * A telepes lekérdezi a Game-tõl az AI robot megépítéséhez szükséges 
	 * receptet a Recipe getAIRobotRecipe() metódusának meghívásával, 
	 * majd pedig összehasonlítja a saját nyersanyagait a receptben lévõkkel. 
	 * Amennyiben rendelkezik a szükséges nyersanyagokkal, létrehoz egy AIRobot példányt, 
	 * ezután pedig hozzáadja az aktuális aszteroidához az accept(c: Character)
	 *  metódus meghívásával. Végül meghívja a Recipe reset() függvényét, 
	 * amelyben ezáltal visszaállítja a recept listájának a tartalmát.
	 */
	public void createAIRobot() {
		
	}
	
	/**
	 * A telepes lekérdezi a Game-tõl a teleportkapu-pár megépítéséhez 
	 * szükséges receptet a Recipe getGatePairRecipe() metódusának meghívásával, 
	 * majd pedig összehasonlítja a saját nyersanyagait a receptben lévõkkel. 
	 * Amennyiben rendelkezik a szükséges nyersanyagokkal és nincs nála elkészített 
	 * teleportkapu, akkor létrehozza a TeleportingGate két példányát és párba állítja
	 *  õket a setPair(tg: TeleportingGate) függvények meghívásával, 
	 *  majd az ûrhajóján a gatesCreated kollekcióban eltárolja õket. 
	 *  Végül meghívja a Recipe reset() függvényét, 
	 * amelyben ezáltal visszaállítja a recept listájának a tartalmát.
	 */
	public void createGatePair() {
		
	}
	
	/**
	 * A telepes a gatesCreated kollekcióból kiválaszt egy teleportkaput, 
	 * majd az aktuális aszteroidája körüli pályára állítja az
	 *  Asteroid accept(TeleportingGate tg) függvényével. 
	 *  Ha a telepesnél nincs elkészült teleportkapu 
	 * (gatesCreated üres), akkor nem történik semmi.
	 */
	public void releaseGate() {
		
	}
	
	/**
	 * A telepes eltávolítja a tg teleportkaput a gatesCreated kollekciójából.
	 * @param tg
	 */
	public void remove(TeleportingGate tg) {
		
	}
	
	/**
	 * A telepes meghal: megsemmisíti a nála levõ teleportkapukat 
	 * azok die() függvényeivel, eltávolítja magát az aszteroidájáról, 
	 * végül meghívja a game removeSettler(Settler s) metódusát.
	 */
	public void die() {
		
	}
}
