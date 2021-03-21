package berenyi_kft;

import java.util.Random;

/**
 * A karakterek egyik fajtaja a robot, 
 * amely minden idoegysegben furni vagy szomszedos aszteroidara mozogni tud.
 * @author berenyi_kft
 *
 */
public class AIRobot extends Character implements ISteppable {
	
	/**
	 * A jatek idozitoje, amely a robotot is lepteti.
	 */
	private Timer timer;
	
	/**
	 * Uj AI Robot jon letre, amely parameterkent atveszi az ot lepteto idozitot is.
	 * A konstruktor egybol hozzaadja a robotot az idozitojehez.
	 * @param timer A jatek idozitoje
	 */
	public AIRobot(Timer timer) {
		this.timer = timer;
		timer.addSteppable(this);
	}
	
	/**
	 * A robot megkerdezi az aszteroidatol, amelyen all, 
	 * hogy mekkora a kopenyvastagsaga. 
	 * Ha az aszteroida nincs teljesen atfurva, 
	 * akkor a robot drill() muveletet vegez,
	 * kulonben valamelyik szomszedos aszteroidara lep (move(int d)).
	 */
	public void step() {
		if (place.getRockLayerThickness() >= 1) {
			drill();
		} else {
			Random random = new Random();
			move(random.nextInt() % place.getNeighbors().size());
		}
	}
	
	/**
	 * A robot megsemmisul: 
	 * meghivja a Timer removeSteppable(ISteppable s) metodusat.
	 */
	public void die() {
		timer.removeSteppable(this);
	}
	
	/**
	 * A robot egy veletlenszeru szomszedos aszteroidan landol: 
	 * sorsol egy d szamot, majd meghivja magan a move (int d) metodust.
	 */
	
	@Override
	public void reactToExplosion() {
		System.out.println("AIRobot's reactToExplosion() has been called");
		Random random = new Random();
		move(random.nextInt(place.getSizeOfNeighbors()));
	}

}
