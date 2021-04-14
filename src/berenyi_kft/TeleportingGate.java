package berenyi_kft;

import java.util.Random;

/**
 * Teleportkaput reprezentalo osztaly,
 * amelyek mindig parban leteznek
 * @author berenyi_kft
 *
 */
public class TeleportingGate implements ISteppable {

	/**
	 * Az adott teleportkapu parja, amellyel osszekottetesben all 
	 */
	private TeleportingGate pair;
	
	/**
	 * Az aszteroida, amely korul az adott teleportkapu kering.
	 * Ha a kaput meg nem allitottak palyara, akkor értéke null
	 */
	private Asteroid asteroid=null;
	
	/**
	 * A telepes, aki tarolja a letrehozott teleportkaput.
	 * Ha mar palyara van allitva, akkor settler erteke null
	 */
	private Settler settler;
	
	private Timer timer;
	
	//--------------------------------------------------------------
	
	/**
	 * Visszaadja a teleportkapu parjat (pair).
	 * @return A teleportkapu parja, amellyel osszekottetesben all
	 */
	public TeleportingGate getPair() {
		System.out.println("TeleportingGate's getPair() has been called");
		return this.pair;
	}
	
	/**
	 * Beallitja a teleportkaput a tg kapu parjakent oda-vissza,
	 * amihez tg-n is meghivja ezt a fuggvenyt.
	 * @param tg A teleportkapunak beallitando par
	 */
	public void setPair(TeleportingGate tg) {
		System.out.println("TeleportingGate's setPair(tg: TeleportingGate) has been called");
		if (this.pair != tg ) {
			this.pair = tg;	
			tg.setPair(this);
		}
	}
	
	/**
	 * Visszaadja az aszteroidat, amely korul a teleportkapu kering.
	 * Ha a kaput meg nem allitottak palyara, akkor null-lal ter vissza.
	 * @return Az aszteroida, amely korul a teleportkapu kering.
	 * 		   Ha nics palyara allitva, akkor null-lal ter vissza.
	 */
	public Asteroid getAsteroid() {
		System.out.println("TeleportingGate's getAsteroid() has been called");
		return this.asteroid;
	}
	
	/**
	 * Beallitja a teleportkapu aszteroidajat, amely korul keringeni fog.
	 * @param a Az aszteroida, amely korul a teleportkapu palyara all
	 */
	public void setAsteroid(Asteroid a) {
		System.out.println("TeleportingGate's setAsteroid(a: Asteroid) has been called");
		this.asteroid = a;
	}
	
	/**
	 * A teleportkapu a parjaval egyutt megsemmisul. A kapu ehhez
	 * beallitja a parjanak a parjat null-ra (megszunteti az osszekottetest),
	 * meghivja a parjanak die() fuggvenyet, majd sajat magat is
	 * eltavolitja az aszteroidajarol/urhajojarol.
	 * 
	 * Ha a fuggvenyhivas elejen a teleportkapu parja mar null, 
	 * az csak akkor fordul elo, ha a parjan hivodott elobb a die() fuggveny.
	 * Ilyenkor csak magat tavolitja el a megfelelo helyrol.
	 */
	public void die() {
		System.out.println("TeleportingGate's die() has been called");
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
		
		if(timer.getSteppables().contains(this))
			timer.removeSteppable(this);
	}
	
	public void step() {
		Random random = new Random();
		move(random.nextInt());
	}
	
	public void move(int d) {
		Asteroid a=asteroid.getNeighbor(d);
		asteroid.remove(this);
		a.accept(this);
	}
	
	public void goMad() {
		timer.addSteppable(this);
	}
}
