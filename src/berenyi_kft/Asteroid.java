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
	  * a jï¿½tï¿½kot reprezentï¿½lï¿½ osztï¿½ly
	  */
	 Game game;
	
	 /**
	  * az aszteroida magjï¿½ban talï¿½lhatï¿½ egysï¿½gnyi nyersanyag, 
	  * ha a mag ï¿½reges, akkor ï¿½rtï¿½ke null
	  */
	 Resource resource;
	 
	 /**
	  * az aszteroidaï¿½vben levï¿½ Nap
	  */
	 Sun sun;
	 
	 /**
	  * az aszteroidï¿½val szomszï¿½dos aszteroidï¿½k listï¿½ja; 
	  * a teleportkapuk ï¿½ltal szomszï¿½dossï¿½ vï¿½lt aszteroidï¿½kat is magï¿½ba foglalja
	  */
	 ArrayList<Asteroid> neighbors = new ArrayList<Asteroid>();
	 
	 /**
	  * az aszteroidï¿½n tartï¿½zkodï¿½ karakterek (telepesek, robotok, stb.) kollekciï¿½ja
	  */
	 ArrayList<Character> characters = new ArrayList<Character>();
	 
	 /**
	  * a kï¿½zvetlenï¿½l az aszteroida kï¿½rï¿½l keringï¿½ teleportkapuk halmaza
	  */
	 ArrayList<TeleportingGate> gates = new ArrayList<TeleportingGate>();
	
