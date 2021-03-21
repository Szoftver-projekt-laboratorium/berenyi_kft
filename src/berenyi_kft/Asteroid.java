package berenyi_kft;

import java.util.ArrayList;

/**
 * 
 * @author berenyi_kft
 * Aszteroid�kat reprezent�l� oszt�ly
 */
public class Asteroid {
	 
	 /**
	  * az aszteroida k�penyvastags�ga, vagyis a magot bor�t� sziklar�tegek sz�ma
	  */
	 int rockLayerThickness;
	 
	 /**
	  * a j�t�kot reprezent�l� oszt�ly
	  */
	 Game game;
	
	 /**
	  * az aszteroida magj�ban tal�lhat� egys�gnyi nyersanyag, 
	  * ha a mag �reges, akkor �rt�ke null
	  */
	 Resource resource= null;
	 
	 /**
	  * az aszteroida�vben lev� Nap
	  */
	 Sun sun;
	 
	 /**
	  * az aszteroid�val szomsz�dos aszteroid�k list�ja; 
	  * a teleportkapuk �ltal szomsz�doss� v�lt aszteroid�kat is mag�ba foglalja
	  */
	 ArrayList<Asteroid> neighbors = new ArrayList<Asteroid>();
	 
	 /**
	  * az aszteroid�n tart�zkod� karakterek (telepesek, robotok, stb.) kollekci�ja
	  */
	 ArrayList<Character> characters = new ArrayList<Character>();
	 
	 /**
	  * a k�zvetlen�l az aszteroida k�r�l kering� teleportkapuk halmaza
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
	  * Hozz�adja a neighbor aszteroid�t az aszteroida neighbors kollekci�j�hoz.
	  * @param a
	  */
	 public void accept(Asteroid a) {
		 System.out.println("Asteroid's accept(a: Asteroid) has been called");
		 neighbors.add(a);
	 }
	 
	 /**
	  * Elt�vol�tja a neighbor aszteroid�t a neighbors kollekci�b�l.
	  * @param a
	  */
	 public void remove(Asteroid a) {
		 System.out.println("Asteroid's remove(a: Asteroid) has been called");
		 if(neighbors.contains(a)) {
			 neighbors.remove(a);
		 }
	 }
	 
	 /**
	  * Megadja az aszteroida d-edik szomsz�dj�t 
	  * (az aszteroid�hoz tartoz� teleportkapuk �ltali szomsz�dai figyelembev�tel�vel).
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
	  * Visszaadja az adott aszteroid�val szomsz�dos aszteroid�k kollekci�j�t, 
	  * bele�rtve ebbe az aszteroida k�zvetlen szomsz�dait (neighbors)
	  * �s a teleportkapuk (gates) �ltali szomsz�dokat is.
	  * @return
	  */
	 public ArrayList<Asteroid> getNeighbors(){
		 System.out.println("Asteroid's getNeighbors() has been called");
		 return neighbors;
	 }
	 
	 
	 /**
	  * A c karakter meg�rkezik az aszteroid�ra, 
	  * az aszteroida hozz�adja a characters kollekci�j�hoz.
	  * @param c
	  */
	 public void accept(Character c) {
		 System.out.println("Asteroid's accept(c: Character) has been called");
		 characters.add(c);
	 }
	 
	 /**
	  * A c karakter elhagyja az aszteroid�t, az aszteroida 
	  * elt�vol�tja a characters kollekci�j�b�l.
	  * @param c
	  */
	 public void remove(Character c) {
		 System.out.println("Asteroid's remove(c: Character) has been called");
		 characters.remove(c);
	 }
	 
	 /**
	  * Visszaadja az adott aszteroid�n tart�zkod� karakterek kollekci�j�t.
	  * @return
	  */
	 public ArrayList<Character> getCharacters(){
		 System.out.println("Asteroid's getCharacters() has been called");
		 return characters;
	 }
	 
	 /**
	  * A tg teleportkapu p�ly�ra �ll az aszteroida k�r�l, 
	  * az aszteroida hozz�adja a gates kollekci�j�hoz.
	  * @param tg
	  */
	 public void accept(TeleportingGate tg) {
		 System.out.println("Asteroid's accept(tg: TeleportingGate) has been called");
		 gates.add(tg);
	 }
	 
	 /**
	  * A tg teleportkaput elt�vol�tja az aszteroida k�r�li p�ly�r�l, 
	  * az aszteroida t�rli a gates kollekci�j�b�l. 
	  * @param tg
	  */
	 public void remove(TeleportingGate tg) {
		 System.out.println("Asteroid's remove(tg: TeleportingGate) has been called");
		 gates.remove(tg);
	 }
	 
	 /**
	  * Visszaadja az adott aszteroid�hoz tartoz� teleportkapukat.
	  * @return
	  */
	 public ArrayList<TeleportingGate> getGates(){
		 System.out.println("Asteroid's getGates() has been called");
		 return gates;
	 }
	 
