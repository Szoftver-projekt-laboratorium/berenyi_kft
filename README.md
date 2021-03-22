# berenyi_kft Olvasd el!
**/Bálint/ Kész a kommentezés+átnézés :)**

**A _Balint_ ágon megtaláltok minden módosítást. A master-be egyelőre nem akartam merge-elni, de nyugodtan tegyétek meg.**

A projektben java jdk-11.0.2-t állítottam be a [berenyi_kft projekt]/Build Path/Libraries/Classpath-ban JRE System library-nek
az eddigi jdk-14.0.2 helyett, ez a projektleíró fájlba be lett vezetve. Így a virtuális gépen már fordítható is,
nem csak futtatható (azon csak jdk-11.0.2-t találtunk).

Kitöröltem a Controller osztályt és a main.java-t is.
Töröltem pár felesleges getter-settert is, amit egyáltalán nem használtunk a teszteknél sem, és később sem lesz haszna.

"// TODO" kezdetű kommenteket írtam kb. 5-öt, amit még el kell dönteni, hogy hogy legyen: 
1. tesztek initjei után rakjunk-e üres sort
1. mi legyen a tabulálással
1. a checkSpaceBase() ellenőrizze-e a megfúrt aszteroida magjában levő nyersanyagot is
1. a teleportkapu-párok törlése a már kezdetben is meglévő szomszédságokat kitörölhet az aszteroidákból, ami nem biztos, hogy jó
1. az ids Map-nek nincs semmi szerepe, kitörölhetjük

A Recipe.reset() ugye minden nyersanyagot sikeresen klónoz, és ehhez elég a Resource() ősosztálynak Cloneable-nek lennie?
Nézzétek meg, a Timer-be írt kommenteket, meg egyébként fussátok át az egészet, hogy minden oké-e!

A többi fixet, módosítást nagyjából leírtam a kommentekben. Minden teszteset szépen fut.
(Fixek pl: 
- az Asteroid getNeighbor(int d) fv-e végzi a maradékos osztást a szomszédok számával.
  A robot random generálhat szomszédot, így jobban Tell don't Ask a megvalósítás.
- a napközelség másodszomszédos aszteroidákig is kiterjed
- a napvihar elér minden aszteroidát, ehhez a game-et kérdezi meg
- az AIRobot megkapja konstruktorban a timer-t (a Settler megkérdezi a game-től és átadja neki),
  és a konstruktorban ő adja hozzá magát a steppable-ökhöz. Így a die()-ban hívhat removeSteppable()-t a timer-en.
- az Uran-t Uranium-ra neveztem; és neki nincs explicit drilledOut(Asteroid a) fv-e,
  hanem a radioaktív nyersanyag drilledOut()-ját örökli (szerintem a kiírásnál nem baj,
  ha Uranium helyett RadioactiveResource jelenik meg)
