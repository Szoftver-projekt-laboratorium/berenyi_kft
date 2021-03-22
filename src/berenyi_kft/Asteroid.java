package berenyi_kft;

import java.util.ArrayList;

/**
 * Aszteroida osztaly: nyersanyagot tartalmazhat,
 * illetve karakterek tartozkodhatnak rajta
 * @author berenyi_kft
 */
public class Asteroid {
	 
	 /**
	  * Az aszteroida kopenyvastagsaga,
	  * vagyis a magot borito sziklaretegek szama
	  */
	 private int rockLayerThickness;
	 
	 /**
	  * Referencia a jatekot reprezentalo osztalyra
	  */
	 private Game game;
	
	 /**
	  * Az aszteroida magjaban talalhato egysegnyi nyersanyag.
	  * Ha a mag ureges, akkor erteke null
	  */
	 private Resource resource = null;
	 
	 /**
	  * Az aszteroidaovben levo Nap
	  */
	 private Sun sun;
	 
	 /**
	  * Az aszteroidaval szomszedos aszteroidak listaja.
	  * A teleportkapuk altal szomszedossa valt
	  * aszteroidakat is magaba foglalja
	  */
	 private ArrayList<Asteroid> neighbors = new ArrayList<Asteroid>();
	 
	 /**
	  * Az aszteroidan tartozkodo karakterek
	  * (telepesek, robotok, stb.) kollekcioja
	  */
	 private ArrayList<Character> characters = new ArrayList<Character>();
	 
	 /**
	  * Az aszteroida korul kozvetlenul keringo teleportkapuk kollekcioja
	  */
	 private ArrayList<TeleportingGate> gates = new ArrayList<TeleportingGate>();
	
//------------------------------------------------------------------------
	 
	 /*
	 public void addNeighbor(Asteroid a) {
		 System.out.println("Asteroid's addNeighbor(a: Asteroid) has been called");
		 neighbors.add(a);
	 }
	 */
	 
	 /**
	  * Hozzaadja a neighbor aszteroidat az aszteroida neighbors kollekciojahoz.
	  * @param a Az uj szomszedos aszteroida
	  */
	 public void accept(Asteroid a) {
		 System.out.println("Asteroid's accept(a: Asteroid) has been called");
		 if (!neighbors.contains(a)) {
			 neighbors.add(a);
			 a.accept(this);
		 }
	 }
	 
	 /**
	  * Eltavolitja a neighbor aszteroidat a neighbors kollekciobol.
	  * @param a Az eltavolitando szomszed aszteroida
	  */
	 public void remove(Asteroid a) {
		 System.out.println("Asteroid's remove(a: Asteroid) has been called");
		 if (neighbors.contains(a)) {
			 neighbors.remove(a);
			 a.remove(this);
		 }
	 }
	 
	 /**
	  * Megadja az aszteroida d-edik szomszedjat (az aszteroidahoz tartozo
	  * teleportkapuk altali szomszedai figyelembevetelevel).
	  * A d parametert modulo a szomszedok szamaban ertelmezi.
	  * @param d Az aszteroida adott szomszedjanak sorszama
	  * 		 (modulo a szomszedok szama)
	  * @return A d-edik szomszedos aszteroida
	  */
	 public Asteroid getNeighbor(int d) {
		 System.out.println("Asteroid's getNeighbor(d: int) has been called");
		 d = d % this.getNeighbors().size();
		 return neighbors.get(d);
	 }
	 
	 /**
	  * Megadja az aszteroida kopenyvastagsagat.
	  * @return A kopenyt alkoto sziklaretegek szama
	  */
	 public int getRockLayerThickness() {
		 System.out.println("Asteroid's getRockLayerThickness() has been called");
		 return rockLayerThickness;
	 }
	 
	 /**
	  * Beallitja az aszteroida kopenyvastagsagat a parameterertekre.
	  * @param value A beallitando sziklareteg-vastagsag
	  */
	 public void setRockLayerThickness(int value) {
		 System.out.println("Asteroid's setRockLayerThickness(value: int) has been called");
		 rockLayerThickness=value;
	 }
	 
	 /**
	  * Visszaadja az adott aszteroidaval szomszedos aszteroidak kollekciojat, 
	  * beleertve ebbe az aszteroida teleportkapuk altali szomszedait is.
	  * @return A szomszedos aszteroidak kollekcioja
	  */
	 public ArrayList<Asteroid> getNeighbors(){
		 System.out.println("Asteroid's getNeighbors() has been called");
		 return neighbors;
	 }
	 
	 /**
	  * A c karakter megerkezik az aszteroidara, es az
	  * aszteroida hozzaadja a characters kollekciojahoz.
	  * Az aszteroida ezutan ellenorzi az urbazishoz szukseges
	  * nyersanyagok megletet, (mivel az erkezessel ez lehetseges).
	  * @param c Az aszteroidara erkezo karakter
	  */
	 public void accept(Character c) {
		 System.out.println("Asteroid's accept(c: Character) has been called");
		 characters.add(c);
		 // this.checkSpaceBase();
	 }
	 
