package berenyi_kft;

import java.util.ArrayList;

/**
 * 
 * @author berenyi_kft
 * Aszteroidákat reprezentáló osztály
 */
public class Asteroid {
	 
	 /**
	  * az aszteroida köpenyvastagsága, vagyis a magot borító sziklarétegek száma
	  */
	 int rockLayerThickness;
	 
	 /**
	  * a játékot reprezentáló osztály
	  */
	 Game game;
	
	 /**
	  * az aszteroida magjában található egységnyi nyersanyag, 
	  * ha a mag üreges, akkor értéke null
	  */
	 Resource resource;
	 
	 /**
	  * az aszteroidaövben levõ Nap
	  */
	 Sun sun;
	 
	 /**
	  * az aszteroidával szomszédos aszteroidák listája; 
	  * a teleportkapuk által szomszédossá vált aszteroidákat is magába foglalja
	  */
	 ArrayList<Asteroid> neighbors = new ArrayList<Asteroid>();
	 
	 /**
	  * az aszteroidán tartózkodó karakterek (telepesek, robotok, stb.) kollekciója
	  */
	 ArrayList<Character> characters = new ArrayList<Character>();
	 
	 /**
	  * a közvetlenül az aszteroida körül keringõ teleportkapuk halmaza
	  */
	 ArrayList<TeleportingGate> gates = new ArrayList<TeleportingGate>();
	
//------------------------------------------------------------------------
	 
	 /**
	  * ctor
	  */
	 public Asteroid(){}
	 
	 public void addNeighbor(Asteroid a) {
		 neighbors.add(a);
		 System.out.println("Asteroid's addNeighbor(a: Asteroid) has been called");
	 }
	 
	 /**
	  * Hozzáadja a neighbor aszteroidát az aszteroida neighbors kollekciójához.
	  * @param a
	  */
	 public void accept(Asteroid a) {
		 neighbors.add(a);
		 System.out.println("Asteroid's accept(a: Asteroid) has been called");
	 }
	 
	 /**
	  * Eltávolítja a neighbor aszteroidát a neighbors kollekcióból.
	  * @param a
	  */
	 public void remove(Asteroid a) {
		 if(neighbors.contains(a)) {
			 neighbors.remove(a);
		 }
		 System.out.println("Asteroid's remove(a: Asteroid) has been called");
	 }
	 
	 /**
	  * Megadja az aszteroida d-edik szomszédját 
	  * (az aszteroidához tartozó teleportkapuk általi szomszédai figyelembevételével).
	  * @param d
	  * @return
	  */
	 public Asteroid getNeighbor(int d) {
		 System.out.println("Asteroid's getNeighbor(d: int) has been called");
		 return neighbors.get(d);
	 }
	 
	 public int getRockLayerThickness() {
		 System.out.println("Asteroid's getRockLayerThickness() has been called");
		 return rockLayerThickness;
	 }
	 
	 public void setRockLayerThickness(int value) {
		 System.out.println("Asteroid's setRockLayerThickness(value: int) has been called");
		 rockLayerThickness=value;
	 }
	 
	 /**
	  * Visszaadja az adott aszteroidával szomszédos aszteroidák kollekcióját, 
	  * beleértve ebbe az aszteroida közvetlen szomszédait (neighbors)
	  * és a teleportkapuk (gates) általi szomszédokat is.
	  * @return
	  */
	 public ArrayList<Asteroid> getNeighbors(){
		 System.out.println("Asteroid's getNeighbors() has been called");
		 return neighbors;
	 }
	 
	 
	 /**
	  * A c karakter megérkezik az aszteroidára, 
	  * az aszteroida hozzáadja a characters kollekciójához.
	  * @param c
	  */
	 public void accept(Character c) {
		 characters.add(c);
		 System.out.println("Asteroid's accept(c: Character) has been called");
	 }
	 
	 /**
	  * A c karakter elhagyja az aszteroidát, az aszteroida 
	  * eltávolítja a characters kollekciójából.
	  * @param c
	  */
	 public void remove(Character c) {
		 characters.remove(c);
		 System.out.println("Asteroid's remove(c: Character) has been called");
	 }
	 
