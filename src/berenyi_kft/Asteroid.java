package berenyi_kft;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aszteroida osztaly: nyersanyagot tartalmazhat, illetve karakterek
 * tartozkodhatnak rajta
 * 
 * @author berenyi_kft
 */
public class Asteroid {
	
	/**
	 * Az aszteroidak maximalis kopenyvastagsaga
	 */
	private static final int maxThickness = 5;	// Uj statikus mezo
	
	/**
	 * Az aszteroida kopenyvastagsaga, vagyis a magot borito sziklaretegek szama
	 */
	private int rockLayerThickness;

	/**
	 * Referencia a jatekot reprezentalo osztalyra
	 */
	private Game game = null;

	/**
	 * Az aszteroida magjaban talalhato egysegnyi nyersanyag.
	 * Ha a mag ureges, akkor erteke null
	 */
	private Resource resource = null;

	/**
	 * Az aszteroidaovben levo Nap
	 */
	private Sun sun = null;

	/**
	 * Az aszteroidaval szomszedos aszteroidak listaja. A teleportkapuk altal
	 * szomszedossa valt aszteroidakat is magaba foglalja
	 */
	private ArrayList<Asteroid> neighbors = new ArrayList<Asteroid>();

	/**
	 * Az aszteroidan tartozkodo karakterek (telepesek, robotok, stb.) kollekcioja
	 */
	private ArrayList<Character> characters = new ArrayList<Character>();

	/**
	 * Az aszteroida korul kozvetlenul keringo teleportkapuk kollekcioja
	 */
	private ArrayList<TeleportingGate> gates = new ArrayList<TeleportingGate>();
	
	/**
	 * Megmondja, hogy az aszteroida jatekban van-e meg
	 */
	private boolean dead = false;
	
	/**
	 * Jeloli, hogy az aszteroida kiemelt allapotban van-e
	 */
	private boolean emphasized = false;

	//---------------------------------------------------------------------------

	/**
	 * Beallitja az aszteroida attributumait az sc Scanner aktualis poziciojatol.
	 * 
	 * @param sc A beolvasast vegzo Scanner
	 */
	public void load(Scanner sc) {
		String line = sc.nextLine();
		while (!line.equals("") & sc.hasNextLine()) {
			line = sc.nextLine();
			line = line.stripLeading();
			String[] tokens = line.split("\\s+");

			switch (tokens[0]) {
			case "rockLayerThickness":
				rockLayerThickness = Integer.parseInt(tokens[1]);
				break;

			case "game":
				game = (Game) Proto.getObject(tokens[1]);
				break;

			case "sun":
				sun = (Sun) Proto.getObject(tokens[1]);
				break;

			case "neighbors":
				for (int i = 1; i < tokens.length; i++) {
					Asteroid a = (Asteroid) Proto.getObject(tokens[i]);
					if (a != null)
						neighbors.add(a);
				}
				break;

			case "resource":
				resource = (Resource) Proto.getObject(tokens[1]);
				break;

			case "characters":
				for (int i = 1; i < tokens.length; i++) {
					Character c = (Character) Proto.getObject(tokens[i]);
					if (c != null)
						characters.add(c);
				}
				break;

			case "gates":
				for (int i = 1; i < tokens.length; i++) {
					TeleportingGate tg = (TeleportingGate) Proto.getObject(tokens[i]);
					if (tg != null)
						gates.add(tg);
				}
				break;

			default:
				break;
			}
		}
	}

	public String getDescription() {

		String str = "";

		String id = Proto.getId(this);
		str += "Asteroid " + id + "\n";

		String thicknessStr = Integer.toString(rockLayerThickness);
		str += "\trockLayerThickness " + thicknessStr + "\n";

		String gameId = Proto.getId(game);
		str += "\tgame " + gameId + "\n";

		String sunId = Proto.getId(sun);
		str += "\tsun " + sunId + "\n";

		if (!neighbors.isEmpty()) {
			str += "\tneighbors";
			for (Asteroid a : neighbors) {
				String neighborId = Proto.getId(a);
				str += " " + neighborId;
			}
			str += "\n";
		} else
			str += "\tneighbors null\n";

		String resourceId = Proto.getId(resource);
		str += "\tresource " + resourceId + "\n";

		if (!characters.isEmpty()) {
			str += "\tcharacters";
			for (Character c : characters) {
				String characterId = Proto.getId(c);
				str += " " + characterId;
			}
			str += "\n";
		} else
			str += "\tcharacters null\n";

		if (!gates.isEmpty()) {
			str += "\tgates";
			for (TeleportingGate tg : gates) {
				String teleportingGateId = Proto.getId(tg);
				str += " " + teleportingGateId;
			}
			str += "\n";
		} else
			str += "\tgates null\n";

		return str;
	}
	
