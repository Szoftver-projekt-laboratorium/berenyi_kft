package berenyi_kft;

import java.util.ArrayList;
import java.util.Scanner;

import berenyi_kft_GUI.AIRobotGraphics; // TODO A gamePanelre kell bizni a letrehozasukat!
import berenyi_kft_GUI.TeleportingGateGraphics;

/**
 * A karakterek egyik fajtaja a telepes, mindegyiket egy-egy jatekos iranyitja
 * 
 * @author berenyi_kft
 */
public class Settler extends Character {

	/**
	 * Egy telepes altal egyidoben tarolhato nyersanyagegysegek maximalis szama
	 */
	private static final int capacity = 10;

	/**
	 * A telepes altal tarolt nyersanyagok listaja [0..10]
	 */
	private ArrayList<Resource> collectedResources = new ArrayList<Resource>();

	/**
	 * a telepes altal tarolt teleportkapuk kollekcioja [0..2]
	 */
	private ArrayList<TeleportingGate> gatesCreated = new ArrayList<TeleportingGate>();

	/**
	 * A jatekot reprezentalo osztaly
	 */
	private Game game = null;

	// ---------------------------------

	public String getDescription() {

		String str = "";

		String id = Proto.getId(this);
		str += "Settler " + id + "\n";

		String gameId = Proto.getId(game);
		str += "\tgame " + gameId + "\n";

		String timerId = Proto.getId(timer);
		str += "\ttimer " + timerId + "\n";

		String placeId = Proto.getId(place);
		str += "\tplace " + placeId + "\n";

		if (!collectedResources.isEmpty()) {
			str += "\tresources";
			for (Resource r : collectedResources) {
				String resourceId = Proto.getId(r);
				if (resourceId.equals("null")) {
					str += " " + resourceId;
					break;
				}
				str += " " + resourceId;
			}
			str += "\n";
		} else
			str += "\tresources null\n";

		if (!gatesCreated.isEmpty()) {
			str += "\tgatesCreated";
			for (TeleportingGate tg : gatesCreated) {
				String gateId = Proto.getId(tg);
				str += " " + gateId;
			}
			str += "\n";
		} else
			str += "\tgatesCreated null\n";

		return str;
	}

	/*
	 * Minta: Settler game game timer timer place a1 resources ir2 ir3 ic1 ur2
	 * gatesCreated tg1 tg2
	 */
	public void load(Scanner sc) {
		String line = sc.nextLine();
		while (!line.equals("") & sc.hasNextLine()) {
			line = sc.nextLine();
			line = line.stripLeading();
			String[] tokens = line.split("\\s+");

			switch (tokens[0]) {
			case "game":
				game = (Game) Proto.getObject(tokens[1]);
				break;

			case "timer":
				timer = (Timer) Proto.getObject(tokens[1]);
				break;

			case "place":
				place = (Asteroid) Proto.getObject(tokens[1]);
				break;

			case "resources":
				for (int i = 1; i < tokens.length; i++) {
					Resource r = (Resource) Proto.getObject(tokens[i]);
					if (r != null)
						collectedResources.add(r);
				}
				break;

			case "gatesCreated":
				for (int i = 1; i < tokens.length; i++) {
					TeleportingGate tg = (TeleportingGate) Proto.getObject(tokens[i]);
					if (tg != null)
						gatesCreated.add(tg);
				}
				break;

			default:
				break;
			}
		}
	}

	/**
	 * Visszater a karakter altal tarolt nyersanyagok listajaval, alapertelmezetten
	 * egy ures listaval.
	 */
	@Override
	public ArrayList<Resource> getCollectedResources() {
		Proto.println(Proto.getId(this) + ".getCollectedResources()");
		return this.collectedResources;
	}

	/**
	 * A telepes eltarolja a kibanyaszott r nyersanyagot a resources kollekciojaban.
	 * 
	 * @param r Az elraktarozando nyersanyagegyseg.
	 */
	public void accept(Resource r) {
		Proto.println(Proto.getId(this) + ".accept(" + Proto.getId(r) + ")");
		collectedResources.add(r);
	}