	 /**
	  * Visszaadja az adott aszteroidán tartózkodó karakterek kollekcióját.
	  * @return
	  */
	 public ArrayList<Character> getCharacters(){
		 System.out.println("Asteroid's getCharacters() has been called");
		 return characters;
	 }
	 
	 /**
	  * A tg teleportkapu pályára áll az aszteroida körül, 
	  * az aszteroida hozzáadja a gates kollekciójához.
	  * @param tg
	  */
	 public void accept(TeleportingGate tg) {
		 System.out.println("Asteroid's accept(tg: TeleportingGate) has been called");
		 gates.add(tg);
	 }
	 
	 /**
	  * A tg teleportkaput eltávolítja az aszteroida körüli pályáról, 
	  * az aszteroida törli a gates kollekciójából. 
	  * @param tg
	  */
	 public void remove(TeleportingGate tg) {
		 System.out.println("Asteroid's remove(tg: TeleportingGate) has been called");
		 gates.remove(tg);
	 }
	 
	 /**
	  * Visszaadja az adott aszteroidához tartozó teleportkapukat.
	  * @return
	  */
	 public ArrayList<TeleportingGate> getGates(){
		 System.out.println("Asteroid's getGates() has been called");
		 return gates;
	 }
	 
	 /**
	  * Egy az aszteroidán tartózkodó telepes behelyezi az r nyersanyagot 
	  * az aszteroida üreges magjába, az aszteroida azt beállítja resource attribútumának. 
	  * Ha a paraméterül kapott r=null, 
	  * akkor a függvénynek nincs hatása, 
	  * a resource attribútumot nem írja felül.
	  * @param r
	  */
	 //Ezt a függvényt a Settlernek a restore(r) metódusa hívja meg, azt változtattuk rajta, hogy nem csak a Resource
	 //a paraméter, hanem a Settler is. Settler restore-ban 2 paraméterrel hívjátok meg.
	 public void accept(Settler s, Resource r) {
		 if(this.isMined()) {
			 resource=r;
			 resource.setAsteroid(this);
			 s.remove(r);
			 if(sun.isCloseToSun(this)) {
				 resource.drilledOut(this);  //Donát tanácsait megfogadva ha napközelben restore-olunk, akkor hívódik meg.
			 }
		 }
		 System.out.println("Asteroid's accept(s: Settler, r: Resource) has been called");
	 }
	 
	 /**
	  * Egy az aszteroidán tartózkodó telepes eltávolítja a magban található nyersanyagot. 
	  * Az aszteroida a resource attribútumát null-ra állítja. Ha kezdetben resource=null volt,
	  * a függvénynek nincs mellékhatása.
	  * @return
	  */
	 public void removeResource() {
		 resource=null;
		 System.out.println("Asteroid's removeResource() has been called");
	 }
	 
	 /**
	  * Visszaadja az adott aszteroida magjában található nyersanyagot.
	  * @return
	  */
	 public Resource getResource() {
		 System.out.println("Asteroid's getResource() has been called");
		 return resource;
	 }
	 
	 /**
	  * Az aszteroida rockLayerThickness attribútumát eggyel csökkenti, 
	  * amennyiben az pozitív volt. Ha ekkor a köpenyvastagság 0, 
	  * és a resource attribútuma nem null, akkor a sun objektum isCloseTo(Asteroid a) 
	  * függvényével lekérdezi, hogy napközelben található-e. 
	  * Ha igen, akkor meghívja a resource nyersanyag drilledOut() függvényét, jelezve, 
	  * hogy napközeli aszteroidán felszínre került.
	  */
	 public void drilled() {
		 if(rockLayerThickness>=1)
			 this.setRockLayerThickness(this.rockLayerThickness--);
		 if(rockLayerThickness==0) {
			 if(resource!=null) {
				 if(sun.isCloseToSun(this)) {
					 resource.drilledOut(this);
				 }
			 }
		 }
		 System.out.println("Asteroid's drilled() has been called");
	 }
	 
