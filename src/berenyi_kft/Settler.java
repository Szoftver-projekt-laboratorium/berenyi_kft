package berenyi_kft;

import java.util.ArrayList;

/**
 * A karakterek egyik fajtaja a telepes, mindegyiket egy-egy jatekos iranyitja
 * @author berenyi_kft
 */
public class Settler extends Character {
	
	/**
	 * Egy telepes altal egyidoben tarolhato nyersanyagegysegek
	 * maximalis szama
	 */
	private static final int capacity = 10;
	
	/**
	 * A telepes altal tarolt nyersanyagok listaja
	 * [0..10]
	 */
	private ArrayList<Resource> collectedResources = new ArrayList<Resource>();
	
	/**
	 * a telepes altal tarolt teleportkapuk kollekcioja
	 * [0..2]
	 */
	private ArrayList<TeleportingGate> gatesCreated = new ArrayList<TeleportingGate>();
	
	/**
	 * A jatekot reprezentalo osztaly
	 */
	private Game game;
	
	//---------------------------------
	
	/**
	 * Visszater a karakter altal tarolt nyersanyagok 
	 * listajaval, alapertelmezetten egy ures listaval
	 */
	@Override
	public ArrayList<Resource> getCollectedResources(){
		System.out.println("Settler's getCollectedResources() has been called");
		return this.collectedResources;
	}
	
	/**
	 * A telepes eltarolja a kibanyaszott r nyersanyagot a resources kollekciojaban.
	 * @param r Az elraktarozando nyersanyagegyseg
	 */
	public void accept(Resource r) {
		System.out.println("Settler's accept(r: Resource) has been called");
		collectedResources.add(r);
	}
	
	/**
	 * A telepes eltarolja a teleportkaput a gatesCreated kollekciojaban.
	 * @param tg A frissen elkészült teleportkapu
	 */
	public void accept(TeleportingGate tg) {
		System.out.println("Settler's accept(tg: TeleportingGate) has been called");
		gatesCreated.add(tg);
	}
	
	/**
	 * A telepes eltavolitja az r nyersanyagot a resources kollekciojabol.
	 * @param r Az eltavolitando nyersanyagegyseg
	 */
	public void remove(Resource r) {
		System.out.println("Settler's remove(r: Resource) has been called");
		for (Resource rCollected : collectedResources) {
			if (r.isCompatibleWith(rCollected)) {
				collectedResources.remove(rCollected);
				return;
			}
		}
	}
	
	/**
	 * A telepes kibanyassza az adott megfurt aszteroida magjaban talalhato 
	 * nyersanyagot. Ehhez meghivja az aszteroida minedBy(s: Settler) metodusat. 
	 * Ha a banyaszat kezdeten az urhajoban mar nincs hely ujabb nyersanyagnak, 
	 * akkor a telepes nem tud banyaszni, a fuggvenynek nincs hatasa.
	 */
	public void mine() {
		System.out.println("Settler's mine() has been called");
		if (collectedResources.size() < Settler.capacity) {
			place.minedBy(this);
		}
	}
	
	/**
	 * Meghivja a place aszteroida accept(Resource r) metodusat. 
	 * Ha az aszteroida ures, akkor az aszteroida eltarolja az r nyersanyagot,
	 * es eltavolitja azt a Settler nyersanyagai kozul. 
	 * Ha az aszteroida magjaban mar volt nyersanyag, akkor nem tortenik semmi.
	 * @param r Az aszteroidaba visszatoltendo nyersanyagegyseg
	 */
	public void restore(Resource r) {
		System.out.println("Settler's restore(r: Resource) has been called");
		place.accept(this, r);
	}
	
	/**
	 * A telepes lekerdezi a Game-tol az AI robot megepitesehez szukseges 
	 * receptet a Recipe getAIRobotRecipe() metodusanak meghivasaval, 
	 * majd osszehasonlitja a sajat nyersanyagait a receptben levokkel.
	 * 
	 * Amennyiben rendelkezik a szukseges nyersanyagokkal, akkor
	 * eltavolitja a receptben szereplo nyersanyagokat a raktarabol.
	 * Ezutan letrehoz egy AIRobot peldanyt, majd hozzaadja
	 * az aktualis aszteroidajahoz az accept(c: Character)
	 * metodus meghivasaval.
	 * 
	 * Vegul mindenkepp meghivja a Recipe reset() fuggvenyet, ezzel
	 * visszaallitva a recept eredeti tartalmat.
	 */
	public void createAIRobot() {
		System.out.println("Settler's createAIRobot() has been called.");
		Recipe aiRobotRecipe = game.getAIRobotRecipe();
		for (int i = collectedResources.size()-1; i >= 0; i--) {
			Resource r = collectedResources.get(i);
			if (aiRobotRecipe.isEmpty()) {
				break;
			}
			aiRobotRecipe.isNeeded(r);
		}
		
		if (aiRobotRecipe.isEmpty()) {
			aiRobotRecipe.reset();
			for (Resource r : aiRobotRecipe.getResources()) {
				this.remove(r);
			}
			
			AIRobot air = new AIRobot(game.getTimer());
			place.accept(air);
		}
		aiRobotRecipe.reset();
	}
	
