package berenyi_kft;

import java.util.ArrayList;

/**
 * A karakterek egyik fajtája a robot, 
 * amely minden idõegységben fúrni vagy szomszédos aszteroidára mozogni tud.
 * @author berenyi_kft
 *
 */
public class AIRobot extends Character implements ISteppable{

	/**
	 * A robot megkérdezi az aszteroidától, amelyen áll, 
	 * hogy mekkora a köpenyvastagsága. 
	 * Ha az aszteroida nincs teljesen átfúrva, 
	 * akkor a robot drill() mûveletet végez,
	 * különben valamelyik szomszédos aszteroidára lép (move(int d)).
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * auto implementation
	 */
	@Override
	public ArrayList<Resource> getCollectedResources() {
		return null;
	}
	
	/**
	 * A robot megsemmisül: 
	 * meghívja a Timer removeSteppable(Steppable s) metódusát.
	 */
	public void die() {
		
	}
	
	/**
	 * A robot egy véletlenszerû szomszédos aszteroidán landol: 
	 * sorsol egy d számot, 
	 * majd meghívja a move (int d) metódust.
	 */
	public void reactToExplosion() {
		
	}

	

}