	 /**
	  * A c karakter elhagyja az aszteroidat, az aszteroida 
	  * eltavolitja a characters kollekciojabol.
	  * @param c Az aszteroidarol tovamozgo karakter
	  */
	 public void remove(Character c) {
		 System.out.println("Asteroid's remove(c: Character) has been called");
		 characters.remove(c);
	 }
	 
	 /**
	  * Visszaadja az adott aszteroidan tartozkodo karakterek kollekciojat
	  * (akar a felszinen, akar a megfurt ureges magban tartozkodnak).
	  * @return Az aszteroidan levo karakterek kollekcioja
	  */
	 public ArrayList<Character> getCharacters() {
		 System.out.println("Asteroid's getCharacters() has been called");
		 return characters;
	 }
	 
	 /**
	  * A tg teleportkapu palyara all az aszteroida korul, 
	  * az aszteroida hozzaadja a gates kollekciojahoz.
	  * Ezzel uj szomszedsag is letrejohet ket aszteroida kozott.
	  * @param tg A palyara allitott teleportkapu
	  */
	 public void accept(TeleportingGate tg) {
		 System.out.println("Asteroid's accept(tg: TeleportingGate) has been called");
		 gates.add(tg);
		 if (tg.getPair().getAsteroid() != null) {
			 tg.getPair().getAsteroid().accept(this);
		 }
	 }
	 
	 /**
	  * A tg teleportkaput eltavolitja az aszteroida koruli palyarol, 
	  * az aszteroida torli a gates kollekciojabol. 
	  * @param tg A torolt teleportkapu
	  */
	 // TODO: Igy torlodnek a kezdetben meglevo szomszedsagok,
	 // 	  ahova kerult kesobb torlodo teleportkapu-par is!
	 public void remove(TeleportingGate tg) {
		 System.out.println("Asteroid's remove(tg: TeleportingGate) has been called");
		 gates.remove(tg);
		 if (tg.getPair().getAsteroid() != null) {
			 tg.getPair().getAsteroid().remove(this);
		 }
	 }
	 
	 /**
	  * Visszaadja az adott aszteroida korul keringo teleportkapukat.
	  * @return Az aszteroidahoz tartozo teleportkapuk listaja
	  */
	 public ArrayList<TeleportingGate> getGates(){
		 System.out.println("Asteroid's getGates() has been called");
		 return gates;
	 }
	 
	 /**
	  * Egy az aszteroidan tartozkodo telepes behelyezi az r nyersanyagot 
	  * az aszteroida megfurt ureges magjaba, az aszteroida azt beallitja
	  * resource attributumanak. Ha az aszteroida meg napkozelben is van,
	  * akkor a resource nyersanyagon meghivja a drilledOut(Asteroid a) fuggvenyt.
	  * 
	  * Ha a parameterul kapott r erteke null, akkor a fuggvenynek nincs hatasa, 
	  * a resource attributumot nem irja felul.
	  * @param s A nyersanyagot visszatolto telepes
	  * @param r A visszatoltott nyersanyagegyseg
	  */
	 public void accept(Settler s, Resource r) {
		 System.out.println("Asteroid's accept(s: Settler, r: Resource) has been called");
		 if (this.isMined()) {
			 resource = r;
			 s.remove(r);
			 if (sun.isCloseToSun(this)) {
				 resource.drilledOut(this);
			 }
		 }
	 }
	 
	 /**
	  * Egy az aszteroidan tartozkodo telepes eltavolitja a magban talalhato nyersanyagot. 
	  * Az aszteroida a resource attributumat null-ra allitja.
	  * Ha kezdetben resource erteke null volt, a fuggvenynek nincs mellekhatasa.
	  * @return
	  */
	 public void removeResource() {
		 System.out.println("Asteroid's removeResource() has been called");
		 resource = null;
	 }
	 
	 /**
	  * Visszaadja az adott aszteroida magjaban talalhato nyersanyagot.
	  * @return A magban talalhato nyersanyagegyseg
	  */
	 public Resource getResource() {
		 System.out.println("Asteroid's getResource() has been called");
		 return resource;
	 }
	 
	 /**
	  * Az aszteroida rockLayerThickness attributumat eggyel csokkenti, 
	  * amennyiben az pozitiv volt.
	  * Ha ezutan a kopenyvastagsag 0, es a resource attributuma nem null,
	  * akkor a sun objektum isCloseTo(Asteroid a) fuggvenyevel lekerdezi,
	  * hogy napkozelben talalhato-e. 
	  * Ha igen, akkor meghivja a resource nyersanyag drilledOut() fuggvenyet,
	  * jelezve, hogy napkozeli aszteroidan felszinre kerult.
	  */
	 public void drilled() {
		 System.out.println("Asteroid's drilled() has been called");
		 if (rockLayerThickness >= 1) {
			 int value = rockLayerThickness-1;
			 this.setRockLayerThickness(value);
		 }
		 if (rockLayerThickness==0) {
			 if (resource != null) {
				 if (sun.isCloseToSun(this)) {
					 resource.drilledOut(this);
				 }
			 }
		 }
	 }
	 