	 /**
	  * Az s telepes bányászik az aszteroidán. Ha az aszteroida köpenyvastagsága nem 0,
	  *  akkor a függvénynek nincs hatása. Ha a köpenyvastagság 0, 
	  *  és az aszteroida resource attribútuma nem null,
	  *   akkor eltávolítja azt a magjából (removeResource()),
	  *  és eltároltatja azt az s telepessel (s.accept(resource)), ezenkívül
	  *  meghívja a checkSpaceBase() metódust.
	  * @param s
	  */
	 public void minedBy(Settler s) {
		 if(rockLayerThickness == 0 && resource != null) {
			 s.accept(resource);
			 resource.setAsteroid(null);
			 this.removeResource();
			 this.checkSpaceBase();
		 }
		 System.out.println("Asteroid's minedBy() has been called");
	 }
	 
	 /**
	  * Visszatér annak logikai értékével, hogy az aszteroida megfúrt és üreges,
	  *  vagyis rockLayerThickness=0 és resource=null.
	  * @return
	  */
	 public boolean isMined() {
		 System.out.println("Asteroid's isMined() has been called");
		 return (this.rockLayerThickness==0 && this.resource==null) ? true : false;
	 }
	 
	 /**
	  * A megfúrt, napközelben lévõ, radioaktív nyersanyagot tartalmazó 
	  * aszteroida felrobban. Minden rajta tartózkodó karakternek meghívja
	  * a reactToExplosion() függvényét. Az aszteroida szomszédain a 
	  * remove(Asteroid neighbor) függvényt hívja, amivel törli magát
	  * a szomszédai szomszédsági listáiból, majd megsemmisíti
	  * a körülötte keringõ teleportkapukat a párjaikkal együtt. Végül eltávolítja magát 
	  * a játékból a Game osztály removeAsteroid() függvényét hívva.
	  * @param rr
	  */
	 public void explodedBy(RadioactiveResource rr) {
		 for(Character c : characters){
			 c.reactToExplosion();
		 }
		 
		 for(TeleportingGate tg : gates) {
			 tg.die();
		 }
		 
		 for(Asteroid a : neighbors) {
			 a.remove(this);
		 }
		 
		 game.removeAsteroid(this);
		 System.out.println("Asteroid's explodedBy(rr: RadioactiveResource) has been called");
	 }
	 
	 /**
	  * Az aszteroidán végigsöpör a napvihar. Ha az aszteroida 
	  * nincs megfúrva (rockLayerThickness > 0), 
	  * vagy a mag nem üreges (getResource() != null), akkor a függvény 
	  * meghívja az aszteroidán tartózkodó karakterek die() függvényét.
	  */
	 public void destroySurface() {
		 if(!this.isMined()) {
			 for(Character c: characters) {
				 c.die();
			 }
		 }
		 System.out.println("Asteroid's destroySurface() has been called");
	 }
	 
	 /**
	  * Ellenõrzi, hogy az adott aszteroidán lévõ telepeseknél 
	  * rendelkezésre áll-e az ûrbázis felépítéséhez szükséges nyersanyagmennyiség.
	  * Ha igen, akkor meghívja a Game endGame() metódusát.
	  */
	 public void checkSpaceBase() {
		 ArrayList<Resource> temp=new ArrayList<Resource>();
		 for(Character c: characters) {
			 temp.addAll(c.getCollectedResources());
		 }
		 
		 Recipe recipe=game.getSpaceBaseRecipe();
		 for(Resource r : temp) {
			 if(recipe.isEmpty()) //Az aszteroida resource-át is ellenõrizni kell a checkspacebase-ben?
				 break;
			 recipe.isNeeded(r);
		 }
		 
		 if(recipe.isEmpty())
			 game.endGame();
		 System.out.println("Asteroid's checkSpaceBase() has been called");
		 recipe.reset();
	 }
	 
}
