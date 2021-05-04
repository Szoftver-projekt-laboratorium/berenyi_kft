package berenyi_kft;

import java.util.Random;
import java.util.Scanner;

/**
 * Teleportkaput reprezentalo osztaly, amelyek mindig parban leteznek
 * 
 * @author berenyi_kft
 *
 */
public class TeleportingGate implements ISteppable {

	/**
	 * Az adott teleportkapu parja, amellyel osszekottetesben all
	 */
	private TeleportingGate pair = null;

	/**
	 * Az aszteroida, amely korul az adott teleportkapu kering. Ha a kaput meg nem
	 * allitottak palyara, akkor értéke null
	 */
	private Asteroid asteroid = null;

	/**
	 * A telepes, aki tarolja a letrehozott teleportkaput. Ha mar palyara van
	 * allitva, akkor settler erteke null
	 */
	private Settler settler = null;

	/**
	 * A jatek idozitoje
	 */
	private Timer timer = null;
	
	/**
	 * Jeloli, hogy a teleportkapu kiemelt allapotban van-e
	 */
	private boolean emphasized = false;

	// --------------------------------------------------------------

	public String getDescription() {

		String str = "";

		String id = Proto.getId(this);
		str += "TeleportingGate " + id + "\n";

		String timerId = Proto.getId(timer);
		str += "\ttimer " + timerId + "\n";

		String pairId = Proto.getId(pair);
		str += "\tpair " + pairId + "\n";

		String settlerId = Proto.getId(settler);
		str += "\tsettler " + settlerId + "\n";

		String asteroidId = Proto.getId(asteroid);
		str += "\tasteroid " + asteroidId + "\n";

		return str;
	}

	/**
	 * Uj teleportkapu jon letre, amely parameterkent atveszi 
	 * az ot lepteto idozitot is.
	 * 
	 * @param timer A jatek idozitoje
	 */
	/*
	 * public TeleportingGate(Timer timer) { Proto.println("TeleportingGate(" +
	 * Proto.getId(timer) + ")"); this.timer = timer; }
	 */

	/**
	 * Beallitja a teleportkapunak a timer idozitot.
	 * 
	 * @param timer Az idozito a jatekban
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	};

	/**
	 * Visszaadja a teleportkapu parjat (pair).
	 * 
	 * @return A teleportkapu parja, amellyel osszekottetesben all
	 */
	public TeleportingGate getPair() {
		Proto.println(Proto.getId(this) + ".getPair()");
		return this.pair;
	}

	/**
	 * Beallitja a teleportkaput tarolo telepest.
	 * 
	 * @param s A telepes, aki eltarolja a kaput
	 */
	public void setSettler(Settler s) {
		Proto.println(Proto.getId(this) + ".setSettler(" + Proto.getId(s) + ")");
		Proto.incrTabs();
		settler = s;
		Proto.decrTabs();
	}

	/**
	 * Beallitja a teleportkaput a tg kapu parjakent oda-vissza, amihez tg-n is
	 * meghivja ezt a fuggvenyt.
	 * 
	 * @param tg A teleportkapunak beallitando par
	 */
	public void setPair(TeleportingGate tg) {
		Proto.println(Proto.getId(this) + ".setPair(" + Proto.getId(tg) + ")");
		Proto.incrTabs();
		if (this.pair != tg) {
			this.pair = tg;
			if (tg != null)
				tg.setPair(this);
		}
		Proto.decrTabs();
	}

	/**
	 * Visszaadja az aszteroidat, amely korul a teleportkapu kering. Ha a kaput meg
	 * nem allitottak palyara, akkor null-lal ter vissza.
	 * 
	 * @return Az aszteroida, amely korul a teleportkapu kering. Ha nics palyara
	 *         allitva, akkor null-lal ter vissza.
	 */
	public Asteroid getAsteroid() {
		Proto.println(Proto.getId(this) + ".getAsteroid()");
		return this.asteroid;
	}

	/**
	 * Beallitja a teleportkapu aszteroidajat, amely korul keringeni fog.
	 * 
	 * @param a Az aszteroida, amely korul a teleportkapu palyara all
	 */
	public void setAsteroid(Asteroid a) {
		Proto.println(Proto.getId(this) + ".setAsteroid(" + Proto.getId(a) + ")");
		Proto.incrTabs();
		this.asteroid = a;
		Proto.decrTabs();
	}

	/**
	 * A teleportkapu a parjaval egyutt megsemmisul. A kapu ehhez beallitja a
	 * parjanak a parjat null-ra (megszunteti az osszekottetest), meghivja a
	 * parjanak die() fuggvenyet, majd sajat magat is eltavolitja az
	 * aszteroidajarol/urhajojarol.
	 * 
	 * Ha a fuggvenyhivas elejen a teleportkapu parja mar null, az csak akkor fordul
	 * elo, ha a parjan hivodott elobb a die() fuggveny. Ilyenkor csak magat
	 * tavolitja el a megfelelo helyrol.
	 */
	public void die() {
		Proto.println(Proto.getId(this) + ".die()");
		Proto.incrTabs();
		if (pair != null) {
			pair.setPair(null);
			pair.die();
			this.setPair(null);
		}
		if (settler != null) {
			settler.remove(this);
		} else {
			asteroid.remove(this);
		}

		if (timer.getSteppables().contains(this)) { // (a feltetel nem szukseges)
			timer.removeSteppable(this);
		}
		Proto.getAllObjects().removeTeleportingGate(this);
		Proto.decrTabs();
	}

	/**
	 * A megkergult teleportkapu egy veletlenszeru szomszedos aszteroidara koruli
	 * palyara all at.
	 */
	public void step() {
		Proto.println(Proto.getId(this) + ".step()");
		Proto.incrTabs();
		if (Proto.isRandom()) {
			Random random = new Random();
			move(random.nextInt());
		} else
			move(0);
		Proto.decrTabs();
	}

	/**
	 * A megkergult teleportkapu szomszedos aszteroidara mozog.
	 * 
	 * @param d A szomszedos aszteroida sorszama
	 */
	public void move(int d) {
		Proto.println(Proto.getId(this) + ".move(" + d + ")");
		Proto.incrTabs();
		Asteroid a = asteroid.getNeighbor(d);
		asteroid.remove(this);
		a.accept(this);
		Proto.decrTabs();
	}

	/**
	 * A teleportkapu megkergul: az idozito steppables listajahoz adja magat.
	 */
	public void goMad() {
		Proto.println(Proto.getId(this) + ".goMad()");
		Proto.incrTabs();
		if(!timer.getSteppables().contains(this))
			timer.addSteppable(this);
		Proto.decrTabs();
	}
	
	/**
	 * Megadja, hogy a kapu ki van-e emelve.
	 * @return - <code>true</code>, ha a teleportkapu kiemelt
	 */
	public boolean isEmphasized() {
		return emphasized;
	}
	
	/**
	 * Beallitja a teleportkapu kiemeltseget (kiemelt vagy sem).
	 * @param ag - a kapu uj kiemelt allapota
	 */
	public void setEmphasized(boolean emphasized) {
		this.emphasized = emphasized;
	}

	/**
	 * Beolvassa a jatek attributumait az sc Scanner aktualis poziciojatol.
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
			case "timer":
				timer = (Timer) Proto.getObject(tokens[1]);
				break;

			case "pair":
				pair = (TeleportingGate) Proto.getObject(tokens[1]);
				break;

			case "asteroid":
				asteroid = (Asteroid) Proto.getObject(tokens[1]);
				break;

			case "settler":
				settler = (Settler) Proto.getObject(tokens[1]);
				break;

			default:
				break;
			}
		}
	}
}