	/**
	 * Megadja az aszteroida kopenyvastagsagat.
	 * 
	 * @return A kopenyt alkoto sziklaretegek szama
	 */
	public int getRockLayerThickness() {
		Proto.println(Proto.getId(this) + ".getRockLayerThickness()" + " = " + rockLayerThickness);
		return rockLayerThickness;
	}

	/**
	 * Beallitja az aszteroida kopenyvastagsagat a parameterertekre.
	 * 
	 * @param value A beallitando sziklareteg-vastagsag
	 */
	public void setRockLayerThickness(int thickness) {
		Proto.println(Proto.getId(this) + ".setRockLayerThickness(" + thickness + ")");
		if (thickness > Asteroid.maxThickness)
			thickness = Asteroid.maxThickness;
		else if (thickness < 0)
			thickness = 0;
		rockLayerThickness = thickness;
	}
	
	/**
	 * Beallitja a magban talalhato nyersanyagot (az inicializalashoz hasznalando).
	 * 
	 * @param r A magba beallitando nyersanyag. Ha null, akkor az
	 * 			aszteroida uregesse valik.
	 */
	public void addResource(Resource r) {
		// System.out.println("Asteroid's addResource(r: Resource) has been called");
		resource = r;
	}

	/**
	 * Beallitja a jatek osztalyt.
	 * 
	 * @param g A jatekot reprezentalo osztaly
	 */
	public void setGame(Game g) {
		game = g;
	}

	/**
	 * Beallitja a jatekban levo Napot.
	 * 
	 * @param s A beallitando Nap
	 */
	public void setSun(Sun s) {
		sun = s;
	}

	public Sun getSun() {
		return sun;
	}

	/**
	 * Visszater az aszteroidaval kozvetlenul szomszedos aszteroidak listajaval. A
	 * lista nem tartalmazza a teleportkapu-parok altali szomszedokat.
	 * 
	 * @return A kozvetlen szomszed aszteroidakbol allo lista
	 */
	public ArrayList<Asteroid> getNeighboringAsteroids() {
		Proto.println(Proto.getId(this) + ".getNeighboringAsteroids()");
		return neighbors;
	}

	/**
	 * Hozzaadja a neighbor aszteroidat az aszteroida neighbors kollekciojahoz.
	 * 
	 * @param a Az uj szomszedos aszteroida
	 */
	public void accept(Asteroid a) {
		Proto.println(Proto.getId(this) + ".accept(" + Proto.getId(a) + ")");
		Proto.incrTabs();
		if (!neighbors.contains(a)) {
			neighbors.add(a);
		}
		Proto.decrTabs();
	}

	/**
	 * Eltavolitja a neighbor aszteroidat a neighbors kollekciobol.
	 * 
	 * @param a Az eltavolitando szomszed aszteroida
	 */
	public void remove(Asteroid a) {
		Proto.println(Proto.getId(this) + ".remove(" + Proto.getId(a) + ")");
		Proto.incrTabs();
		if (neighbors.contains(a)) {
			neighbors.remove(a);
			a.remove(this);
		}
		Proto.decrTabs();
	}