	/**
	 * A telepes lekerdezi a Game-tol a teleportkapu-par megepitesehez 
	 * szukseges receptet a Recipe getGatePairRecipe() metodusanak meghivasaval, 
	 * majd osszehasonlitja a sajat nyersanyagait a receptben levokkel.
	 * 
	 * Amennyiben rendelkezik a szukseges nyersanyagokkal, es nincs nala
	 * elkeszitett teleportkapu, akkor eltavolitja a collectedResources
	 * kollekciojabol a receptben szereplo nyersanyagokat.
	 * Ezutan letrehozza a TeleportingGate ket peldanyat, es parba allitja oket
	 * a setPair(tg: TeleportingGate) fuggveny hivasaval, ezek utan pedig
	 * az urhajojan, a gatesCreated kollekcioban eltarolja oket.
	 *  
	 * Vegul mindenkepp meghivja a Recipe reset() fuggvenyet, 
	 * amelyben ezaltal visszaallitja a recept listajanak a tartalmat.
	 */
	public void createGatePair() {
		System.out.println("Settler's createGatePair() has been called");
		if (!gatesCreated.isEmpty()) {
			return;
		}
		
		Recipe gatePairRecipe = game.getGatePairRecipe();
		for (int i = collectedResources.size()-1; i >= 0; i--) {
			Resource r = collectedResources.get(i);
			if (gatePairRecipe.isEmpty()) {
				break;
			}
			gatePairRecipe.isNeeded(r);
		}
		
		if (gatePairRecipe.isEmpty()) {
			gatePairRecipe.reset();
			for (Resource r : gatePairRecipe.getResources()) {
				this.remove(r);
			}
			
			TeleportingGate tg1 = new TeleportingGate();
			TeleportingGate tg2 = new TeleportingGate();
			tg1.setPair(tg2);
			gatesCreated.add(tg1);
			gatesCreated.add(tg2);
		}
		gatePairRecipe.reset();
	}
	
	/**
	 * A telepes a gatesCreated kollekciobol kivalaszt egy teleportkaput, 
	 * majd az aktualis aszteroidaja koruli palyara allatja az
	 * Asteroid accept(TeleportingGate tg) fuggvenyevel.
	 * Ha mar a teleportkapu parja is palyara allt, bejegyzi
	 * a szomszedsagot a ket aszteroida kozott.
	 * Ha a telepesnel nincs elkeszult teleportkapu 
	 * (gatesCreated ures), akkor nem tortenik semmi.
	 */
	public void releaseGate() {
		System.out.println("Settler's releaseGate() has been called");
		if (gatesCreated.size() >= 1) {
			TeleportingGate tg = gatesCreated.remove(0);
			place.accept(tg);
			
			TeleportingGate tg2 = tg.getPair();
			Asteroid a2 = tg2.getAsteroid();
			if (a2 != null) {
				this.getPlace().accept(a2);
				a2.accept(this.getPlace());
			}
		} else {
			System.out.println("No TeleportingGate available. Cannot release a gate.");	
		}
	}
	
	/**
	 * A telepes eltavolitja a tg teleportkaput a gatesCreated kollekciojabol.
	 * @param tg Az eltavolitando teleportkapu
	 */
	public void remove(TeleportingGate tg) {
		System.out.println("Settler's remove(tg: TeleportingGate) has been called");
		gatesCreated.remove(tg);
	}
	
	/**
	 * A telepes meghal: megsemmisiti a nala levo teleportkapukat 
	 * azok die() fuggvenyeivel, eltavolitja magat az aszteroidajarol, 
	 * vegul meghivja a game removeSettler(Settler s) metodusat.
	 */
	@Override
	public void die() {
		System.out.println("Settler's die() has been called");
		super.die();
		gatesCreated.forEach((tg) -> {tg.die();});
		place.remove(this);
		game.removeSettler(this);
	}
	
	/**
	 * Beallitja a jatekot reprezentalo osztalyt a telepesnel.
	 * param game A jatekot reprezentalo osztaly
	 */
	public void setGame(Game game) {
		System.out.println("Settler's setGame() has been called");
		this.game = game;
	}
	
	/**
	 * Visszater a frissen elkeszult teleportkapuk listajaval.
	 * @return A telepesnel tarolt teleportkapuk listaja
	 */
	public ArrayList<TeleportingGate> getGatesCreated() {
		System.out.println("Settler's getGatesCreated() has been called");
		return gatesCreated;
	}
}
