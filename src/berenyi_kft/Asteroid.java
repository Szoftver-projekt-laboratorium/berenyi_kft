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
	 ArrayList<Character> character = new ArrayList<Character>();
	 
	 /**
	  * a közvetlenül az aszteroida körül keringõ teleportkapuk halmaza
	  */
	 ArrayList<TeleportingGate> gates = new ArrayList<TeleportingGate>();
	
//------------------------------------------------------------------------
	 
	 /**
	  * ctor
	  */
	 Asteroid(){
		 
	 }
	 
	 //class diagramon addneighbor szerepel
	 /**
	  * Hozzáadja a neighbor aszteroidát az aszteroida neighbors kollekciójához.
	  * @param a
	  */
	 public void accept(Asteroid a) {
		 this.neighbors.add(a);
	 }
	 
	 /**
	  * Eltávolítja a neighbor aszteroidát a neighbors kollekcióból.
	  * @param a
	  */
	 public void remove(Asteroid a) {
		 if(this.neighbors.contains(a)) {
			 this.neighbors.remove(a);
		 }
	 }
	 
	 /**
	  * Megadja az aszteroida d-edik szomszédját 
	  * (az aszteroidához tartozó teleportkapuk általi szomszédai figyelembevételével).
	  * @param d
	  * @return
	  */
	 public Asteroid getNeighbor(int d) {
		 return this.neighbors.get(d);
	 }
	 
	 /**
	  * Visszaadja az adott aszteroidával szomszédos aszteroidák kollekcióját, 
	  * beleértve ebbe az aszteroida közvetlen szomszédait (neighbors)
	  * és a teleportkapuk (gates) általi szomszédokat is.
	  * @return
	  */
	 public ArrayList<Asteroid> getNeighbor(){
		 return this.neighbors;
	 }
	 
	 
	 /**
	  * A c karakter megérkezik az aszteroidára, 
	  * az aszteroida hozzáadja a characters kollekciójához.
	  * @param c
	  */
	 public void accept(Character c) {
		 
	 }
	 
	 /**
	  * A c karakter elhagyja az aszteroidát, az aszteroida 
	  * eltávolítja a characters kollekciójából.
	  * @param c
	  */
	 public void remove(Character c) {
		 
	 }
	 
	 /**
	  * Visszaadja az adott aszteroidán tartózkodó karakterek kollekcióját.
	  * @return
	  */
	 public ArrayList<Character> getCharacters(){
		 return this.character;
	 }
	 
	 /**
	  * A tg teleportkapu pályára áll az aszteroida körül, 
	  * az aszteroida hozzáadja a gates kollekciójához.
	  * @param tg
	  */
	 public void accept(TeleportingGate tg) {
		 
	 }
	 
	 /**
	  * A tg teleportkaput eltávolítja az aszteroida körüli pályáról, 
	  * az aszteroida törli a gates kollekciójából. 
	  * @param tg
	  */
	 public void remove(TeleportingGate tg) {
		 
	 }
	 
	 /**
	  * Visszaadja az adott aszteroidához tartozó teleportkapukat.
	  * @return
	  */
	 public ArrayList<TeleportingGate> getGate(){
		 return this.gates;
	 }
	 
	 /**
	  * Egy az aszteroidán tartózkodó telepes behelyezi az r nyersanyagot 
	  * az aszteroida üreges magjába, az aszteroida azt beállítja resource attribútumának. 
	  * Ha a paraméterül kapott r=null, 
	  * akkor a függvénynek nincs hatása, 
	  * a resource attribútumot nem írja felül.
	  * @param r
	  */
	 public void accept(Resource r) {
		 
	 }
	 
	 /**
	  * Egy az aszteroidán tartózkodó telepes eltávolítja a magban található nyersanyagot. 
	  * Az aszteroida a resource attribútumát null-ra állítja, visszatérési értékül
	  * a nyersanyagot adja. Ha kezdetben resource=null volt,
	  * a függvénynek nincs mellékhatása, és null-lal tér vissza
	  * @return
	  */
	 public Resource removeResource() {
		 //todo
		 return this.resource; //temp; 
	 }
	 
	 /**
	  * Visszaadja az adott aszteroida magjában található nyersanyagot.
	  * @return
	  */
	 public Resource getResource() {
		 return this.resource;
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
		 
	 }
	 
	 /**
	  * Az s telepes bányászik az aszteroidán. Ha az aszteroida köpenyvastagsága nem 0,
	  *  akkor a függvénynek nincs hatása. Ha a köpenyvastagság 0, 
	  *  és az aszteroida resource attribútuma nem null,
	  *   akkor eltávolítja azt a magjából (removeResource()),
	  *  és eltároltatja azt az s telepessel (s.accept(resource)).
	  * @param s
	  */
	 public void minedBySettler(Settler s) {
		 
	 }
	 
	 /**
	  * Visszatér annak logikai értékével, hogy az aszteroida megfúrt és üreges,
	  *  vagyis rockLayerThickness=0 és resource=null.
	  * @return
	  */
	 public boolean isMined() {
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
		 
	 }
	 
	 /**
	  * Az aszteroidán végigsöpör a napvihar. Ha az aszteroida 
	  * nincs megfúrva (rockLayerThickness > 0), 
	  * vagy a mag nem üreges (getResource() != null), akkor a függvény 
	  * meghívja az aszteroidán tartózkodó karakterek die() függvényét.
	  */
	 public void destroySurface() {
		 
	 }
	 
	 /**
	  * Ellenõrzi, hogy az adott aszteroidán lévõ telepeseknél 
	  * rendelkezésre áll-e az ûrbázis felépítéséhez szükséges nyersanyagmennyiség.
	  * Ha igen, akkor meghívja a Game endGame() metódusát.
	  */
	 public void checkSpaceBase() {
		 
	 }
	 
	 
	 
	
}