	/**
	 * Megadja az aszteroida d-edik szomszedjat (az aszteroidahoz tartozo
	 * teleportkapuk altali szomszedai figyelembevetelevel). A d parametert modulo a
	 * szomszedok szamaban ertelmezi.
	 * 
	 * @param d Az aszteroida adott szomszedjanak sorszama (modulo a szomszedok
	 *          szama)
	 * @return A d-edik szomszedos aszteroida
	 */
	public Asteroid getNeighbor(int d) {
		ArrayList<Asteroid> list = this.getNeighbors();
		if (list.size() != 0) {
			d = (d * Integer.signum(d)) % list.size();
			Asteroid neighbor = list.get(d);
			Proto.println(Proto.getId(this) + ".getNeighbor(" + d + ")" + " = " + Proto.getId(neighbor));
			return neighbor;
		} else
			return null;
	}

	/**
	 * Visszaadja az adott aszteroidaval szomszedos aszteroidak kollekciojat,
	 * beleertve ebbe az aszteroida teleportkapuk altali szomszedait is.
	 * 
	 * @return A szomszedos aszteroidak kollekcioja
	 */
	public ArrayList<Asteroid> getNeighbors() {
		Proto.println(Proto.getId(this)+".getNeighbors()");
		
		ArrayList<Asteroid> neighborList = new ArrayList<Asteroid>();
		neighborList.addAll(neighbors);
		for (TeleportingGate tg : gates) {
			Asteroid other = tg.getPair().getAsteroid();
			if (other != null) {
				neighborList.add(other);
			}
		}
		return neighborList;
	}
	
	/**
	 * Visszaadja az adott aszteroida melletti aktiv teleportkapuk
	 * es azok parjaibol allo listat.
	 * 
	 * @return a szomszedos aktiv teleportkapuk es parjaik listaja
	 */
	public ArrayList<TeleportingGate> getNeighboringGatePairs() {
		// Proto.println(Proto.getId(this)+".getNeighbors()");
		// Proto.incrTabs();
		ArrayList<TeleportingGate> neighborList = new ArrayList<TeleportingGate>();
		for (TeleportingGate tg : gates) {
			Asteroid other = tg.getPair().getAsteroid();
			if (other != null) {
				neighborList.add(tg);
				neighborList.add(tg.getPair());
			}
		}
		Proto.decrTabs();
		return neighborList;
	}

	/**
	 * Hozzaadja a c karaktert az aszteroidahoz; az inicializalashoz hasznalando.
	 * 
	 * @param c Az uj, hozzaadando karakter
	 */
	public void addCharacter(Character c) {
		characters.add(c);
	}

	/**
	 * A c karakter megerkezik az aszteroidara. Az aszteroida reagal erre, es jelzi
	 * ezt a karakternek az acceptedBy(Asteroid a) fuggveny hivasaval. A karakter
	 * ezutan specializalt mukodest valosithat meg az aszteroidara erkezeskor.
	 * 
	 * @param c Az aszteroidara erkezo karakter
	 */
	public void accept(Character c) {
		Proto.println(Proto.getId(this) + ".accept(" + Proto.getId(c) + ")");
		Proto.incrTabs();
		c.acceptedBy(this);
		Proto.decrTabs();
	}

	/**
	 * A karakter megerkezik az aszteroidara, az aszteroida hozzaadja a characters
	 * listaja vegere.
	 * 
	 * @param c Az ujonnan megerkezett karakter
	 */
	public void acceptRegularCharacter(Character c) {
		Proto.println(Proto.getId(this) + ".acceptRegularCharacter(" + Proto.getId(c) + ")");
		characters.add(c);
	}

	/**
	 * Az s telepes megerkezik az aszteroidara, es az aszteroida hozzaadja a
	 * characters kollekciojahoz. Az aszteroida ezutan ellenorzi az urbazishoz
	 * szukseges nyersanyagok megletet, (mivel az erkezessel ez lehetseges).
	 * 
	 * @param s Az aszteroidara erkezo telepes
	 */
	public void accept(Settler s) {
		Proto.println(Proto.getId(this) + ".accept(" + Proto.getId(s) + ")");
		//game.getGamePanel().writeToMessageBoard("Settler arrived to a new asteroid!");
		//game.getGamePanel().writeToMessageBoard("This asteroid's rocklayerThickness: " + this.getRockLayerThickness());
		characters.add(s);
		this.checkSpaceBase();
	}