	/**
	 * A telepes eltarolja a teleportkaput a gatesCreated kollekciojaban.
	 * 
	 * @param tg A frissen elkészült teleportkapu.
	 */
	public void accept(TeleportingGate tg) {
		Proto.println(Proto.getId(this) + ".accept(" + Proto.getId(tg) + ")");
		Proto.incrTabs();
		gatesCreated.add(tg);
		tg.setSettler(this);
		Proto.decrTabs();
	}

	/**
	 * A telepes eltavolitja az r nyersanyagot a resources kollekciojabol.
	 * 
	 * @param r Az eltavolitando nyersanyagegyseg.
	 */
	public Resource remove(Resource r) {
		Proto.println(Proto.getId(this) + ".remove(" + Proto.getId(r) + ")");
		for (Resource rCollected : collectedResources) {
			if (r.isCompatibleWith(rCollected)) {
				collectedResources.remove(rCollected);
				return rCollected;
			}
		}
		return null;
	}

	/**
	 * A telepes furja az aszteroidat, ehhez meghivja az aszteroidaja drilled()
	 * metodusat.
	 */
	public void drill() {
		Proto.println(Proto.getId(this) + ".drill()");
		Proto.incrTabs();
		place.drilled();
		Proto.decrTabs();
	}

	/**
	 * A telepes kibanyassza az adott megfurt aszteroida magjaban talalhato
	 * nyersanyagot. Ehhez meghivja az aszteroida minedBy(s: Settler) metodusat. Ha
	 * a banyaszat kezdeten az urhajoban mar nincs hely ujabb nyersanyagnak, akkor a
	 * telepes nem tud banyaszni, a fuggvenynek nincs hatasa.
	 */
	public void mine() {
		Proto.println(Proto.getId(this) + ".mine()");
		Proto.incrTabs();
		if (collectedResources.size() < Settler.capacity) {
			place.minedBy(this);
		}
		Proto.decrTabs();
	}

	/**
	 * Meghivja a place aszteroida accept(Resource r) metodusat. Ha az aszteroida
	 * ures, akkor az aszteroida eltarolja az r nyersanyagot, es eltavolitja azt a
	 * Settler nyersanyagai kozul. Ha az aszteroida magjaban mar volt nyersanyag,
	 * akkor nem tortenik semmi.
	 * 
	 * @param r Az aszteroidaba visszatoltendo nyersanyagegyseg
	 */
	public void restore(Resource r) {
		Proto.println(Proto.getId(this) + ".restore(" + Proto.getId(r) + ")");
		Proto.incrTabs();
		place.accept(this, r);
		Proto.decrTabs();
	}

	/**
	 * A telepes lekerdezi a Game-tol az AI robot megepitesehez szukseges receptet a
	 * Recipe getAIRobotRecipe() metodusanak meghivasaval, majd osszehasonlitja a
	 * sajat nyersanyagait a receptben levokkel.
	 * 
	 * Amennyiben rendelkezik a szukseges nyersanyagokkal, akkor eltavolitja a
	 * receptben szereplo nyersanyagokat a raktarabol. Ezutan letrehoz egy AIRobot
	 * peldanyt, majd hozzaadja az aktualis aszteroidajahoz az accept(c: Character)
	 * metodus meghivasaval.
	 * 
	 * Vegul mindenkepp meghivja a Recipe reset() fuggvenyet, ezzel visszaallitva a
	 * recept eredeti tartalmat.
	 */
	public void createAIRobot() {
		Proto.println(Proto.getId(this) + ".createAIRobot()");
		Proto.incrTabs();
		Recipe aiRobotRecipe = game.getAIRobotRecipe();
		for (int i = collectedResources.size() - 1; i >= 0; i--) {
			Resource r = collectedResources.get(i);
			if (aiRobotRecipe.isEmpty()) {
				break;
			}
			aiRobotRecipe.isNeeded(r);
		}

		if (aiRobotRecipe.isEmpty()) {
			aiRobotRecipe.reset();
			for (Resource r : aiRobotRecipe.getResources()) {
				Resource resource = this.remove(r);
				resource.removeFromGame();
			}
			
			// Ujitott jatekhoz adas
			AIRobot air = new AIRobot();
			Proto.getAllObjects().addAIRobot(air);
			
			// TODO Meg lehet kerulni, hogy a Settler peldanyositsa a robot kepet?
			AIRobotGraphics airg = new AIRobotGraphics(air);
			game.getGamePanel().addToMapPanel(airg);
			game.getGamePanel().addDrawable(airg);
			
			Timer timer = game.getTimer();
			air.setTimer(timer);
			if (timer != null)
				timer.addSteppable(air);
			
			place.accept(air);
			air.setPlace(place);
		}
		aiRobotRecipe.reset();
		Proto.decrTabs();
	}