	 /**
	  * Egy az aszteroid�n tart�zkod� telepes behelyezi az r nyersanyagot 
	  * az aszteroida �reges magj�ba, az aszteroida azt be�ll�tja resource attrib�tum�nak. 
	  * Ha a param�ter�l kapott r=null, 
	  * akkor a f�ggv�nynek nincs hat�sa, 
	  * a resource attrib�tumot nem �rja fel�l.
	  * @param r
	  */
	 //Ezt a f�ggv�nyt a Settlernek a restore(r) met�dusa h�vja meg, azt v�ltoztattuk rajta, hogy nem csak a Resource
	 //a param�ter, hanem a Settler is. Settler restore-ban 2 param�terrel h�vj�tok meg.
	 public void accept(Settler s, Resource r) {
		 System.out.println("Asteroid's accept(s: Settler, r: Resource) has been called");
		 if(this.isMined()) {
			 resource=r;
			 resource.setAsteroid(this);
			 s.remove(r);
			 if(sun.isCloseToSun(this)) {
				 resource.drilledOut(this);  //Don�t tan�csait megfogadva ha napk�zelben restore-olunk, akkor h�v�dik meg.
			 }
		 }
	 }
	 
	 /**
	  * Egy az aszteroid�n tart�zkod� telepes elt�vol�tja a magban tal�lhat� nyersanyagot. 
	  * Az aszteroida a resource attrib�tum�t null-ra �ll�tja. Ha kezdetben resource=null volt,
	  * a f�ggv�nynek nincs mell�khat�sa.
	  * @return
	  */
	 public void removeResource() {
		 System.out.println("Asteroid's removeResource() has been called");
		 resource=null;
	 }
	 
	 /**
	  * Visszaadja az adott aszteroida magj�ban tal�lhat� nyersanyagot.
	  * @return
	  */
	 public Resource getResource() {
		 System.out.println("Asteroid's getResource() has been called");
		 return resource;
	 }
	 
	 /**
	  * Az aszteroida rockLayerThickness attrib�tum�t eggyel cs�kkenti, 
	  * amennyiben az pozit�v volt. Ha ekkor a k�penyvastags�g 0, 
	  * �s a resource attrib�tuma nem null, akkor a sun objektum isCloseTo(Asteroid a) 
	  * f�ggv�ny�vel lek�rdezi, hogy napk�zelben tal�lhat�-e. 
	  * Ha igen, akkor megh�vja a resource nyersanyag drilledOut() f�ggv�ny�t, jelezve, 
	  * hogy napk�zeli aszteroid�n felsz�nre ker�lt.
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
	  * Az s telepes b�ny�szik az aszteroid�n. Ha az aszteroida k�penyvastags�ga nem 0,
	  *  akkor a f�ggv�nynek nincs hat�sa. Ha a k�penyvastags�g 0, 
	  *  �s az aszteroida resource attrib�tuma nem null,
	  *   akkor elt�vol�tja azt a magj�b�l (removeResource()),
	  *  �s elt�roltatja azt az s telepessel (s.accept(resource)), ezenk�v�l
	  *  megh�vja a checkSpaceBase() met�dust.
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
	  * Visszat�r annak logikai �rt�k�vel, hogy az aszteroida megf�rt �s �reges,
	  *  vagyis rockLayerThickness=0 �s resource=null.
	  * @return
	  */
	 public boolean isMined() {
		 System.out.println("Asteroid's isMined() has been called");
		 return (this.rockLayerThickness==0 && this.resource==null) ? true : false;
	 }
	 
	 /**
	  * A megf�rt, napk�zelben l�v�, radioakt�v nyersanyagot tartalmaz� 
	  * aszteroida felrobban. Minden rajta tart�zkod� karakternek megh�vja
	  * a reactToExplosion() f�ggv�ny�t. Az aszteroida szomsz�dain a 
	  * remove(Asteroid neighbor) f�ggv�nyt h�vja, amivel t�rli mag�t
	  * a szomsz�dai szomsz�ds�gi list�ib�l, majd megsemmis�ti
	  * a k�r�l�tte kering� teleportkapukat a p�rjaikkal egy�tt. V�g�l elt�vol�tja mag�t 
	  * a j�t�kb�l a Game oszt�ly removeAsteroid() f�ggv�ny�t h�vva.
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
	  * Az aszteroid�n v�gigs�p�r a napvihar. Ha az aszteroida 
	  * nincs megf�rva (rockLayerThickness > 0), 
	  * vagy a mag nem �reges (getResource() != null), akkor a f�ggv�ny 
	  * megh�vja az aszteroid�n tart�zkod� karakterek die() f�ggv�ny�t.
	  */
	 public void destroySurface() {
		 System.out.println("Asteroid's destroySurface() has been called");
		 if(!this.isMined()) {
			 for(int i=characters.size()-1;i>=0;i--) {
				 characters.get(i).die();
			 }
		 }
	 }
	 
	 /**
	  * Ellen�rzi, hogy az adott aszteroid�n l�v� telepesekn�l 
	  * rendelkez�sre �ll-e az �rb�zis fel�p�t�s�hez sz�ks�ges nyersanyagmennyis�g.
	  * Ha igen, akkor megh�vja a Game endGame() met�dus�t.
	  */
	 public void checkSpaceBase() {
		 System.out.println("Asteroid's checkSpaceBase() has been called");
		 ArrayList<Resource> temp=new ArrayList<Resource>();
		 for(Character c: characters) {
			 temp.addAll(c.getCollectedResources());
		 }
		 
		 Recipe recipe=game.getSpaceBaseRecipe();
		 for(Resource r : temp) {
			 if(recipe.isEmpty()) //Az aszteroida resource-�t is ellen�rizni kell a checkspacebase-ben?
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
