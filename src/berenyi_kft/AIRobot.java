package berenyi_kft;

import java.util.Random;
import java.util.Scanner;

/**
 * A karakterek egyik fajtaja a robot, amely minden idoegysegben furni vagy
 * szomszedos aszteroidara mozogni tud
 * 
 * @author berenyi_kft
 */
public class AIRobot extends Character implements ISteppable {

	public String getDescription() {

		String str = "";

		String id = Proto.getId(this);
		str += "AIRobot " + id + "\n";

		String timerId = Proto.getId(timer);
		str += "\ttimer " + timerId + "\n";

		String placeId = Proto.getId(place);
		str += "\tplace " + placeId + "\n";

		return str;
	}

	/*
	 * Minta: AIRobot air1 timer timer place a1
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

			case "place":
				place = (Asteroid) Proto.getObject(tokens[1]);
				break;

			default:
				break;
			}
		}
	}

	/**
	 * Uj AI Robot jon letre, amely parameterkent atveszi az ot lepteto idozitot is.
	 * A konstruktor egybol hozzaadja a robotot az idozitojehez.
	 * 
	 * @param timer A jatek idozitoje
	 */
	public AIRobot(Timer timer) {
		Proto.println(Proto.getId(this) + ".AIRobot(" + Proto.getId(timer) + ")");
		Proto.incrTabs();
		this.timer = timer;
		if (timer != null)
			timer.addSteppable(this);
		Proto.decrTabs();
	}

	/**
	 * A robot megkerdezi az aszteroidatol, amelyen all, hogy mekkora a
	 * kopenyvastagsaga. Ha az aszteroida nincs teljesen atfurva, akkor a robot
	 * drill() muveletet vegez, kulonben valamelyik szomszedos aszteroidara lep
	 * (move(int d)).
	 */
	public void step() {
		Proto.println(Proto.getId(this) + ".step()");
		Proto.incrTabs();
		if (place.getRockLayerThickness() >= 1) {
			drill();
		} else {
			if (Proto.isRandom()) {
				Random random = new Random();
				move(random.nextInt());
			} else
				move(0);
		}
		Proto.decrTabs();
	}

	/**
	 * A robot megsemmisul: meghivja a Timer removeSteppable(ISteppable s)
	 * metodusat.
	 */
	@Override
	public void die() {
		Proto.println(Proto.getId(this) + ".die()");
		Proto.incrTabs();
		super.die();
		timer.removeSteppable(this);
		Proto.getAllObjects().removeAIRobot(this);
		Proto.decrTabs();
	}

	/**
	 * A robot egy veletlenszeru szomszedos aszteroidan landol: sorsol egy d szamot,
	 * majd meghivja magan a move (int d) metodust.
	 */

	@Override
	public void reactToExplosion() {
		Proto.println(Proto.getId(this) + ".reactToExplosion()");
		Proto.incrTabs();
		if (Proto.isRandom()) {
			Random random = new Random();
			move(random.nextInt());
		} else
			move(0);
		Proto.decrTabs();
	}

	/**
	 * A robot furja az aszteroidat, amelyen all.
	 */
	public void drill() {
		Proto.println(Proto.getId(this) + ".reactToExplosion()");
		Proto.incrTabs();
		place.drilled();
		Proto.decrTabs();
	}

}