	/**
	 * A telepes lekerdezi a Game-tol a teleportkapu-par megepitesehez szukseges
	 * receptet a Recipe getGatePairRecipe() metodusanak meghivasaval, majd
	 * osszehasonlitja a sajat nyersanyagait a receptben levokkel.
	 * 
	 * Amennyiben rendelkezik a szukseges nyersanyagokkal, es nincs nala elkeszitett
	 * teleportkapu, akkor eltavolitja a collectedResources kollekciojabol a
	 * receptben szereplo nyersanyagokat. Ezutan letrehozza a TeleportingGate ket
	 * peldanyat, es parba allitja oket a setPair(tg: TeleportingGate) fuggveny
	 * hivasaval, ezek utan pedig az urhajojan, a gatesCreated kollekcioban
	 * eltarolja oket.
	 * 
	 * Vegul mindenkepp meghivja a Recipe reset() fuggvenyet, amelyben ezaltal
	 * visszaallitja a recept listajanak a tartalmat.
	 */
	public void createGatePair() {
		Proto.println(Proto.getId(this) + ".createGatePair()");
		Proto.incrTabs();
		if (gatesCreated.size() > 1) {
			Proto.decrTabs();
			return;
		}

		Recipe gatePairRecipe = game.getGatePairRecipe();
		for (int i = collectedResources.size() - 1; i >= 0; i--) {
			Resource r = collectedResources.get(i);
			if (gatePairRecipe.isEmpty()) {
				break;
			}
			gatePairRecipe.isNeeded(r);
		}

		if (gatePairRecipe.isEmpty()) {
			gatePairRecipe.reset();
			for (Resource r : gatePairRecipe.getResources()) {
				Resource resource = this.remove(r);
				resource.removeFromGame();
			}
			
			// Ujitott jatekhoz adas
			TeleportingGate tg1 = new TeleportingGate();
			Proto.getAllObjects().addTeleportingGate(tg1);
			// TODO Meg lehet kerulni, hogy a Settler peldanyositsa a kapu kepet?
			TeleportingGateGraphics tgg1 = new TeleportingGateGraphics(tg1);
			game.getGamePanel().addToMapPanel(tgg1);
			game.getGamePanel().addDrawable(tgg1);
			
			TeleportingGate tg2 = new TeleportingGate();
			Proto.getAllObjects().addTeleportingGate(tg2);
			TeleportingGateGraphics tgg2 = new TeleportingGateGraphics(tg2);
			game.getGamePanel().addToMapPanel(tgg2);
			game.getGamePanel().addDrawable(tgg2);
			
			Timer timer = game.getTimer();
			tg1.setTimer(timer);
			tg2.setTimer(timer);
			
			tg1.setPair(tg2);
			this.accept(tg1);
			this.accept(tg2);
		}
		gatePairRecipe.reset();
		Proto.decrTabs();
	}