//------------------------------------------------------------------------
	 
	 /**
	  * ctor
	  */
	 public Asteroid(){}
	 
	 public void addNeighbor(Asteroid a) {
		 System.out.println("Asteroid's addNeighbor(a: Asteroid) has been called");
		 neighbors.add(a);
	 }
	 
	 /**
	  * Hozzï¿½adja a neighbor aszteroidï¿½t az aszteroida neighbors kollekciï¿½jï¿½hoz.
	  * @param a
	  */
	 public void accept(Asteroid a) {
		 System.out.println("Asteroid's accept(a: Asteroid) has been called");
		 neighbors.add(a);
	 }
	 
	 /**
	  * Eltï¿½volï¿½tja a neighbor aszteroidï¿½t a neighbors kollekciï¿½bï¿½l.
	  * @param a
	  */
	 public void remove(Asteroid a) {
		 System.out.println("Asteroid's remove(a: Asteroid) has been called");
		 if(neighbors.contains(a)) {
			 neighbors.remove(a);
		 }
	 }
	 
	 /**
	  * Megadja az aszteroida d-edik szomszï¿½djï¿½t 
	  * (az aszteroidï¿½hoz tartozï¿½ teleportkapuk ï¿½ltali szomszï¿½dai figyelembevï¿½telï¿½vel).
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
	  * Visszaadja az adott aszteroidï¿½val szomszï¿½dos aszteroidï¿½k kollekciï¿½jï¿½t, 
	  * beleï¿½rtve ebbe az aszteroida kï¿½zvetlen szomszï¿½dait (neighbors)
	  * ï¿½s a teleportkapuk (gates) ï¿½ltali szomszï¿½dokat is.
	  * @return
	  */
	 public ArrayList<Asteroid> getNeighbors(){
		 System.out.println("Asteroid's getNeighbors() has been called");
		 return neighbors;
	 }
	 
	 
	 /**
	  * A c karakter megï¿½rkezik az aszteroidï¿½ra, 
	  * az aszteroida hozzï¿½adja a characters kollekciï¿½jï¿½hoz.
	  * @param c
	  */
	 public void accept(Character c) {
		 System.out.println("Asteroid's accept(c: Character) has been called");
		 characters.add(c);
	 }
	 
	 /**
	  * A c karakter elhagyja az aszteroidï¿½t, az aszteroida 
	  * eltï¿½volï¿½tja a characters kollekciï¿½jï¿½bï¿½l.
	  * @param c
	  */
	 public void remove(Character c) {
		 System.out.println("Asteroid's remove(c: Character) has been called");
		 characters.remove(c);
	 }
	 
	 /**
	  * Visszaadja az adott aszteroidï¿½n tartï¿½zkodï¿½ karakterek kollekciï¿½jï¿½t.
	  * @return
	  */
	 public ArrayList<Character> getCharacters(){
		 System.out.println("Asteroid's getCharacters() has been called");
		 return characters;
	 }
	 
	 /**
	  * A tg teleportkapu pï¿½lyï¿½ra ï¿½ll az aszteroida kï¿½rï¿½l, 
	  * az aszteroida hozzï¿½adja a gates kollekciï¿½jï¿½hoz.
	  * @param tg
	  */
	 public void accept(TeleportingGate tg) {
		 System.out.println("Asteroid's accept(tg: TeleportingGate) has been called");
		 gates.add(tg);
	 }
	 
	 /**
	  * A tg teleportkaput eltï¿½volï¿½tja az aszteroida kï¿½rï¿½li pï¿½lyï¿½rï¿½l, 
	  * az aszteroida tï¿½rli a gates kollekciï¿½jï¿½bï¿½l. 
	  * @param tg
	  */
	 public void remove(TeleportingGate tg) {
		 System.out.println("Asteroid's remove(tg: TeleportingGate) has been called");
		 gates.remove(tg);
	 }
	 
	 /**
	  * Visszaadja az adott aszteroidï¿½hoz tartozï¿½ teleportkapukat.
	  * @return
	  */
	 public ArrayList<TeleportingGate> getGates(){
		 System.out.println("Asteroid's getGates() has been called");
		 return gates;
	 }
	 
	 /**
	  * Egy az aszteroidï¿½n tartï¿½zkodï¿½ telepes behelyezi az r nyersanyagot 
	  * az aszteroida ï¿½reges magjï¿½ba, az aszteroida azt beï¿½llï¿½tja resource attribï¿½tumï¿½nak. 
	  * Ha a paramï¿½terï¿½l kapott r=null, 
	  * akkor a fï¿½ggvï¿½nynek nincs hatï¿½sa, 
	  * a resource attribï¿½tumot nem ï¿½rja felï¿½l.
	  * @param r
	  */
	 //Ezt a fï¿½ggvï¿½nyt a Settlernek a restore(r) metï¿½dusa hï¿½vja meg, azt vï¿½ltoztattuk rajta, hogy nem csak a Resource
	 //a paramï¿½ter, hanem a Settler is. Settler restore-ban 2 paramï¿½terrel hï¿½vjï¿½tok meg.
	 public void accept(Settler s, Resource r) {
		 System.out.println("Asteroid's accept(s: Settler, r: Resource) has been called");
		 if(this.isMined()) {
			 resource=r;
			 resource.setAsteroid(this);
			 s.remove(r);
			 if(sun.isCloseToSun(this)) {
				 resource.drilledOut(this);  //Donï¿½t tanï¿½csait megfogadva ha napkï¿½zelben restore-olunk, akkor hï¿½vï¿½dik meg.
			 }
		 }
	 }
	 
	 /**
	  * Egy az aszteroidï¿½n tartï¿½zkodï¿½ telepes eltï¿½volï¿½tja a magban talï¿½lhatï¿½ nyersanyagot. 
	  * Az aszteroida a resource attribï¿½tumï¿½t null-ra ï¿½llï¿½tja. Ha kezdetben resource=null volt,
	  * a fï¿½ggvï¿½nynek nincs mellï¿½khatï¿½sa.
	  * @return
	  */
	 public void removeResource() {
		 System.out.println("Asteroid's removeResource() has been called");
		 resource=null;
	 }
	 
	 /**
	  * Visszaadja az adott aszteroida magjï¿½ban talï¿½lhatï¿½ nyersanyagot.
	  * @return
	  */
	 public Resource getResource() {
		 System.out.println("Asteroid's getResource() has been called");
		 return resource;
	 }
	 
	 /**
	  * Az aszteroida rockLayerThickness attribï¿½tumï¿½t eggyel csï¿½kkenti, 
	  * amennyiben az pozitï¿½v volt. Ha ekkor a kï¿½penyvastagsï¿½g 0, 
	  * ï¿½s a resource attribï¿½tuma nem null, akkor a sun objektum isCloseTo(Asteroid a) 
	  * fï¿½ggvï¿½nyï¿½vel lekï¿½rdezi, hogy napkï¿½zelben talï¿½lhatï¿½-e. 
	  * Ha igen, akkor meghï¿½vja a resource nyersanyag drilledOut() fï¿½ggvï¿½nyï¿½t, jelezve, 
	  * hogy napkï¿½zeli aszteroidï¿½n felszï¿½nre kerï¿½lt.
	  */
	 public void drilled() {
		 System.out.println("Asteroid's drilled() has been called");
		 if(rockLayerThickness>=1) {
			 int value=rockLayerThickness-1;
			 this.setRockLayerThickness(value);
		 }
		 if(rockLayerThickness==0) {
			 if(resource!=null) {
				 if(sun.isCloseToSun(this)) {
					 resource.drilledOut(this);
				 }
			 }
		 }
	 }
	 
	 /**
	  * Az s telepes bï¿½nyï¿½szik az aszteroidï¿½n. Ha az aszteroida kï¿½penyvastagsï¿½ga nem 0,
	  *  akkor a fï¿½ggvï¿½nynek nincs hatï¿½sa. Ha a kï¿½penyvastagsï¿½g 0, 
	  *  ï¿½s az aszteroida resource attribï¿½tuma nem null,
	  *   akkor eltï¿½volï¿½tja azt a magjï¿½bï¿½l (removeResource()),
	  *  ï¿½s eltï¿½roltatja azt az s telepessel (s.accept(resource)), ezenkï¿½vï¿½l
	  *  meghï¿½vja a checkSpaceBase() metï¿½dust.
	  * @param s
	  */
	 public void minedBy(Settler s) {
		 System.out.println("Asteroid's minedBy() has been called");
		 if(rockLayerThickness == 0 && resource != null) {
			 s.accept(resource);
			 resource.setAsteroid(null);
			 this.removeResource();
			 this.checkSpaceBase();
		 }
	 }
	 
	 /**
	  * Visszatï¿½r annak logikai ï¿½rtï¿½kï¿½vel, hogy az aszteroida megfï¿½rt ï¿½s ï¿½reges,
	  *  vagyis rockLayerThickness=0 ï¿½s resource=null.
	  * @return
	  */
	 public boolean isMined() {
		 System.out.println("Asteroid's isMined() has been called");
		 return (this.rockLayerThickness==0 && this.resource==null) ? true : false;
	 }
	 
	 /**
	  * A megfï¿½rt, napkï¿½zelben lï¿½vï¿½, radioaktï¿½v nyersanyagot tartalmazï¿½ 
	  * aszteroida felrobban. Minden rajta tartï¿½zkodï¿½ karakternek meghï¿½vja
	  * a reactToExplosion() fï¿½ggvï¿½nyï¿½t. Az aszteroida szomszï¿½dain a 
	  * remove(Asteroid neighbor) fï¿½ggvï¿½nyt hï¿½vja, amivel tï¿½rli magï¿½t
	  * a szomszï¿½dai szomszï¿½dsï¿½gi listï¿½ibï¿½l, majd megsemmisï¿½ti
	  * a kï¿½rï¿½lï¿½tte keringï¿½ teleportkapukat a pï¿½rjaikkal egyï¿½tt. Vï¿½gï¿½l eltï¿½volï¿½tja magï¿½t 
	  * a jï¿½tï¿½kbï¿½l a Game osztï¿½ly removeAsteroid() fï¿½ggvï¿½nyï¿½t hï¿½vva.
	  * @param rr
	  */
	 public void explodedBy(RadioactiveResource rr) {
		 System.out.println("Asteroid's explodedBy(rr: RadioactiveResource) has been called");
		 for(int i = characters.size()-1; i >= 0; i--) {
			 characters.get(i).reactToExplosion();
		 }
		 
		 for(int i=gates.size()-1;i>=0;i--) {
			 gates.get(i).die();
		 }
		 
		 for(int i=neighbors.size()-1;i>=0;i--) {
			 neighbors.get(i).remove(this);
		 }
		 
		 game.removeAsteroid(this);
	 }
	 
	 /**
	  * Az aszteroidï¿½n vï¿½gigsï¿½pï¿½r a napvihar. Ha az aszteroida 
	  * nincs megfï¿½rva (rockLayerThickness > 0), 
	  * vagy a mag nem ï¿½reges (getResource() != null), akkor a fï¿½ggvï¿½ny 
	  * meghï¿½vja az aszteroidï¿½n tartï¿½zkodï¿½ karakterek die() fï¿½ggvï¿½nyï¿½t.
	  */
	 public void destroySurface() {
		 System.out.println("Asteroid's destroySurface() has been called");
		 if(!this.isMined()) {
			 for(Character c: characters) {
				 c.die();
			 }
		 }
	 }
	 
	 /**
	  * Ellenï¿½rzi, hogy az adott aszteroidï¿½n lï¿½vï¿½ telepeseknï¿½l 
	  * rendelkezï¿½sre ï¿½ll-e az ï¿½rbï¿½zis felï¿½pï¿½tï¿½sï¿½hez szï¿½ksï¿½ges nyersanyagmennyisï¿½g.
	  * Ha igen, akkor meghï¿½vja a Game endGame() metï¿½dusï¿½t.
	  */
	 public void checkSpaceBase() {
		 System.out.println("Asteroid's checkSpaceBase() has been called");
		 ArrayList<Resource> temp=new ArrayList<Resource>();
		 for(Character c: characters) {
			 temp.addAll(c.getCollectedResources());
		 }
		 
		 Recipe recipe=game.getSpaceBaseRecipe();
		 for(Resource r : temp) {
			 if(recipe.isEmpty()) //Az aszteroida resource-ï¿½t is ellenï¿½rizni kell a checkspacebase-ben?
				 break;
			 recipe.isNeeded(r);
		 }
		 
		 if(recipe.isEmpty())
			 game.endGame();
		 recipe.reset();
	 }
	 
	 public void addResource(Resource r) {
		 System.out.println("Asteroid's addResource(r: Resource) has been called");
		 resource=r;
		 r.setAsteroid(this);
	 }
	 
	 public int getSizeOfNeighbors() {
		 System.out.println("Asteroid's getSizeOfNeighbors() has been called");
		 return neighbors.size();
	 }
	 
	 public void setSun(Sun s) {
		 System.out.println("Asteroid's setSun(s: Sun) has been called");
		 sun=s;
	 }
	 
	 public void setGame(Game g) {
		 System.out.println("Asteroid's setGame(g: Game) has been called");
		 game=g;
	 }
	 
	 public int getSizeOfCharacters() {
		 System.out.println("Asteroid's getSizeOfCharacters() has been called");
		 return characters.size();
	 }
}
