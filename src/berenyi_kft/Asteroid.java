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
	 Resource resource;
	 
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
	 ArrayList<Character> character = new ArrayList<Character>();
	 
	 /**
	  * a k�zvetlen�l az aszteroida k�r�l kering� teleportkapuk halmaza
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
	  * Hozz�adja a neighbor aszteroid�t az aszteroida neighbors kollekci�j�hoz.
	  * @param a
	  */
	 public void accept(Asteroid a) {
		 this.neighbors.add(a);
	 }
	 
	 /**
	  * Elt�vol�tja a neighbor aszteroid�t a neighbors kollekci�b�l.
	  * @param a
	  */
	 public void remove(Asteroid a) {
		 if(this.neighbors.contains(a)) {
			 this.neighbors.remove(a);
		 }
	 }
	 
	 
	 /**
	  * Visszatér az aszteroida köpenyvastagságával,
	  * ami egy nemnegatív egész szám.
	  * @return Az aszteroida aktuális köpenyvastagsága
	  */
	 public int getRockLayerThickness() {
		 return rockLayerThickness;
	 }
	 
	 
	 /**
	  * Megadja az aszteroida d-edik szomsz�dj�t 
	  * (az aszteroid�hoz tartoz� teleportkapuk �ltali szomsz�dai figyelembev�tel�vel).
	  * @param d
	  * @return
	  */
	 public Asteroid getNeighbor(int d) {
		 return this.neighbors.get(d);
	 }
	 
	 /**
	  * Visszaadja az adott aszteroid�val szomsz�dos aszteroid�k kollekci�j�t, 
	  * bele�rtve ebbe az aszteroida k�zvetlen szomsz�dait (neighbors)
	  * �s a teleportkapuk (gates) �ltali szomsz�dokat is.
	  * @return
	  */
	 public ArrayList<Asteroid> getNeighbor(){
		 return this.neighbors;
	 }
	 
	 
	 /**
	  * A c karakter meg�rkezik az aszteroid�ra, 
	  * az aszteroida hozz�adja a characters kollekci�j�hoz.
	  * @param c
	  */
	 public void accept(Character c) {
		 
	 }
	 

	/**
	  * A c karakter elhagyja az aszteroid�t, az aszteroida 
	  * elt�vol�tja a characters kollekci�j�b�l.
	  * @param c
	  */
	 public void remove(Character c) {
		 
	 }
	 
	 /**
	  * Visszaadja az adott aszteroid�n tart�zkod� karakterek kollekci�j�t.
	  * @return
	  */
	 public ArrayList<Character> getCharacters(){
		 return this.character;
	 }
	 
	 /**
	  * A tg teleportkapu p�ly�ra �ll az aszteroida k�r�l, 
	  * az aszteroida hozz�adja a gates kollekci�j�hoz.
	  * @param tg
	  */
	 public void accept(TeleportingGate tg) {
		 
	 }
	 
	 /**
	  * A tg teleportkaput elt�vol�tja az aszteroida k�r�li p�ly�r�l, 
	  * az aszteroida t�rli a gates kollekci�j�b�l. 
	  * @param tg
	  */
	 public void remove(TeleportingGate tg) {
		 
	 }
	 
	 /**
	  * Visszaadja az adott aszteroid�hoz tartoz� teleportkapukat.
	  * @return
	  */
	 public ArrayList<TeleportingGate> getGate(){
		 return this.gates;
	 }
	 
	 /**
	  * Egy az aszteroid�n tart�zkod� telepes behelyezi az r nyersanyagot 
	  * az aszteroida �reges magj�ba, az aszteroida azt be�ll�tja resource attrib�tum�nak. 
	  * Ha a param�ter�l kapott r=null, 
	  * akkor a f�ggv�nynek nincs hat�sa, 
	  * a resource attrib�tumot nem �rja fel�l.
	  * @param r
	  */
	 public void accept(Resource r) {
		 
	 }
	 
	 /**
	  * Egy az aszteroid�n tart�zkod� telepes elt�vol�tja a magban tal�lhat� nyersanyagot. 
	  * Az aszteroida a resource attrib�tum�t null-ra �ll�tja, visszat�r�si �rt�k�l
	  * a nyersanyagot adja. Ha kezdetben resource=null volt,
	  * a f�ggv�nynek nincs mell�khat�sa, �s null-lal t�r vissza
	  * @return
	  */
	 public Resource removeResource() {
		 //todo
		 return this.resource; //temp; 
	 }
	 
	 /**
	  * Visszaadja az adott aszteroida magj�ban tal�lhat� nyersanyagot.
	  * @return
	  */
	 public Resource getResource() {
		 return this.resource;
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
		 
	 }
	 
	 /**
	  * Az s telepes b�ny�szik az aszteroid�n. Ha az aszteroida k�penyvastags�ga nem 0,
	  *  akkor a f�ggv�nynek nincs hat�sa. Ha a k�penyvastags�g 0, 
	  *  �s az aszteroida resource attrib�tuma nem null,
	  *   akkor elt�vol�tja azt a magj�b�l (removeResource()),
	  *  �s elt�roltatja azt az s telepessel (s.accept(resource)).
	  * @param s
	  */
	 public void minedBySettler(Settler s) {
		 
	 }
	 
	 /**
	  * Visszat�r annak logikai �rt�k�vel, hogy az aszteroida megf�rt �s �reges,
	  *  vagyis rockLayerThickness=0 �s resource=null.
	  * @return
	  */
	 public boolean isMined() {
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
		 
	 }
	 
	 /**
	  * Az aszteroid�n v�gigs�p�r a napvihar. Ha az aszteroida 
	  * nincs megf�rva (rockLayerThickness > 0), 
	  * vagy a mag nem �reges (getResource() != null), akkor a f�ggv�ny 
	  * megh�vja az aszteroid�n tart�zkod� karakterek die() f�ggv�ny�t.
	  */
	 public void destroySurface() {
		 
	 }
	 
	 /**
	  * Ellen�rzi, hogy az adott aszteroid�n l�v� telepesekn�l 
	  * rendelkez�sre �ll-e az �rb�zis fel�p�t�s�hez sz�ks�ges nyersanyagmennyis�g.
	  * Ha igen, akkor megh�vja a Game endGame() met�dus�t.
	  */
	 public void checkSpaceBase() {
		 
	 }
	 
	 
	 
	
}
