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
	 */
	@Override
	public ArrayList<Resource> getCollectedResources(){
		System.out.println("Settler's getCollectedResources() has been called");
		return this.collectedResources;
	}
	
	/**
	 * A telepes elt�rolja a kib�ny�szott r nyersanyagot a resources kollekci�j�ban.
	 * @param r
	 */
	public void accept(Resource r) {
		System.out.println("Settler's accept(r: Resource) has been called");
		collectedResources.add(r);
	}
	
	/**
	 * A telepes elt�rolja a teleportkaput a gatesCreated kollekci�j�ban.
	 * @param tg A frissen elkészült teleportkapu
	 */
	public void accept(TeleportingGate tg) {
		System.out.println("Settler's accept(tg: TeleportingGate) has been called");
		gatesCreated.add(tg);
	}
	
	/**
	 * A telepes elt�vol�tja az r nyersanyagot a resources kollekci�j�b�l.
	 * @param r
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
	 * A telepes kib�ny�ssza az adott megf�rt aszteroida magj�ban tal�lhat� 
	 * nyersanyagot. Ehhez megh�vja az aszteroida minedBySettler(s: Settler) met�dus�t. 
	 * Ha a b�ny�szat kezdet�n az �rhaj�ban m�r nincs hely �jabb nyersanyagnak, 
	 * akkor a telepes nem tud b�ny�szni, a f�ggv�nynek nincs hat�sa.
	 */
	public void mine() {
		System.out.println("Settler's mine() has been called");
		if (collectedResources.size() < 10) {
			place.minedBy(this);
		}
	}
	
	
	/**
	 * Megh�vja a place aszteroida accept(Resource r) met�dus�t. 
	 * Ha az aszteroida �res, akkor az aszteroida elt�rolja az r nyersanyagot,
	 * �s elt�vol�tja azt a Settler nyersanyagai k�z�l. 
	 * Ha az aszteroida magj�ban m�r volt nyersanyag, akkor nem t�rt�nik semmi.
	 * @param r
	 */
	public void restore(Resource r) {
		System.out.println("Settler's restore(r: Resource) has been called");
		place.accept(this,r);
	}
	
	/**
	 * A telepes lek�rdezi a Game-t�l az AI robot meg�p�t�s�hez sz�ks�ges 
	 * receptet a Recipe getAIRobotRecipe() met�dus�nak megh�v�s�val, 
	 * majd pedig �sszehasonl�tja a saj�t nyersanyagait a receptben l�v�kkel. 
	 * Amennyiben rendelkezik a sz�ks�ges nyersanyagokkal, l�trehoz egy AIRobot p�ld�nyt, 
	 * ezut�n pedig hozz�adja az aktu�lis aszteroid�hoz az accept(c: Character)
	 * met�dus megh�v�s�val. V�g�l megh�vja a Recipe reset() f�ggv�ny�t, 
	 * amelyben ez�ltal vissza�ll�tja a recept list�j�nak a tartalm�t.
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
	 * A telepes lek�rdezi a Game-t�l a teleportkapu-p�r meg�p�t�s�hez 
	 * sz�ks�ges receptet a Recipe getGatePairRecipe() met�dus�nak megh�v�s�val, 
	 * majd pedig �sszehasonl�tja a saj�t nyersanyagait a receptben l�v�kkel. 
	 * Amennyiben rendelkezik a sz�ks�ges nyersanyagokkal �s nincs n�la elk�sz�tett 
	 * teleportkapu, akkor l�trehozza a TeleportingGate k�t p�ld�ny�t �s p�rba �ll�tja
	 * *�ket a setPair(tg: TeleportingGate) f�ggv�nyek megh�v�s�val, 
	 * majd az �rhaj�j�n a gatesCreated kollekci�ban elt�rolja �ket. 
	 *  V�g�l megh�vja a Recipe reset() f�ggv�ny�t, 
	 * amelyben ez�ltal vissza�ll�tja a recept list�j�nak a tartalm�t.
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
	 * A telepes a gatesCreated kollekci�b�l kiv�laszt egy teleportkaput, 
	 * majd az aktu�lis aszteroid�ja k�r�li p�ly�ra �ll�tja az
	 * Asteroid accept(TeleportingGate tg) f�ggv�ny�vel. 
	 * Ha a telepesn�l nincs elk�sz�lt teleportkapu 
	 * (gatesCreated �res), akkor nem t�rt�nik semmi.
	 */
	public void releaseGate() {
		System.out.println("Settler's releaseGate() has been called");
		if (gatesCreated.size() >= 1) {
		TeleportingGate tg = gatesCreated.remove(0);
		place.accept(tg);
		TeleportingGate tg2=tg.getPair();
		Asteroid a2 =tg2.getAsteroid();
		if(a2!=null) {
		this.getPlace().accept(a2);
		a2.accept(this.getPlace());
		}
		}else {System.out.println("No TeleportingGate available. Can not release a gate. ");}
	}
	
	/**
	 * A telepes elt�vol�tja a tg teleportkaput a gatesCreated kollekci�j�b�l.
	 * @param tg
	 */
	public void remove(TeleportingGate tg) {
		System.out.println("Settler's remove(tg: TeleportingGate) has been called");
		gatesCreated.remove(tg);
	}
	
	/**
	 * A telepes meghal: megsemmis�ti a n�la lev� teleportkapukat 
	 * azok die() f�ggv�nyeivel, elt�vol�tja mag�t az aszteroid�j�r�l, 
	 * v�g�l megh�vja a game removeSettler(Settler s) met�dus�t.
	 */
	public void die() {
		System.out.println("Settler's die() has been called");
		gatesCreated.forEach((tg) -> {tg.die();});
		place.remove(this);
		game.removeSettler(this);
	}
	
	public Asteroid getPlace() {
			return place;
	}
	
	public void setPlace(Asteroid a) {
		place=a;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public ArrayList<TeleportingGate> getGatesCreated() {
		System.out.println("Settler's getGatesCreated() has been called");
		return gatesCreated;
	}
}