	/**
	 * A c karakter elhagyja az aszteroidat, az aszteroida eltavolitja a characters
	 * kollekciojabol.
	 * 
	 * @param c Az aszteroidarol tovamozgo karakter
	 */
	public void remove(Character c) {
		Proto.println(Proto.getId(this) + ".remove(" + Proto.getId(c) + ")");
		characters.remove(c);
	}

	/**
	 * Visszaadja az adott aszteroidan tartozkodo karakterek kollekciojat (akar a
	 * felszinen, akar a megfurt ureges magban tartozkodnak).
	 * 
	 * @return Az aszteroidan levo karakterek kollekcioja
	 */
	public ArrayList<Character> getCharacters() {
		// Proto.println(Proto.getId(this) + ".getCharacters()");
		return characters;
	}

	/**
	 * A tg teleportkapu palyara all az aszteroida korul, az aszteroida hozzaadja a
	 * gates kollekciojahoz. Ezzel uj szomszedsag is letrejohet ket aszteroida
	 * kozott.
	 * 
	 * @param tg A palyara allitott teleportkapu
	 */
	public void accept(TeleportingGate tg) {
		Proto.println(Proto.getId(this) + ".accept(" + Proto.getId(tg) + ")");
		Proto.incrTabs();
		gates.add(tg);
		tg.setAsteroid(this);
		Proto.decrTabs();
	}

	/**
	 * A tg teleportkaput eltavolitja az aszteroida koruli palyarol, az aszteroida
	 * torli a gates kollekciojabol.
	 * 
	 * @param tg A torolt teleportkapu
	 */
	public void remove(TeleportingGate tg) {
		Proto.println(Proto.getId(this) + ".remove(" + Proto.getId(tg) + ")");
		gates.remove(tg);
	}

	/**
	 * Visszaadja az adott aszteroida korul keringo teleportkapukat.
	 * 
	 * @return Az aszteroidahoz tartozo teleportkapuk listaja
	 */
	public ArrayList<TeleportingGate> getGates() {
		// Proto.println(Proto.getId(this) + ".getGates()");
		return gates;
	}

	/**
	 * Egy az aszteroidan tartozkodo telepes behelyezi az r nyersanyagot az
	 * aszteroida megfurt ureges magjaba, az aszteroida azt beallitja resource
	 * attributumanak. Ha az aszteroida meg napkozelben is van, akkor a resource
	 * nyersanyagon meghivja a drilledOut(Asteroid a) fuggvenyt.
	 * 
	 * Ha a parameterul kapott r erteke null, akkor a fuggvenynek nincs hatasa, a
	 * resource attributumot nem irja felul.
	 * 
	 * @param s A nyersanyagot visszatolto telepes
	 * @param r A visszatoltott nyersanyagegyseg
	 */
	public void accept(Settler s, Resource r) {
		Proto.println(Proto.getId(this) + ".accept(" + Proto.getId(s) + ", " + Proto.getId(r) + ")");
		
		Proto.incrTabs();
		if (this.isMined()) {
			Resource rRestored = s.remove(r);
			if (rRestored != null) { // Ha s-nel nincs meg r, nem tortenik semmi.
				resource = rRestored;
				game.getGamePanel().writeToMessageBoard(
						game.getGamePanel().getController().getActPlayer().getName()
						+ " restored a resource succesfully.");
				if (sun.isCloseToSun(this)) {

					if (resource.isCompatibleWith(new Uranium())) {
						Uranium ur = (Uranium)resource;
						game.getGamePanel().writeToMessageBoard(
								"Uranium exposed. Life decreasing to "
								+ (ur.getLife() - 1) + ".");
					} else if (resource.isCompatibleWith(new Ice())) {
						game.getGamePanel().writeToMessageBoard("Ice sublimates.");
					}

					resource.drilledOut(this);
				}
			}
		}
		Proto.decrTabs();
	}

	/**
	 * A magban talalhato nyersanyag eltavolitodik az aszteroidabol (peldaul
	 * banyaszas hatasara). Az aszteroida a resource attributumat null-ra allitja.
	 * Ha kezdetben resource erteke null volt, a fuggvenynek nincs mellekhatasa.
	 * 
	 * @return
	 */
	public void removeResource() {
		Proto.println(Proto.getId(this) + ".removeResource()" + " - " + Proto.getId(resource));
		resource = null;
	}