	 /**
	  * Az s telepes banyaszik az aszteroidan. Ha az aszteroida kopenyvastagsaga nem 0,
	  * akkor a fuggvenynek nincs hatasa. Ha a kopenyvastagsag 0, es az aszteroida
	  * resource attributuma nem null, akkor eltavolitja azt a magjabol (removeResource()),
	  * es eltaroltatja azt az s telepessel (s.accept(resource)).
	  * Ezenkivul meghivja a checkSpaceBase() metodust, (mivel a banyaszat hatasara
	  * osszegyulhetett az urbazishoz szukseges nyersanyagmennyiseg az aszteroidan).
	  * @param s
	  */
	 public void minedBy(Settler s) {
		 System.out.println("Asteroid's minedBy() has been called");
		 if (rockLayerThickness == 0 && resource != null) {
			 s.accept(resource);
			 this.removeResource();
			 this.checkSpaceBase();
		 }
	 }
	 
	 /**
	  * Visszater annak logikai ertekevel, hogy az aszteroida megfurt es ureges.
	  * @return Pontosan akkor igaz, ha az aszteroida kopenyvastagsaga 0,
	  * 		es a resource attributuma null.
	  */
	 public boolean isMined() {
		 System.out.println("Asteroid's isMined() has been called");
		 return (this.rockLayerThickness == 0 && this.resource == null) ? true : false;
	 }
	 
	 /**
	  * A megfurt, napkozelben levo, radioaktiv nyersanyagot tartalmazo 
	  * aszteroida felrobban. Minden rajta tartozkodo karakternek meghivja
	  * a reactToExplosion() fuggvenyet.
	  * Ezutan a szomszedain a remove(Asteroid neighbor) fuggvenyt hivja,
	  * amivel torli magat a szomszedai szomszedsagi listaibol, majd megsemmisiti
	  * a korulotte keringo teleportkapukat a parjaikkal egyutt.
	  * Vegul eltavolitja magat a jatekbol a Game osztaly
	  * removeAsteroid(Asteroid a) fuggvenyet hivva.
	  * @param rr
	  */
	 public void explodedBy(RadioactiveResource rr) {
		 System.out.println("Asteroid's explodedBy(rr: RadioactiveResource) has been called");
		 for (int i = characters.size()-1; i >= 0; i--) {
			 characters.get(i).reactToExplosion();
		 }
		 
		 for(int i = gates.size()-1; i>=0; i--) {
			 gates.get(i).die();
		 }
		 
		 for (int i = neighbors.size() - 1; i >= 0; i--) {
			 neighbors.get(i).remove(this);
		 }
		 
		 game.removeAsteroid(this);
	 }
	 
	 /**
	  * Az aszteroidan vegigsopor a napvihar. Ha az aszteroida 
	  * nincs megfurva vagy a mag nem ureges (isMined()==false), akkor
	  * a fuggveny meghivja az aszteroidan tartozkodo karakterek die() fuggvenyet.
	  */
	 public void destroySurface() {
		 System.out.println("Asteroid's destroySurface() has been called");
		 if (!this.isMined()) {
			 for (int i = characters.size()-1;i>=0;i--) {
				 characters.get(i).die();
			 }
		 }
	 }
	 
	 /**
	  * Ellenorzi, hogy az adott aszteroidan levo telepeseknel 
	  * rendelkezesre all-e az urbazis felepitesehez szukseges nyersanyagmennyiseg.
	  * Ha igen, akkor meghivja a Game endGame() metodusat.
	  */
	 public void checkSpaceBase() {
		 System.out.println("Asteroid's checkSpaceBase() has been called");
		 ArrayList<Resource> temp = new ArrayList<Resource>();
		 for (Character c: characters) {
			 temp.addAll(c.getCollectedResources());
		 }
		 // TODO: Hozza kell adni a megfurt aszteroida magjaban
		 // levo nyersanyagot is a temp-hez?
		 /*if (rockLayerThickness == 0) {
			 temp.add(resource);
		 }*/
		 
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
	 }
	 
	 /**
	  * Beallitja a magban talalhato nyersanyagot (az inicializalashoz hasznalando).
	  * @param r A magba beallitando nyersanyag.
	  * 		 Ha null, akkor az aszteroida ureges lesz.
	  */
	 public void addResource(Resource r) {
		 System.out.println("Asteroid's addResource(r: Resource) has been called");
		 resource = r;
	 }
	 
	 /**
	  * Beallitja a jatek osztalyt.
	  * @param g A jatekot reprezentalo osztaly
	  */
	 public void setGame(Game g) {
		 System.out.println("Asteroid's setGame(g: Game) has been called");
		 game = g;
	 }
	 
	 /**
	  * Beallitja a jatekban levo Napot.
	  * @param s A beallitando Nap
	  */
	 public void setSun(Sun s) {
		 System.out.println("Asteroid's setSun(s: Sun) has been called");
		 sun = s;
	 }
}