	/**
	 * A telepes a gatesCreated kollekciobol kivalaszt egy teleportkaput, majd az
	 * aktualis aszteroidaja koruli palyara allatja az Asteroid
	 * accept(TeleportingGate tg) fuggvenyevel. Ha mar a teleportkapu parja is
	 * palyara allt, bejegyzi a szomszedsagot a ket aszteroida kozott. Ha a
	 * telepesnel nincs elkeszult teleportkapu (gatesCreated ures), akkor nem
	 * tortenik semmi.
	 */
	public void releaseGate(/*TeleportingGate tg*/) {
		Proto.println(Proto.getId(this) + ".releaseGate("
				+ /* Proto.getId(tg) +*/ ")");
		Proto.incrTabs();
		if (gatesCreated.size() >= 1) {
			TeleportingGate tg = gatesCreated.get(0);
			place.accept(tg);
			tg.setSettler(null);
			gatesCreated.remove(0);
		} else {
			Proto.println("There is no TeleportingGate available on you at the moment.");
		}
		Proto.decrTabs();
	}

	/**
	 * A telepes eltavolitja a tg teleportkaput a gatesCreated kollekciojabol.
	 * 
	 * @param tg Az eltavolitando teleportkapu
	 */
	public void remove(TeleportingGate tg) {
		Proto.println(Proto.getId(this) + ".remove(" + Proto.getId(tg) + ")");
		gatesCreated.remove(tg);
	}

	/**
	 * A telepes meghal: megsemmisiti a nala levo teleportkapukat azok die()
	 * fuggvenyeivel, eltavolitja magat az aszteroidajarol, vegul meghivja a game
	 * removeSettler(Settler s) metodusat.
	 */
	@Override
	public void die() {
		Proto.println(Proto.getId(this) + ".die()");
		game.getGamePanel().writeToMessageBoard("Settler " + Proto.getId(this) + " died!");
		Proto.incrTabs();
		super.die();
		for (Resource r : collectedResources) {
			r.removeFromGame();
		}

		// gatesCreated.forEach((tg) -> {tg.die();});
		for (int i = gatesCreated.size() - 1; i >= 0; i--) {
			gatesCreated.get(i).die();
		}

		// A Game ertesiti a Controllert es a Playert is errol.
		game.removeSettler(this);
		// Proto.getAllObjects().removeSettler(this);
		Proto.decrTabs();
	}

	/**
	 * Beallitja a jatekot reprezentalo osztalyt a telepesnel. param game A jatekot
	 * reprezentalo osztaly
	 */
	public void setGame(Game game) {
		// Proto.println(Proto.getId(this) + "setGame(" + Proto.getId(game) + ")");
		this.game = game;
	}

	/**
	 * Visszater a frissen elkeszult teleportkapuk listajaval.
	 * 
	 * @return A telepesnel tarolt teleportkapuk listaja
	 */
	public ArrayList<TeleportingGate> getGatesCreated() {
		// Proto.println(Proto.getId(this) + "getGatesCreated()");
		return gatesCreated;
	}

	/**
	 * Settlerkent lep az <code>a</code> aszteroidara, (ezzel az urbazis
	 * megepithetosege ellenorzesre fog kerulni az aszteroidan).
	 * 
	 * @return a A telepest fogado aszteroida
	 */
	@Override
	public void acceptedBy(Asteroid a) {
		Proto.println(Proto.getId(this) + ".acceptedBy("
				+ Proto.getId(a) + ")");
		a.accept(this);
	}

	/**
	 * A settler az eppen aktuálisan soronkovetkezo-e
	 * 
	 * @return true/false
	 */
	public boolean isActualSettler() {
		if(Proto.getId(this).equals( Proto.getId(this.game.getController().getActPlayer().getSettler())))
			return true;
		return false;
	}
	
	public Integer[] getNumbOfResources() {
		Uranium uranium=new Uranium();
		Coal coal=new Coal();
		Iron iron=new Iron();
		Ice ice=new Ice();
	
		Integer[] numbOfResources=new Integer[4];
		numbOfResources[0]=0;
		numbOfResources[1]=0;
		numbOfResources[2]=0;
		numbOfResources[3]=0;
		
		for(Resource r : collectedResources) {
			if(r.isCompatibleWith(coal))
				numbOfResources[0]++;
			if(r.isCompatibleWith(iron))
				numbOfResources[1]++;
			if(r.isCompatibleWith(uranium))
				numbOfResources[2]++;
			if(r.isCompatibleWith(ice))
				numbOfResources[3]++;
		}
		
		return numbOfResources;
	}
	
}