	/**
	 * Visszaadja az adott aszteroida magjaban talalhato nyersanyagot.
	 * 
	 * @return A magban talalhato nyersanyagegyseg
	 */
	public Resource getResource() {
		Proto.println(Proto.getId(this) + ".getResource()" + " = " + Proto.getId(resource));
		return resource;
	}

	/**
	 * Az aszteroida rockLayerThickness attributumat eggyel csokkenti, amennyiben az
	 * pozitiv volt. Ha ezutan a kopenyvastagsag 0, es a resource attributuma nem
	 * null, akkor a sun objektum isCloseTo(Asteroid a) fuggvenyevel lekerdezi, hogy
	 * napkozelben talalhato-e. Ha igen, akkor meghivja a resource nyersanyag
	 * drilledOut() fuggvenyet, jelezve, hogy napkozeli aszteroidan felszinre
	 * kerult.
	 */
	public void drilled() {
		Proto.println(Proto.getId(this) + ".drilled()");
		Proto.incrTabs();
		if (rockLayerThickness >= 1) {
			rockLayerThickness--;
			if (rockLayerThickness == 0 && resource != null && sun.isCloseToSun(this)) {
				
				if(resource.isCompatibleWith(new Uranium())) {
					game.getGamePanel().writeToMessageBoard(" Uranium explodes");
				}else if(resource.isCompatibleWith(new Ice())) {
					game.getGamePanel().writeToMessageBoard(" Ice sublimates");
				}
				resource.drilledOut(this);
			}
		}
		Proto.decrTabs();
	}

	/**
	 * Az s telepes banyaszik az aszteroidan. Ha az aszteroida kopenyvastagsaga nem
	 * 0, akkor a fuggvenynek nincs hatasa. Ha a kopenyvastagsag 0, es az aszteroida
	 * resource attributuma nem null, akkor eltavolitja azt a magjabol
	 * (removeResource()), es eltaroltatja azt az s telepessel (s.accept(resource)).
	 * Ezenkivul meghivja a checkSpaceBase() metodust, (mivel a banyaszat hatasara
	 * osszegyulhetett az urbazishoz szukseges nyersanyagmennyiseg az aszteroidan).
	 * 
	 * @param s
	 */
	public void minedBy(Settler s) {
		Proto.println(Proto.getId(this) + ".minedBy(" + Proto.getId(s) + ")");
		Proto.incrTabs();
		if (rockLayerThickness == 0 && resource != null) {
			s.accept(resource);
			this.removeResource();
			game.getGamePanel().writeToMessageBoard(game.getGamePanel().getController().getActPlayer().getName()+ " mined successfully.");
			this.checkSpaceBase();
		} else {
			game.getGamePanel().writeToMessageBoard(game.getGamePanel().getController().getActPlayer().getName()+ " failed to mine.");
			Proto.println("Asteroid is not drilled or it is empty.");
		}
		Proto.decrTabs();
	}

	/**
	 * Az aszteroidat UFO probalja banyaszni. Ha az aszteroida meg van furva (es van
	 * benne nyersanyag), akkor a benne levo nyersanyagegyseg eltunik a jatekbol.
	 */
	public void minedByUFO() {
		Proto.println(Proto.getId(this) + ".minedByUFO()");
		Proto.incrTabs();
		if (rockLayerThickness == 0 && resource != null) {
			Resource r = resource;
			this.removeResource();
			r.removeFromGame();
		}
		Proto.decrTabs();
	}

	/**
	 * Visszater annak logikai ertekevel, hogy az aszteroida megfurt es ureges.
	 * 
	 * @return Pontosan akkor igaz, ha az aszteroida kopenyvastagsaga 0, es a
	 *         resource attributuma null.
	 */
	public boolean isMined() {
		String ismined = (this.rockLayerThickness == 0 && this.resource == null) ? "true" : "false";
		Proto.println(Proto.getId(this) + ".isMined()" + " = " + ismined);
		return (this.rockLayerThickness == 0 && this.resource == null) ? true : false;
	}

	/**
	 * A megfurt, napkozelben levo, radioaktiv nyersanyagot tartalmazo aszteroida
	 * felrobban. Minden rajta tartozkodo karakternek meghivja a reactToExplosion()
	 * fuggvenyet. Ezutan a szomszedain a remove(Asteroid neighbor) fuggvenyt hivja,
	 * amivel torli magat a szomszedai szomszedsagi listaibol, majd megsemmisiti a
	 * korulotte keringo teleportkapukat a parjaikkal egyutt. Vegul eltavolitja
	 * magat a jatekbol a Game osztaly removeAsteroid(Asteroid a) fuggvenyet hivva.
	 * 
	 * @param rr - a radioaktiv nyersanyag, amely felrobbantja az aszteroidat
	 */
	public void explodedBy(RadioactiveResource rr) {
		Proto.println(Proto.getId(this) + ".explodedBy(" + Proto.getId(rr) + ")");
		Proto.incrTabs();
		game.getGamePanel().writeToMessageBoard("Uranium is exploding!!!");
		resource.removeFromGame();

		for (int i = characters.size() - 1; i >= 0; i--) {
			characters.get(i).reactToExplosion();
		}
		for (int i = gates.size() - 1; i >= 0; i--) {
			gates.get(i).die();
		}
		for (int i = neighbors.size() - 1; i >= 0; i--) {
			neighbors.get(i).remove(this);
		}
		
		game.removeAsteroid(this);
		dead = true;
		Proto.getAllObjects().removeAsteroid(this);
		Proto.decrTabs();
	}

	/**
	 * Az aszteroidan vegigsopor a napvihar. Ha az aszteroida nincs megfurva vagy a
	 * mag nem ureges (isMined()==false), akkor a fuggveny meghivja az aszteroidan
	 * tartozkodo karakterek die() fuggvenyet.
	 */
	public void destroySurface() {
		Proto.println(Proto.getId(this) + ".destroySurface()");
		Proto.incrTabs();
		if (!this.isMined()) {
			for (int i = characters.size() - 1; i >= 0; i--) {
				characters.get(i).die();
			}
		}

		for (TeleportingGate tg : gates) {
			tg.goMad();
		}
		Proto.decrTabs();
	}

	/**
	 * Ellenorzi, hogy az adott aszteroidan levo telepeseknel rendelkezesre all-e az
	 * urbazis felepitesehez szukseges nyersanyagmennyiseg. Ha igen, akkor meghivja
	 * a Game endGame() metodusat.
	 */
	public void checkSpaceBase() {
		Proto.println(Proto.getId(this) + ".checkSpaceBase()");
		Proto.incrTabs();
		ArrayList<Resource> temp = new ArrayList<Resource>();
		for (Character c : characters) {
			temp.addAll(c.getCollectedResources());
		}

		// Nem hasznalt lehetseges alternativa:
		// a megfurt magban levo nyersanyag is szamit.
		/*
		 * if (rockLayerThickness == 0) { temp.add(resource); }
		 */

		Recipe recipe = game.getSpaceBaseRecipe();
		for (Resource r : temp) {
			if (recipe.isEmpty()) {
				break;
			}
			recipe.isNeeded(r);
		}

		if (recipe.isEmpty()) {
			game.endGame();
		}
		recipe.reset();
		Proto.decrTabs();
	}
	
	/**
	 * Megadja, hogy az aszteroida jatekban van-e, vagy sem.
	 * 
	 * @return	- <code>true</code>, ha az aszteroida mar nincs jatekban
	 */
	public boolean isDead() {
		return dead;
	}
	
	/**
	 * Megadja, hogy az aszteroida ki van-e emelve.
	 * 
	 * @return	- <code>true</code>, ha az aszteroida kiemelt
	 */
	public boolean isEmphasized() {
		return emphasized;
	}
	
	/**
	 * Beallitja az aszteroida kiemeltseget (kiemelt vagy sem).
	 * @param ag - az aszteroida uj kiemelt allapota
	 */
	public void setEmphasized(boolean emphasized) {
		this.emphasized = emphasized;
	}

}
